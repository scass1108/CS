#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
	char c;
	printf("\n[MY_SHELL ] ");
	while(c != 'q') {
		c = getchar();
		if(c == '\n')
			printf("cs350sh> ");
	}
	printf("\n");
	return 0;
}

