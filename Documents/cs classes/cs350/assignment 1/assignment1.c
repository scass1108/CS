#include <stdio.h>
#include<stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
	char c;
	char line[150];
	
	while(1) 
	{
		printf("cs350sh> ");
		
		fgets(line, 150, stdin);
		int len = strlen(line);
		line[len -1] = '\0';

		if(strcmp(line,"") == 0)
		{
			
		}
		else if(strcmp(line, "exit") == 0)
		{
			exit(1);
		}
		else
		{		
			
		int len = strlen(line);
		int args_count = 0;
				
		for(int i = 0; i < len; i++)
		{
			if(line[i] == ' ')
			{
				args_count++;
			}
		}
		args_count++;

		char *args[args_count];
		char *temp = strtok(line, " ");
	
		for(int j = 0; temp != NULL; j++)
		{
			args[j] = temp;
			temp = strtok(NULL, " ");
		}
		
			
			
			pid_t pid;

			pid = fork();
			int status;
	 
			if (pid == -1) { 
				fprintf(stderr, "fork failed\n"); 
				exit(1); 
			} 
	
			if (pid == 0) 
			{ 
				//printf("This is the child\n"); 	
				if(execvp(args[0], &args[0]) != -1)
				{
					execvp(args[0], &args[0]);
				}
				else
				{
					printf("not a real command\n");
				}
			
			} 

			if (pid > 0) 
			{ 
				//printf("This is parent.\n"); 
				waitpid(pid, &status, 0);
			
			}
			
		}
	}
	return 0;
}


