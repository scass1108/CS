#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <stack>


using namespace std;

int main(int argc, char *argv[])
{

//initialize 2d array
	int places [11][11];
	
//set array distances to 100000(instead of infinity)	
	for(int n = 0; n < 11; n++)
	{
		for(int l = 0; l < 11; l++)
		{
			places[n][l] = 100000;
		}
		
	}

//set distance to itself as 0	
	for(int m = 0; m < 11; m++)
	{
		places [m][m] = 0;
	}

//hardcode distances between all points
	places[0][1] = 180;
	places[0][3] = 100;
	places[1][0] = 180;
	places[1][2] = 160;
	places[1][5] = 65;
	places[2][1] = 160;
	places[2][6] = 130;
	places[3][0] = 100;
	places[3][4] = 60;
	places[4][3] = 60;
	places[4][5] = 70;
	places[5][2] = 65;
	places[5][4] = 70;
	places[5][6] = 100;
	places[5][7] = 70;
	places[6][2] = 130;
	places[6][5] = 100;
	places[6][10] = 140;
	places[7][5] = 70;
	places[7][8] = 60;
	places[7][10] = 180;
	places[8][7] = 60;
	places[8][9] = 65;
	places[8][10] = 100;
	places[9][8] = 65;
	places[9][10] = 70;
	places[10][6] = 140;
	places[10][7] = 180;
	places[10][8] = 100;
	places[10][9]= 70;

	
//floyds algorithm for 2d matrix

//initialize D to orig matrix
	int D[11][11];
	
	for(int a = 0; a < 11; a++)
	{
		for(int b = 0; b < 11; b++)
		{
			D[a][b]= places[a][b];
		}
	}
	
//extra matrix
	int D_1[11][11];

//initialize P to 0
	int P[11][11];
	for(int z = 0; z <11; z++)
	{
		for(int y = 0; y < 11; y++)
		{
			P[z][y] = 0;
		}
	}
//floyds from slide 54 of dynam prog lec
	for(int k = 0; k < 11; k++)
	{
		for(int i = 0; i < 11; i++)
		{
			for(int j = 0; j < 11; j++)
			{
				if(D[i][j] > (D[i][k] + D[k][j]))
				{
					D_1[i][j] = (D[i][k] + D[k][j]);
					P[i][j] = k;
				}
				else
				{
					D_1[i][j] = D[i][j];
				}
			}
		}
		
		
		for(int c = 0; c < 11; c++)
		{
			for(int d = 0; d < 11; d++)
			{
				D[c][d] = D_1[c][d];
			}
		}	
	}
	
//find path from slide 65 of dynam prog lec
	int i = 0;
	int j = 10;

	stack <int> s;
	s.push(j);
	int k = j;
	while(P[i][k] != i)
	{
		s.push(P[i][k]);
		k = P[i][k];
	}
	s.push(i);
		
	string cities[11];
	cities[0] = "Toronto";
	cities[1] = "Kingston";
	cities[2] = "Montreal";
	cities[3] = "Buffalo";
	cities[4] = "Rochester";
	cities[5] = "Syracuse";
	cities[6] = "Albany";
	cities[7] = "Binghamton";
	cities[8] = "Scranton";
	cities[9] = "Allentown";
	cities[10] = "New York City";
		
	cout << cities[s.top()];
	s.pop();
	
	while(!s.empty())
	{
		cout << " to " << cities[s.top()];
		s.pop();
	}
	cout << endl;
	
	
	
	
	
}
