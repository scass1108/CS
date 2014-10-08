#include <stdio.h>
#include<stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
	if(argc >= 3)
	{
		//stdin
		if(atoi(argv[1]) == 0)
		{
			pid_t pid;

			pid = fork();
			int status;
 
			if (pid == -1) { 
				fprintf(stderr, "fork failed\n"); 
				exit(1); 
			} 
	
			if (pid == 0) { 
				//printf("This is the child\n");
				freopen(argv[2], "r", stdin);
				execvp(argv[3], &argv[3]);
			
			} 

			if (pid > 0) { 
				//printf("This is parent.\n"); 
				waitpid(pid, &status, 0);
			
			} 
		}
		
		//stdout
		else if(atoi(argv[1]) == 1)
		{
			pid_t pid;

			pid = fork();
			int status;
	 
			if (pid == -1) { 
				fprintf(stderr, "fork failed\n"); 
				exit(1); 
			} 
	
			if (pid == 0) { 
				//printf("This is the child\n"); 
				freopen(argv[2], "w", stdout);					
				execvp(argv[3], &argv[3]);
			
			} 

			if (pid > 0) { 
				//printf("This is parent.\n"); 
				waitpid(pid, &status, 0);
			
			} 
		}
		
		else if(atoi(argv[1]) != 0 || atoi(argv[0]) != 1)
		{
		
			int plusmark = -1;
			for(int i = 0; i < argc; i++)
			{
				if(argv[i] == "+")
				{
					plusmark = i;
					break;
				}
			}
			if(plusmark == -1)
			{
				printf("bad input");	
				exit(1);
			}
			else{
					
				pid_t pid;
				pid = fork();
				int status;
				
				if (pid == -1) { 
				fprintf(stderr, "fork failed\n"); 
				exit(1); 
				} 

				if (pid == 0) { 
					//printf("This is the child\n");
					freopen(argv[2], "r", stdin);
					execvp(argv[3], &argv[3]);
		
				} 
					if (pid > 0) { 
					//printf("This is parent.\n"); 
					waitpid(pid, &status, 0);
		
				} 
			}
		}
	}
}
