#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#include <assert.h>
#include <sys/time.h>

int turn;
int numThreads;
int numTurns;
double totalHandoverTime;

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
		
		while(turn != s->me)
		{
		}
	struct timeval tim;
        gettimeofday(&tim, NULL);
        double ta=tim.tv_sec+(tim.tv_usec/1000000.0);
        
		s->counter++;
	//	printf("thread %d, turn = %d\n", s->me, s->counter);
		turn = s->next;
		
		gettimeofday(&tim, NULL);
       		double tb=tim.tv_sec+(tim.tv_usec/1000000.0);
         	totalHandoverTime = totalHandoverTime + (tb-ta);

		
	}
}
int main(int argc, char* argv[])
{
	struct timeval tim;
        gettimeofday(&tim, NULL);
        double t1=tim.tv_sec+(tim.tv_usec/1000000.0);

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

	gettimeofday(&tim, NULL);
        double t2=tim.tv_sec+(tim.tv_usec/1000000.0);
        printf("total execution time = %.8lf \n", t2-t1);
        printf("avg handover time = %.8lf \n", totalHandoverTime/numThreads);

	return 0;
}
