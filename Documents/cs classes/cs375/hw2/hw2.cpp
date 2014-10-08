#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <algorithm>
#include <sstream>
#include <cmath>
#include <time.h>

using namespace std;

struct Point
{
	int x;
	int y;
	bool operator <(const Point &p1)const
	{
		return x < p1.x;
	}
};

int main(int argc, char *argv[])
{

	vector<Point> points;

	//get args:
	if(argc != 3)
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
		
		
		while(!input.eof())
		{			
			getline(input, input_num);
			
			//convert numbers in file to ints
			stringstream stream;
			stringstream stream2;
			
			string xNum;
			string yNum;
			size_t found;
			found = input_num.find(" ");
			int space = int(found);
			xNum = input_num.substr(0, space);
			yNum = input_num.substr(space+1);
			
			//convert x val strings to ints
			stream << xNum;
			int x;
			stream >> x;
			
			//convert y val string to ints
			stream2 << yNum;
			int y;
			stream2 >> y;	
			
			//put xs and ys into an array
			Point p;
			p.x = x;
			p.y = y;
			points.push_back(p);		
		}
	
	
	time_t start,end;
	double time1;
	time_t start2,end2;
	double time2;
	
	time(&start);
	
	int pnt1 = 0;
	int pnt2 = 1;
	int diffbtwn;
	int diffbtwn2;
	diffbtwn = sqrt(pow((points[0].x - points[1].x),2) + pow((points[0].y - points[1].y),2));
	//brute force
	for(int i = 0; i < points.size(); i++)
	{
		for(int j = 0; j < points.size(); j++)
		{
			if(i != j)
			{
				diffbtwn2 = sqrt(pow((points[i].x - points[j].x),2) + pow((points[i].y - points[j].y),2));
				if(diffbtwn2 < diffbtwn)
				{
					diffbtwn = diffbtwn2;
					pnt1 = i;
					pnt2 = j;
				}
			}
		}
	}
	
	time(&end);
	time1 = difftime(end,start);

	cout << "brute force took " << time1 << " seconds" << endl; 	
	output << diffbtwn << endl;
/*	
	time(&start2);
	
	//sort with stl lib
	sort(points.begin(),points.end(),myobj);
	
	dAndC(points);
	
	
	time(&end2);
	time2 = difftime(end2,start2);
	

	}
	
	void dAndC(vector<Point> pList)
	{
		if(pList.size() <= 3)
		{
			//brute force
		}
		else
		{
			int left = pList.size()/2;
			int right = pList.size()/2 + pList.size() %2;
			vector<Point> pLeft;
			vector<Point> pRight;
			for(int i = 0; i < left; i++)
			{
				pLeft[i] = pList[i];
			}
			for(int j = 0; j < right; j++)
			{
				pRight[j] = pList[i + right]
			}
		}
	}
	*/
}
