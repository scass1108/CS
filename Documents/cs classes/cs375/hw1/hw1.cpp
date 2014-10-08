#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <cmath>

using namespace std;

int main(int argc, char *argv[])
{
	//get args:
	if(argc < 3)
	{
		cout << "wrong number of args" << endl;
		exit(1);
	}
	else
	{
		ifstream input(argv[1]);
		ofstream output(argv[2]);
		
		if(!input.good() || !output.good())
		{
			cout << "error, unable to access one of the files" << endl;
			exit(1);
		}		
			
		//get input:
		string input_num;
		vector<int> numsList;
		
		while(!input.eof())
		{			
			getline(input, input_num);
			
			//convert number in file to int
			stringstream stream;
			stream << input_num;
			int n;
			stream >> n;	
			
			numsList.push_back(n);			
		}
		//add nums to vector
		numsList.pop_back();
				
		//sort list:
		sort(numsList.begin(), numsList.end());			
		
		//compare 2 keys and find diff
		int target;

		//pick 2 keys and do binary search to check for difference in the vector				
		
		for(int n = 0; n < numsList.size(); n++)
		{
			for(int p = 0; p < numsList.size(); p++)
			{
			//binary search
				if(numsList[n] != numsList[p])
				{				
					target = abs(numsList[n]-numsList[p]);	
					
					int min = 0;
					int max = numsList.size();
					int mid;
				
					while(max >= min)
					{
						mid = (max + min)/ 2;
						
						if(numsList[mid] < target)
						{
							min = mid + 1;	
												
						}
						else if(numsList[mid] > target)
						{
							max = mid - 1;		
											
						} 
						else
						{
							//mid is target so write to file and exit
							output << target << endl;
							
							exit(0);
							
						}
					}
				}
			}
		}
//end of for		
		
		//if no num found dont write anything to output file
		exit(2);
	}
	return 0;
}
