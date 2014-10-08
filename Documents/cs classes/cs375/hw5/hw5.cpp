#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <math.h>

using namespace std;


struct photo{

	int p1;
	int p2;
	int weight;
};


int main(int argc, char* argv[])
{
	if(argc != 2)
	{
		cout << "need input file" << endl;
		exit(2);
	}
	else
	{
		ifstream in(argv[1]);
		if(!in.good())
		{
			cout << "error, couldnt openinput file, try again" << endl;
			exit(2);
		}
		vector<photo> list;
		string input;
		while(!in.eof())
		{
			getline(in, input);
			
			photo p;
			
			stringstream stream;
			stream << input;
			
			stream >> p.p1 >> p.p2 >> p.weight;
			list.push_back(p);
		}
		
		for(int i = 0; i < list.size() - 1; i++)
		{
			cout << list[i].p1 << " " << list[i].p2 << " " << list[i].weight << endl;
		}
			
			
			
			
			
			
			
			
	}
}

