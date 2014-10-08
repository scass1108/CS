#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <iostream>
#include <netdb.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <strings.h>
using namespace std;
//cli = 2 args, serv addr, portnum
int main(int argc, char* argv[])
{
	string line;
	if(argc != 3)
	{
		cout << "wrong num of args" << endl;
		exit(2);
	}
	else
	{

		int  sockfd, n; 
        	char recvline[100];
       		struct sockaddr_in servaddr;
		
		//create socket
		sockfd = socket(AF_INET,SOCK_STREAM, 0);
		
       		servaddr.sin_family = AF_INET;
       		servaddr.sin_port = htons(atoi(argv[2]));
       		
       		//convert hostname string to binary
        	struct hostent *hptr;
        	hptr = gethostbyname(argv[1]);
        	memcpy(&servaddr.sin_addr, hptr->h_addr, hptr->h_length);
	
//cout << "attempting to connect" << endl;

		/* Connect to the server */
     		connect(sockfd, (struct sockaddr *)&servaddr, sizeof(servaddr));
     			
     		
     		
//cout << "connected" << endl;       		
		while(1) 
		{
			cout << "ftp> ";
		
			getline(cin, line);
	
			if(line.length() == 0)
			{
			
			}
			else if(strcmp(line.c_str(), "bye") == 0)
			{
				close(sockfd);
				exit(1);
			}
			else if(line.length() > 0)
			{
				//if command has l in it, send it to the server
				if(line.substr(0,1) == "l" && line.substr(0,2) != "ls")
				{
					//send string to server
					write(sockfd, (char*)line.c_str(), line.length());
				}
				//otherwise its the clients
				else
				{
					if(line.substr(0,2) == "cd")
					{	
						//cout << "using chdir" << endl;
						string line2 = line.substr(3, line.length());
						chdir(line2.c_str());
					}
					else
					{
						//cout << "using system()" << endl;
						system(line.c_str());
					}
				}
			}
		
		}
	}
}
