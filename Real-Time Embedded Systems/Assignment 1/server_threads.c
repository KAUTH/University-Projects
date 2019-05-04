/* A simple server in the internet domain using TCP
   The port number is passed as an argument 
    
   Rensselaer Polytechnic Institute (RPI)
*/
 
#include <stdio.h>
#include <sys/types.h> 
#include <sys/socket.h>
#include <netinet/in.h>
#include<pthread.h> //for threading , link with lpthread
 
void error(char *msg)
{
  perror(msg);
  exit(1);
}
 
void *connection_handler(void *socket_desc);
 
int main(int argc, char *argv[])
{
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
   
  portno = atoi(argv[1]);                           // first arguement, PORT NUMBER
   
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
 * This will handle connection for each client
 * */
void *connection_handler(void *socket_desc)
{
  char buffer[256];  
 
  //Get the socket descriptor
  int sock = *(int*)socket_desc;
  int n; 
      
  bzero(buffer,256);
   
  /*---- Read the message from the client into the buffer ----*/
  n = read(sock,buffer,255);
   
   
  if (n < 0) error("ERROR reading from socket");
   
  printf("Here is the message: %s\n",buffer);
   
  /*---- Send message to the socket of the incoming connection ----*/
  n = write(sock,"I got your message",18);
   
  if (n < 0) error("ERROR writing to socket");
          
    return 0;
} 
