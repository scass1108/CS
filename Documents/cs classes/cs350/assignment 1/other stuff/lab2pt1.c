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
		else
		{
			printf("bad input");
		}
	}
}
