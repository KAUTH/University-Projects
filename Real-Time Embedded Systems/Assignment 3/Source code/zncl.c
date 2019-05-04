/* ERGASIA 3, REAL TIME EMBEDDED SYSTEMS
   AEM 8677
   PAPADOPOULOS KONSTANTINOS
   ---
   Client program to communicate with the ZSUN server.
   Sends the address of the sender (in all cases).
   Sends the address of the receiver of a message, along with the short message (in case of SMS sending).
   The first client has an administrative role and must have the address CL00.
   ---
*/
 
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h> 
 
void error(char *msg)
{
  perror(msg);
  exit(0);
}
 
int main(int argc, char *argv[])
{
  int sockfd, portno, n;
  struct sockaddr_in serv_addr;
  struct hostent *server;
  char buffer[256];
   
  if (argc < 4) {
    fprintf(stderr,"[usage %s] 1) hostname 2) port 3) CLXX, XX number from 01-99 (initial setup by client admin...)\n", argv[0]);
    exit(0);
  }
   
  portno = atoi(argv[2]);    // second arguement, PORT NUMBER
   
   /*---- Create the socket. The three arguments are: ----*/
  /* 1) Internet domain or Address Family (AF_INET) 2) Stream socket 3) Default protocol (TCP in this case) */
  sockfd = socket(AF_INET, SOCK_STREAM, 0);
   
   
  /* The correct thing to do is:
     
     AF_INET -> struct sockaddr_in.sin_family 
     PF_INET -> call to socket() for domain name
      
     But practically speaking, you can use AF_INET everywhere.
      
   */
   
  if (sockfd < 0) 
    error("ERROR opening socket");
   
  server = gethostbyname(argv[1]);    // first arguement, IP ADDRESS
   
  if (server == NULL) {
    fprintf(stderr,"ERROR, no such host\n");
    exit(0);
  }
   
   /*---- Configure settings of the server address struct ----*/
   
  /* Set all bits of the struct to 0 */
  bzero((char *) &serv_addr, sizeof(serv_addr));
   
  /* Address family = Internet */
  serv_addr.sin_family = AF_INET;
   
  /* Set IP address 
   void bcopy(const void *src, void *dest, size_t n);
   The bcopy() function copies n bytes from src to dest.  The result is
   correct, even when both areas overlap.
  */
  bcopy((char *)server->h_addr, 
        (char *)&serv_addr.sin_addr.s_addr,
        server->h_length);
   
  /* Set port number, using htons function to use proper byte order */
  serv_addr.sin_port = htons(portno);
   
  /*---- Connect the socket to the server using the address struct ----*/
  if (connect(sockfd,(struct sockaddr *)&serv_addr,sizeof(serv_addr)) < 0) 
    error("ERROR connecting");

  
  	/*---- Send the address from the client to the server ----*/
  	n = send(sockfd,argv[3],strlen(argv[3]),0); // write

	/*---- --- ----*/
  
  while(1){
    bzero(buffer,256);
    /*---- Read the message from the server into the buffer ----*/
    n = recv(sockfd,buffer,255,0);
  
    if (n < 0) 
      error("ERROR reading from socket");

    /*---- Print the received message ----*/
    printf("%s\n",buffer);


    /*---- Send the message from the client (buffer) to the server ----*/
    printf("Please enter the receiver and the message (PRESS ENTER TO REFRESH): \n[Example: CL01-HELLO] \n");
    bzero(buffer,256);
    fgets(buffer,255,stdin);
    n = send(sockfd,buffer,strlen(buffer),0);
  
    if (n < 0) 
      error("ERROR writing to socket");
  }

  return 0;

}