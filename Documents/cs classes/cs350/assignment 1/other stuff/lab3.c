#include <stdio.h>
#include<stdlib.h>
#include <sys/types.h>
#include <unistd.h>
#include <string.h>

int main(int argc, char* argv[])
{

	int pipefd[2];
	pid_t pid;

	pid = fork();
	int status;
 
 	int fd[2];
 	pipe(fd);
 
 	int plus_spot;
 	
 		for(int i = 0; i < argc; i++)
		{
			if(strcmp(argv[i], "+") == 0)
			{
				
				plus_spot = i;
				
				
			}
	
		}
		
	if(plus_spot > 0)
	{
	 	char* part1[plus_spot +1];
	 	char* part2[(argc - plus_spot) +1];
	 	
	 	for(int j = 0; j < plus_spot; j++)
	 	{
	 		part1[j] = argv[j];
	 	}
	 	for(int k = 0; k < argc - plus_spot; k++)
	 	{
	 		part2[k] = argv[k+plus_spot];
	 
	 	} 	
 
	if (pid == -1) { 
		fprintf(stderr, "fork failed\n"); 
		exit(1); 
	} 
	
	if (pid == 0) { 
		//printf("This is the child\n");
		
		//clone read end of pipe
		dup2(fd[0], 0);
		
		//close write end
		close(pipefd[1]);
		
		//exec prog2
		execvp(part2[1], &part2[1]);
		
	}

	if (pid > 0) { 
		//printf("This is parent.\n"); 
		
		//clone write end
		dup2(fd[1], 1);
		
		//close read end
		close(pipefd[0]);
		
		//exec prog1
		execvp(part1[1], &part1[1]);
	} 
}
}
