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
#include <math.h>
#include <pthread.h>
#include <time.h>
using namspace std;
int main(int argc, char* argv[])
{
int sd, rc, i;
  struct sockaddr_in cliAddr, remoteServAddr;
  struct hostent *h;

  /* check command line args */
  if(argc<3) {
    printf("usage : %s <server> <data1> ... <dataN> \n", argv[0]);
  return -1;
}

  /* get server IP address (no check if input is IP address or DNS name */
  h = gethostbyname(argv[1]);
  if(h==NULL) {
    printf("%s: unknown host '%s' \n", argv[0], argv[1]);
  return -1;
}

  printf("%s: sending data to '%s' (IP : %s) \n", argv[0], h->h_name,
	 inet_ntoa(*(struct in_addr *)h->h_addr_list[0]));

  remoteServAddr.sin_family = h->h_addrtype;
  memcpy((char *) &remoteServAddr.sin_addr.s_addr, 
	 h->h_addr_list[0], h->h_length);
  remoteServAddr.sin_port = htons(REMOTE_SERVER_PORT);

  /* socket creation */
  sd = socket(AF_INET,SOCK_DGRAM,0);
  if(sd<0) {
    printf("%s: cannot open socket \n",argv[0]);
    return -1;
  }

/* timing for 1 minute?

	time_t timer;
	timer = time(NULL) + 60;
	
	while(timer < time(NULL)); 

pthread_t *threads = calloc(sizeof(pthread_t), 2);



*/


}
/*retail pays 8 per case and bulk is 6
# of cases requested for indiv = 1-20
for bulk = 10-100
send message to appropriate sales socket
if order takes > 250ms get back .5 of order cost
if order takes > 500ms get back .9 of order cost

buyer will send ack of cases & if delay was high, request money back
after order fulfilled buyer waits for 100 + 5(order size) before sending next order

for part 1:
 2 threads, 1 for bulk and 1 for individ
//need to get val of company after 1 minute = $ made+5*cases of ketchup
//#of total cases sold & orders for bulk and individ
% of late & very late orders
*/
