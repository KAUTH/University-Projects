/* ERGASIA 3, REAL TIME EMBEDDED SYSTEMS
   AEM 8677
   PAPADOPOULOS KONSTANTINOS
   ---
   ZSUN server program (with sleep() to save energy) to communicate with the ZSUN clients.
   Receives clients' addresses and messages.
   Sends messages from one client to another.
   ---
*/

#include <stdio.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include <pthread.h> 	//for threading , link with lpthread
#include <time.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>

void error(char *msg)
{
  perror(msg);
  exit(1);
}

void *connection_handler(void *socket_desc);

int sum[99]; // each line of the array informs us about how many messages each client has at every moment
	     // line 1 corresponds to CL01, line 2 to CL02 and so on...

struct Box { // struct that keeps the message-"mail" and whether it exists or not-"exist"
 int exist;
 char mail[30];
};
 
struct Box mailbox[99][99]; // each line holds the messages for each client and each column the clients where they are coming from

char mestot[99];

int main(int argc, char *argv[])
{

  for(int i=0; i<99; i++){	// initializing original tables that keep track of messages
  	sum[i] = 0;
	mestot[i] = "";
  	for(int y=0; y<99; y++){
  		mailbox[i][y].exist = 0;
		strcpy(mailbox[i][y].mail, "");
  	}
  } 
  
  int realprt;
  double fract;
  struct timespec delay={realprt,fract};	// structure holding an interval broken down into seconds and nanoseconds.
  delay.tv_sec = 1;
  delay.tv_nsec = 0;
  int sockfd, newsockfd, portno, clilen;
  char buffer[256];
  struct sockaddr_in serv_addr, cli_addr;
  int n;
  
  if (argc < 2) {
    fprintf(stderr,"ERROR, no port provided\n");
    exit(1);
  }
  
  /*---- Create the socket. The three arguments are: ----*/
  /* 1) Internet domain or Address Family (AF_INET) 2) Stream socket 3) Default protocol (TCP in this case) */
  sockfd = socket(AF_INET, SOCK_STREAM, 0);
  
  if (sockfd < 0) 
    error("ERROR opening socket");

  /* Set all bits of the struct to 0 */
  bzero((char *) &serv_addr, sizeof(serv_addr));
  
  portno = atoi(argv[1]);			// first arguement, PORT NUMBER
  
  /*---- Configure settings of the server address struct ----*/
  
  /* Address family = Internet */
  serv_addr.sin_family = AF_INET;
  
  /* Set IP address to localhost */
  serv_addr.sin_addr.s_addr = INADDR_ANY;
  
  /* Set port number, using htons function to use proper byte order */
  serv_addr.sin_port = htons(portno);
  
  /*---- Bind the address struct to the socket ----*/
  if (bind(sockfd, (struct sockaddr *) &serv_addr,
           sizeof(serv_addr)) < 0) 
    error("ERROR on binding");

  /*---- Listen on the socket, with 5 max connection requests queued ----*/	
  listen(sockfd,5);
  
  /*---- Accept call creates a new socket for the incoming connection ----*/
  clilen = sizeof(cli_addr);
  
  pthread_t thread_id;
  //newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen);
  
  while(newsockfd = accept(sockfd, (struct sockaddr *) &cli_addr, &clilen) ) {
	nanosleep(&delay,NULL);	// sleeps to minimize power consumption
	puts("Connection accepted");
         
        if( pthread_create( &thread_id , NULL ,  connection_handler , (void*) &newsockfd) < 0)
        {
            perror("could not create thread");
            return 1;
        }
         
        //Now join the thread , so that we dont terminate before the thread
        //pthread_join( thread_id , NULL);
        puts("Handler assigned");
  }
  
  /////////////////////////////
  if (newsockfd < 0) 
    error("ERROR on accept");
///////////////////////////////
  
  return 0; 
}

/*
 * This will concatenate the two strings
 * */
char* concat(const char *s1, const char *s2){
	char *result = malloc(strlen(s1) + strlen(s2) + 1); // +1 for the null-terminator
	strcpy(result, s1);
	strcat(result, s2);
	return result;
}


/*
 * This will handle connection for each client
 * */
void *connection_handler(void *socket_desc)
{
  char buffer[256];

  int realprt;
  double fract;
  struct timespec delay={realprt,fract};			// structure holding an interval broken down into seconds and nanoseconds.
  delay.tv_sec = 1;
  delay.tv_nsec = 0;  

  //Get the socket descriptor
  int sock = *(int*)socket_desc;
  int n;
  char s_address[4]; // sender address
  char r_address[4]; // receiver address

  bzero(buffer,256);
  
  /*---- Read the client's address from the client into the buffer ----*/
  char new1[2], new2[2], new1s[2], new2s[2];
  n = recv(sock,buffer,255,0);
  strcpy(s_address, buffer);
  printf("The sender is: %s\n", s_address);
  new1[0] = s_address[2];  // parsing string address and converting to int
  new2[0] = s_address[3];
  strcpy(new1s, new1);
  strcpy(new2s, new2);
  char* numstr1 = concat(new1s, new2s);
  int num = atoi(numstr1);
 
  
   
  while(1){
  // SEND POSSIBLE RESPONSES
  nanosleep(&delay,NULL);	// sleeps to minimize power consumption
  if (sum[num-1] == 0){		// use nanosleep instead of clock_nanosleep, because of compiling errors
  	n = send(sock,"You have no new messages...",27,0); // write
  }
  else{
  	for(int i = 0; i<99; i++){
		//bzero(buffer,256);
		if (mailbox[num-1][i].exist == 1 && i!=(num-1)){
			strcat(mestot,mailbox[num-1][i].mail);
			//n = send(sock,mailbox[num-1][i].mail,30,0); // write
			mailbox[num-1][i].exist = 0;
			sum[num-1]--;
		}
		else continue;  	
	}
        n = send(sock,mestot,strlen(mestot),0);
	for(int i=0; i<99; i++){			// initializing table that stores messages
		strcpy(mestot, "");			// mestot[i] = "";
	}
  }
  
  if (n < 0) error("ERROR writing to socket");

  /*---- Read the message from the client into the buffer ----*/
  n = recv(sock,buffer,255,0);
  
  if (n < 0) error("ERROR reading from socket");

  strcpy(r_address, buffer);
  int response;
  char newa[2], newb[2], newas[2], newbs[2];
  newa[0] = r_address[2];  // parsing string address and converting to int
  newb[0] = r_address[3];
  strcpy(newas, newa);
  strcpy(newbs, newb);
  char* numstr2 = concat(newas, newbs);
  int rnum = atoi(numstr2);

  if (r_address[0] == 'C'){
	
	strcpy(mailbox[rnum-1][num-1].mail, r_address); // saves the message to the mailbox
	mailbox[rnum-1][num-1].exist = 1;
	sum[rnum-1]++;
  	response = 1;
  }
  else {response = 0;}

  if (response == 1){
  	n = send(sock,"Your message has been sent!",35,0);	// write
  } 
  else{
  	n = send(sock,"No new messages...",18,0);		// write
  } 
  
  printf("Here is the message: %s\n\n",buffer);
  

  }
         
    return 0;
} 



