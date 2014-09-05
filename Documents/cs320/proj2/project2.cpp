#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <math.h>
#include <bitset>

using namespace std;

struct cache_line{

	char type_of_ins;
	long mem_addr;
	unsigned int tag;
};

int main(int argc, char* argv[])
{
//check right num of args
	if(argc != 3)
	{
		cout << "bad input, enter 2 files, quiting program" << endl;
		exit(1);
	}
	else
	{
		ifstream in(argv[1]);
		ofstream out(argv[2]);
		
		if(!in.good() || !out.good())
		{
			cout << "error, couldnt open either input or output file, try again" << endl;
			exit(2);
		}
	
	
		string input;
		vector<cache_line> list;
		while(!in.eof())
		{
	//get input from file, use getline
			getline(in, input);
			
			cache_line cl;
	//turn string from getline into a stream so i can parse it 			
			stringstream stream;
			stream << input;
			stream >> cl.type_of_ins >> hex >> cl.mem_addr;
			list.push_back(cl);
		}	
/*		
	//check data was read in correctly	
		int i = 0;
		for(i = 0; i < 100; i++)
		{
			bitset<48> x(list[i].mem_addr);
			cout << list[i].type_of_ins << " " << x << endl; 
			
			if(list[i].type_of_ins == 'S')
			{				
				num_stores++;
			}
			else if(list[i].type_of_ins == 'L')
			{
				num_loads++;
			}
			
		}	
		cout << "num stores = " << dec << num_stores << " num loads = " << num_loads << endl;
*/		
//direct mapped cache
cout << "DIRECT MAPPED CACHE" << endl;

		int dm_cache_hit = 0;
		string results_line;
		int line_size = pow(2,6);
		
		for(int k = 12; k < 17; k++)
		{
			
			int dm_cache_size = pow(2,k);
			
			int dm_indexes = dm_cache_size/line_size;
			
			unsigned int dm_table[dm_indexes];
			
			unsigned int dm_num_index_bits = log2(dm_indexes);
			unsigned int dm_num_tag_bits = 32 - (dm_num_index_bits + log2(line_size));

	//figure out masks for tag and index
			
			unsigned int dm_tag_mask = (unsigned int)(pow(2,32) - (pow(2,dm_num_index_bits + log2(line_size))));
			unsigned int dm_index_mask =(unsigned int)(pow(2, dm_num_index_bits + log2(line_size)) - line_size);
			
	
			//cout << "cache size " << dm_cache_size << " + indexes " << dm_indexes << " + indexbits " << dm_num_index_bits << " + tagbits " << dm_num_tag_bits << " + tag mask " << dm_tag_mask << " ++ indexmask " << dm_index_mask << endl;
			
			
			
			for(int l = 0; l < list.size(); l++)
			{			
					unsigned int dm_mem = list[l].mem_addr;
					unsigned int dm_tag = dm_tag_mask & dm_mem;				
					unsigned int dm_index = dm_index_mask & dm_mem;
					dm_index = (unsigned int)dm_index >> (int)log2(line_size);
										
					if(dm_table[dm_index] == dm_tag)
					{	
						dm_cache_hit++;
					}
					else
					{
						dm_table[dm_index] = dm_tag;
					}					
			}
		
			double dm_result = (double)dm_cache_hit / list.size();
			dm_result = (int)((dm_result * 100) + .5);
			
			cout << dec << dm_result << " + " << dm_cache_hit << endl;
			dm_cache_hit = 0;					
	/*
			bitset<32> a(list[0].mem_addr);
			bitset<32> b(tag);
			bitset<32> c(index);
			
			cout << a << " + " << b << " + " << c << endl;
	*/		
		}		
				
//set associative implemented with 2d array
cout << "set associative" << endl;
	for(int a = 1; a < 5; a++)
	{
		unsigned int sa_table[1024][(int)pow(2,a)];
		
		unsigned int sa_lru_cache_hits = 0;
		unsigned int sa_lru_hit = 0;
		
		unsigned int sa_num_index_bits = 10;
		unsigned int sa_num_tag_bits = 32 - (sa_num_index_bits + (int)log2(line_size));
		
		unsigned int sa_tag_mask = (unsigned int)(pow(2,32) - (pow(2,sa_num_index_bits + log2(line_size))));
		unsigned int sa_index_mask =(unsigned int)(pow(2, sa_num_index_bits + log2(line_size)) - line_size);
		
		for(int b = 0; b < list.size(); b++)
		{
			unsigned int sa_mem = list[b].mem_addr;
			unsigned int sa_tag = sa_tag_mask & sa_mem;
			unsigned int sa_index = sa_index_mask & sa_mem;
			sa_index = (unsigned int)sa_index >> (int)log2(line_size);
			
			int c;
			for(c = 0; c < a; c++)
			{				
				if(sa_table[sa_index][c] == sa_tag)
				{		
					sa_lru_hit = 1;
					break;
				}
			}
			
			if(sa_lru_hit == 1)
			{
				sa_lru_cache_hits++;
				
				for(int e = c; e > 0; e--)
				{
					sa_table[sa_index][e] = sa_table[sa_index][e-1];
				}
				sa_table[sa_index][0] = sa_tag;
			}
			else
			{
				for(int d = a; d > 0; d--)
				{
					sa_table[sa_index][d] = sa_table[sa_index][d-1];
				}
				sa_table[sa_index][0] = sa_tag;
			}
			sa_lru_hit = 0;
		}
	
		double sa_result = (double)sa_lru_cache_hits / list.size();
		sa_result = (int)((sa_result * 100) + .5);
		cout << sa_result << " + " << sa_lru_cache_hits << endl;
		sa_lru_cache_hits = 0;
	}
	
//fully associative

	cout << "FULLY ASSOCIATIVE" << endl;
	unsigned int fa_rr_table[1024];
	unsigned int fa_lru_table[1024];
	
	unsigned int fa_tag_mask = pow(2, 32) - line_size;
	
	int fa_rand_cache_hits = 0;
	int fa_lru_cache_hits = 0;
	
	int fa_rr_hit = 0;	
	int fa_lru_hit = 0;
	
	for(int m = 0; m < list.size(); m++)
	{
	//random replacement
		int fa_rand_num = rand() % 1024;

		unsigned int fa_mem = list[m].mem_addr;
		unsigned int fa_tag = fa_tag_mask & fa_mem;
	
		for(int n = 0; n < 1024; n++)
		{
			if(fa_rr_table[n] == fa_tag)
			{
				fa_rr_hit = 1;
				break;
			}
		}	
		if(fa_rr_hit == 0)
		{			
			fa_rr_table[fa_rand_num] = fa_tag;	
		}
		else
		{
			fa_rand_cache_hits++;
		}
		fa_rr_hit = 0;
	
	//LRU replacement	
		int o;
		for(o = 0; o < 1024; o++)
		{
			if(fa_lru_table[o] == fa_tag)
			{
				fa_lru_hit = 1;
				break;
			}	
		}
		if(fa_lru_hit == 1) 
		{
			fa_lru_cache_hits++;
			for(int p = o; p > 0; p--)
			{	
					fa_lru_table[p] = fa_lru_table[p - 1]; 
			}
			fa_lru_table[0] = fa_tag;								
		}
		else
		{
			for(int p = 1023; p > 0; p--)
			{	
				fa_lru_table[p] = fa_lru_table[p - 1]; 
			}
			fa_lru_table[0] = fa_tag;
		}	
		fa_lru_hit = 0;	
	}
		double fa_rr_result = (double)fa_rand_cache_hits / list.size();
		fa_rr_result = (int) ((fa_rr_result * 100) + .5);
		double fa_lru_result = (double)fa_lru_cache_hits / list.size();
		fa_lru_result = (int) ((fa_lru_result * 100) + .5);
		cout << "random " << fa_rr_result << " + " << fa_rand_cache_hits << endl;
		cout << "lru " << fa_lru_result << " + " << fa_lru_cache_hits << endl;

//set associative with no write on store miss

//set associative implemented with 2d array
cout << "set associative w/ no write on store miss" << endl;
	for(int f = 1; f < 5; f++)
	{
		unsigned int sanw_table[1024][(int)pow(2,f)];
		
		unsigned int sanw_lru_cache_hits = 0;
		unsigned int sanw_lru_hit = 0;
		
		unsigned int sanw_num_index_bits = 10;
		unsigned int sanw_num_tag_bits = 32 - (sanw_num_index_bits + (int)log2(line_size));
		
		unsigned int sanw_tag_mask = (unsigned int)(pow(2,32) - (pow(2,sanw_num_index_bits + log2(line_size))));
		unsigned int sanw_index_mask =(unsigned int)(pow(2, sanw_num_index_bits + log2(line_size)) - line_size);
		
		for(int g = 0; g < list.size(); g++)
		{
			unsigned int sanw_mem = list[g].mem_addr;
			unsigned int sanw_tag = sanw_tag_mask & sanw_mem;
			unsigned int sanw_index = sanw_index_mask & sanw_mem;
			sanw_index = (unsigned int)sanw_index >> (int)log2(line_size);
			
			int h;
			for(h = 0; h < f; h++)
			{				
				if(sanw_table[sanw_index][h] == sanw_tag)
				{		
					sanw_lru_hit = 1;
					break;
				}
			}
			if(list[g].type_of_ins == 'S')
			{
				if(sanw_lru_hit == 1)
				{
					sanw_lru_cache_hits++;
				}
			}
			else
			{
				if(sanw_lru_hit == 1)
				{
					sanw_lru_cache_hits++;
				
					for(int t = h; t > 0; t--)
					{
						sanw_table[sanw_index][t] = sanw_table[sanw_index][t-1];
					}
					sanw_table[sanw_index][0] = sanw_tag;
				}
				else
				{
					for(int u = f; u > 0; u--)
					{
						sanw_table[sanw_index][u] = sanw_table[sanw_index][u-1];
					}
					sanw_table[sanw_index][0] = sanw_tag;
				}
			}
			sanw_lru_hit = 0;
		}
	
		double sanw_result = (double)sanw_lru_cache_hits / list.size();
		sanw_result = (int)((sanw_result * 100) + .5);
		cout << sanw_result << " + " << sanw_lru_cache_hits << endl;
		sanw_lru_cache_hits = 0;
	}




//extra credit 		

		
	}
}
