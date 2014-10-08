#include <stdio.h>
#include<stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char* argv[])
{
//while(strcmp(argv[1],"exit") != 0)
	if(argc == 1)
	{
		//start prog and put "cs350sh in front of everything
	}
	else if(argc == 2)
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
			execvp(argv[1], &argv[1]);
			
		} 

		if (pid > 0) { 
			//printf("This is parent.\n"); 
			waitpid(pid, &status, 0);
			
		} 
	}
	else if(argc >= 3)
	{
	
		//parse line for &(background process), < or > for file redirection, or | for piping
	
		int apm_count = 0;
		int pipe_count = 0;
		int redirect_in_count = 0;
		int redirect_out_count = 0;
		int filename_present = 0;
	
	
		for(int i = 1; i < argc; i++)
		{
			if(strcmp(argv[i], "&") == 0)
			{
				apm_count++;
			}
			if(strcmp(argv[i], "|"))
			{
				pipe_count++;
			}
			if(strcmp(argv[i], "<"))
			{
				redirect_in_count++;
			}
			if(strcmp(argv[i], ">"))
			{
				redirect_out_count++;
			}
			if(strcmp(argv[i], ".txt"))
			{
				filename_present = i;
			}
		} 
	
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
		else if(apm_count == 0 && pipe_count == 0 && redirect_in_count == 0 &&redirect_out_count == 0)
		{
			execvp(argv[1], &argv[1]);
		}
		else
		{
			printf("bad input");
		}
	}
}
