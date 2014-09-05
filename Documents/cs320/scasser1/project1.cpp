#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <math.h>

using namespace std;

struct branch_line{

	unsigned long hex_num;
	string branch_path;	
};

int main(int argc, char* argv[])
{

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
		vector<branch_line> list;
		while(!in.eof())
		{
	//get input from file, use getline
			getline(in, input);
			
			branch_line bl;
	//turn string from getline into a stream so i can parse it 			
			stringstream stream;
			stream << input;
			stream >> hex >> bl.hex_num >> bl.branch_path;
			list.push_back(bl);
		}	


//compute static always taken/not taken branches
		unsigned long always_t = 0;
		unsigned long always_nt = 0;
		unsigned long i;
		for(i = 0; i < list.size(); i++)
		{
			if(list[i].branch_path == "T")
			{
				always_t++;
			}
			else
			{
				always_nt++;
			}
		}
	//turn nums into percentages, adding .5 so rounding is correct when cast to int	and print out	
		double t;
		t = (double)always_t / list.size();
		t = (int)((t * 100) + .5);
		
		double nt;
		nt = (double)always_nt / list.size();
		nt = (int)((nt * 100) + .5);
		
	//writes to file(not yet, need to change and format)
		out << dec << t << endl;
		out << nt << endl;
		
//bimodal single bit w/ history part
		unsigned long bimodal_single_count = 0;
		int table_size;
		string bi1;
		for(table_size = 3; table_size < 11; table_size++)
		{
	//create table and initialize all spots to 1(true)
			int size = pow(2,table_size);
			int table[size];
			
			for(int a = 0; a < size; a++)
			{
				table[a] = 1;
			}
			
			
	//do predictor stuff
			if(table_size != 6)
			{
				for(unsigned long j = 0; j < list.size(); j++)
				{
					int hash = list[j].hex_num % size;
				
					if(list[j].branch_path == "T" && table[hash] == 1)
					{
						bimodal_single_count++;
					}
					else if(list[j].branch_path == "T" && table[hash] == 0)
					{
						table[hash] = 1;
					}
					else if(list[j].branch_path == "NT" && table[hash] == 1)
					{
						table[hash] = 0;
					}
					else if(list[j].branch_path == "NT" && table[hash] == 0)
					{
						bimodal_single_count++;
					}
				}
	//convert output to stringstream and add it to a string so i can take off the extra whitespace at the end			
				double bi_count;
				bi_count = (double)bimodal_single_count / list.size();
				bi_count = (int)((bi_count * 100) + .5);
				
				stringstream s1;
				s1 << bi_count;
				
				bi1 += (s1.str() + " ");
				
				bimodal_single_count = 0;
			}
			
		}
	//print out 1 bit bimodal stuff
		bi1 = bi1.substr(0, bi1.length() - 1);		
		out << bi1 << endl;

//bimodal with two bit w/ history part
		
		unsigned long bimodal_double_count = 0;
		int table_size2;
		string bi2;
		for(table_size2 = 3; table_size2 < 11; table_size2++)
		{
	//create table and initialize all spots to Strongly True
			int size2 = pow(2,table_size2);
			string table2[size2];
			
			for(int b = 0; b < size2; b++)
			{
				table2[b] = "ST";
			}
			
			
	//do predictor stuff
			if(table_size2 != 6)
			{
				for(unsigned long k = 0; k < list.size(); k++)
				{
					int hash = list[k].hex_num % size2;		
					
					if(list[k].branch_path == "T" && table2[hash] == "ST")
					{
						bimodal_double_count++;
					}
					else if(list[k].branch_path == "T" && table2[hash] == "WT")
					{
						bimodal_double_count++;
						table2[hash] = "ST";
					}
					else if(list[k].branch_path == "T" && table2[hash] == "WNT")
					{
						table2[hash] = "WT";
					}
					else if(list[k].branch_path == "T" && table2[hash] == "SNT")
					{
						table2[hash] = "WNT";
					}
					else if(list[k].branch_path == "NT" && table2[hash] == "ST")
					{
						table2[hash] = "WT";
					}
					else if(list[k].branch_path == "NT" && table2[hash] == "WT")
					{
						table2[hash] = "WNT";
					}
					else if(list[k].branch_path == "NT" && table2[hash] == "WNT")
					{
						bimodal_double_count++;
						table2[hash] = "SNT";
					}
					else if(list[k].branch_path == "NT" && table2[hash] == "SNT")
					{
						bimodal_double_count++;
					}
				}
	//convert output to stringstream and add it to a string so i can take off the extra whitespace at the end		
				double bi_dub_count;
				bi_dub_count = (double)bimodal_double_count / list.size();
				bi_dub_count = (int)((bi_dub_count * 100) + .5);
				
				stringstream s2;
				s2 << bi_dub_count;
				
				bi2 += (s2.str() + " ");
				
				bimodal_double_count = 0;
			}
		}
	//print out 2 bit bimodal stuff
		bi2 = bi2.substr(0, bi2.length() - 1);
		out << bi2 << endl;


//gshare predictor

		unsigned long gshare_count = 0;
		string table3[1024];
		string gshr;
		for(int n = 2; n < 11; n++)	
		{
			int global_history = 0;
	//initialize table to strongly taken		
			for(int y = 0; y < 1024; y++)
			{
				table3[y] = "ST";
			}
		
			for(unsigned long z = 0; z < list.size(); z++)
			{
	//do predictor stuff		
				int hash = (list[z].hex_num % 1024) ^ global_history;
			
				if(list[z].branch_path == "T" && table3[hash] == "ST")
				{
					gshare_count++;
				}
				else if(list[z].branch_path == "T" && table3[hash] == "WT")
				{
					gshare_count++;
					table3[hash] = "ST";
				}
				else if(list[z].branch_path == "T" && table3[hash] == "WNT")
				{
					table3[hash] = "WT";
				}
				else if(list[z].branch_path == "T" && table3[hash] == "SNT")
				{
					table3[hash] = "WNT";
				}
				else if(list[z].branch_path == "NT" && table3[hash] == "ST")
				{
					table3[hash] = "WT";
				}
				else if(list[z].branch_path == "NT" && table3[hash] == "WT")
				{
					table3[hash] = "WNT";
				}
				else if(list[z].branch_path == "NT" && table3[hash] == "WNT")
				{
					gshare_count++;
					table3[hash] = "SNT";
				}
				else if(list[z].branch_path == "NT" && table3[hash] == "SNT")
				{
					gshare_count++;
				}				
	//check what path of branch was to add it to global history		
				int taken;
				if(list[z].branch_path == "T")
				{
					taken = 1;
				}
				else
				{
					taken = 0;
				}
				
	//put in new bit and mask out old bit			
				global_history = (global_history << 1) | taken;
				global_history = global_history & ((int)pow(2,n)-1);
				
				
			}
	//convert output to stringstream and add it to a string so i can take off the extra whitespace at the end		
			double g_count;
			g_count = (double)gshare_count / list.size();
			g_count = (int)((g_count * 100) + .5);
			
			stringstream s3;
			s3 << g_count;
			
			gshr += (s3.str() + " ");
			
			gshare_count = 0;
			
		}
	//print out gshare stuff
		gshr = gshr.substr(0, gshr.length() - 1);
		out << gshr << endl;

//tournament predictor
		
		unsigned long t_count;
		string tableT[1024];
		string tableG[1024];
		string tableB[1024];
		int g_correct = 0;
		int b_correct = 0;
		
		for(int q = 0; q < 1024; q++)
		{
			tableT[q] = "SG";
		}
		for(int g = 0; g < 1024; g++)
		{
			tableG[g] = "ST";
			tableB[g] = "ST";
		}
		
	//have gshare and bimodal run, if both right, do nothing and add 1 to count, both wrong, do nothing and dont add 1, else change state
	
		int Gglobal_history = 0;
	
		for(unsigned long v = 0; v < list.size(); v++)
		{
	//gshare part
			int Ghash = (list[v].hex_num % 1024) ^ Gglobal_history;
			
				if(list[v].branch_path == "T" && tableG[Ghash] == "ST")
				{
					g_correct = 1;
				}
				else if(list[v].branch_path == "T" && tableG[Ghash] == "WT")
				{
					g_correct = 1;
					tableG[Ghash] = "ST";
				}
				else if(list[v].branch_path == "T" && tableG[Ghash] == "WNT")
				{
					tableG[Ghash] = "WT";
				}
				else if(list[v].branch_path == "T" && tableG[Ghash] == "SNT")
				{
					tableG[Ghash] = "WNT";
				}
				else if(list[v].branch_path == "NT" && tableG[Ghash] == "ST")
				{
					tableG[Ghash] = "WT";
				}
				else if(list[v].branch_path == "NT" && tableG[Ghash] == "WT")
				{
					tableG[Ghash] = "WNT";
				}
				else if(list[v].branch_path == "NT" && tableG[Ghash] == "WNT")
				{
					g_correct = 1;
					tableG[Ghash] = "SNT";
				}
				else if(list[v].branch_path == "NT" && tableG[Ghash] == "SNT")
				{
					g_correct = 1;
				}				
	//check what path of branch was to add it to global history		
				int Gtaken;
				if(list[v].branch_path == "T")
				{
					Gtaken = 1;
				}
				else
				{
					Gtaken = 0;
				}
				
	//put in new bit and mask out old bit			
				Gglobal_history = (Gglobal_history << 1) | Gtaken;
				Gglobal_history = Gglobal_history & ((int)pow(2,10)-1);
		
		
	//bimodal part
		
			int Bhash = list[v].hex_num % 1024;		
					
			if(list[v].branch_path == "T" && tableB[Bhash] == "ST")
			{
				b_correct = 1;
			}
			else if(list[v].branch_path == "T" && tableB[Bhash] == "WT")
			{
				b_correct = 1;
				tableB[Bhash] = "ST";
			}
			else if(list[v].branch_path == "T" && tableB[Bhash] == "WNT")
			{
				tableB[Bhash] = "WT";
			}
			else if(list[v].branch_path == "T" && tableB[Bhash] == "SNT")
			{
				tableB[Bhash] = "WNT";
			}
			else if(list[v].branch_path == "NT" && tableB[Bhash] == "ST")
			{
				tableB[Bhash] = "WT";
			}
			else if(list[v].branch_path == "NT" && tableB[Bhash] == "WT")
			{
				tableB[Bhash] = "WNT";
			}
			else if(list[v].branch_path == "NT" && tableB[Bhash] == "WNT")
			{
				b_correct = 1;
				tableB[Bhash] = "SNT";
			}
			else if(list[v].branch_path == "NT" && tableB[Bhash] == "SNT")
			{
				b_correct = 1;
			}
		
		
	//tournament part	
		
			int hashT = list[v].hex_num % 1024;
			
			if(g_correct == 1 && b_correct == 1 && tableT[hashT] == "SG")
			{
				t_count++;
			}
			else if(g_correct == 1 && b_correct == 0 && tableT[hashT] == "SG")
			{
				
				t_count++;
			}
			else if(g_correct == 0 && b_correct == 1 && tableT[hashT] == "SG")
			{
				tableT[hashT] = "WG";
			}
			else if(g_correct == 0 && b_correct == 0 && tableT[hashT] == "SG")
			{
				
			}
			else if(g_correct == 1 && b_correct == 1 && tableT[hashT] == "WG")
			{
				t_count++;
			}
			else if(g_correct == 1 && b_correct == 0 && tableT[hashT] == "WG")
			{
				tableT[hashT] = "SG";
				t_count++;
			}
			else if(g_correct == 0 && b_correct == 1 && tableT[hashT] == "WG")
			{
				tableT[hashT] = "WB";
			}
			else if(g_correct == 0 && b_correct == 0 && tableT[hashT] == "WG")
			{
				
			}
			else if(g_correct == 1 && b_correct == 1 && tableT[hashT] == "WB")
			{
				t_count++;
			}
			else if(g_correct == 1 && b_correct == 0 && tableT[hashT] == "WB")
			{
				tableT[hashT] = "WG";
				
			}
			else if(g_correct == 0 && b_correct == 1 && tableT[hashT] == "WB")
			{
				tableT[hashT] = "SB";
				t_count++;
			}
			else if(g_correct == 0 && b_correct == 0 && tableT[hashT] == "WB")
			{
				
			}
			else if(g_correct == 1 && b_correct == 1 && tableT[hashT] == "SB")
			{
				t_count++;
			}
			else if(g_correct == 1 && b_correct == 0 && tableT[hashT] == "SB")
			{
				tableT[hashT] = "WB";
				
			}
			else if(g_correct == 0 && b_correct == 1 && tableT[hashT] == "SB")
			{
				t_count++;
			}
			else if(g_correct == 0 && b_correct == 0 && tableT[hashT] == "SB")
			{
				
			}		
			
			
			g_correct = 0;
			b_correct = 0;
		}
	//print out results of tournament predictor	
		double t_cnt;
		t_cnt = (double)t_count / list.size();
		t_cnt = (int)((t_cnt * 100) + .5);
		
		out << t_cnt << endl;
	}
}
