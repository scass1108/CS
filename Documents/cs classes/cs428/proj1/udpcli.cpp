#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h> /* memset() */
#include <sys/time.h> /* select() */ 
#include <stdio.h>
#include <math.h>

#define REMOTE_SERVER_PORT 1500
#define MY_PORT 40000  //or anything
#define MAX_MSG 1000000

int main(int argc, char *argv[]) {
  
  int sd, rc, i;
  struct sockaddr_in cliAddr, remoteServAddr;
  struct hostent *h;

  /* check command line args */
  if(argc!=2) {
    printf("usage : %s <server> \n", argv[0]);
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
  
  /* bind any port */
  /*  cliAddr.sin_family = AF_INET;
  cliAddr.sin_addr.s_addr = htonl(INADDR_ANY);
  cliAddr.sin_port = htons(MY_PORT);
  
   rc = bind(sd, (struct sockaddr *) &cliAddr, sizeof(cliAddr));
  if(rc<0) {
    printf("%s: cannot bind port\n", argv[0]);
    return -1;
  }
  */  

  /* send data */
  //for(i=1;i<10;i++) {
  
    rc = sendto(sd, "message", 10002, 0, 
		(struct sockaddr *) &remoteServAddr, 
		sizeof(remoteServAddr));
//printf("pass %d \n", i);

    if(rc<0) {
      printf("%s: cannot send data, error \n",argv[0]);
      close(sd);
      return -1;
}
	
  //}
  
  return 1;

}

