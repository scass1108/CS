#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <iostream>
#include <fstream>
using namespace std;

int main()
{
	ofstream out;
	out.open("songinfo.txt");
	
	if(!out.good())
	{
		out << "error, couldnt open  output file, try again" << endl;
		exit(2);
	}
		
	string line;
	for(int i = 1; i <= 200; i++)
	{
		out << "name" << i << " " << "album" << i << " " << "singer" << i << " " << 2 << endl;
	}
	out.close();
}
