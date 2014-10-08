#include <stdio.h>
#include<stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char* argv[])
{

	if(argc >= 1)
	{
	
	
		pid_t pid;

		pid = fork();
		int status;
 
		if (pid == -1) { 
			fprintf(stderr, "fork failed\n"); 
			exit(1); 
		} 
	
		if (pid == 0) { 
			printf("This is the child\n");
			wait(1); 	
			execvp(argv[1], &argv[1]);
			
		} 

		if (pid > 0) { 
			printf("This is parent.\n"); 
			
			
		} 
	
	}
}
