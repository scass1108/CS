#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <assert.h>
#include <semaphore.h>

int turn;
int numThreads;
int numTurns;

pthread_mutex_t lock;

typedef struct stuff
{
	int me;
	int counter;
	int next;
}stuff;

void* doTurns(void* arg)
{
	
	stuff *s = (stuff*)(arg);
	while(s->counter < numTurns)
	{
		
		s->counter++;
		//lock this		
		turn = s->next;
		
		printf("thread %d, count = %d\n", s->me, s->counter);
	}
}
int main(int argc, char* argv[])
{
	if(argc != 3)
	{
		printf("bad input, try again :P");
	}
	else
	{
		numThreads = atoi(argv[1]);
		numTurns = atoi(argv[2]);
	}
	pthread_t *threads = calloc(sizeof(pthread_t), numThreads);
	stuff *stf = calloc(sizeof(stuff), numThreads);
	for(int j = 0; j < numThreads; j++)
	{
		stf[j].me = j;
		if(j < numThreads)
		{
			stf[j].next = j+1;
		}
		if(j == numThreads - 1)
		{
			stf[j].next = 0;
		}
		stf[j].counter = 0;
	}
	int ret;
	pthread_mutex_init(&lock, NULL);
	for(int i = 0; i < numThreads; i++)
	{
		
		ret = pthread_create(&threads[i], NULL, doTurns,(void*)(&stf[i]));
		if(ret != 0)
		{
			printf("error\n");
			exit(1);
		}
	}
	for(int v = 0; v < numThreads; v++)
	{
		ret = pthread_join(threads[v], NULL);
		assert(!ret);
	}
	pthread_mutex_destroy(&lock);
}

