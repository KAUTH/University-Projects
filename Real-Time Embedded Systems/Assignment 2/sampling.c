/*
 * ERGASIA 2, REAL TIME EMBEDDED SYSTEMS
 * AEM 8677
 * PAPADOPOULOS KONSTANTINOS
 * VERSION 5
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>

int main(int argc,char **argv)
{
  if(argc != 3)
  {
	printf("***ERROR-Insert two parameters***\n"
			"1st: number of seconds between samples (interval)\n"
			"2nd: number of samples\n");
    exit(1);
  }
  
  FILE *file;
  file= fopen("result.txt","w");	// creating a file to insert the results (real interval between timestamps)
  
  FILE *file1;
  file1= fopen("timestamps.txt","w");	// creating a file to insert the gettimeofdays() (timestamps)

  FILE *file2;
  file2= fopen("expected2real_tstamps.txt","w");	// creating a file to insert the the difference between the the expected time of 
							// sampling and the real time of sampling
  
  if (file == NULL)
  {
    printf("Error opening file!!\n");
    exit(1);
  }
  
  double sec = atof(argv[1]);		// converts the string argument str to an integer (type int)
  int samples =atoi(argv[2]);		// -''- (the same as above)
  
  struct timeval startwtime,endwtime; // this structure is used to specify when a timer should expire
  int i=1;							  // the current number of samples taken every moment
  
  /* fract = decimal part of the t multiplied by 10^9 because of timespec structure
   * realprt = secconds of delay time */
   
  double t = sec;
  double t2;
  int realprt= (int) t;
  double fract;
  fract= t - realprt;
  fract=fract*pow(10,9);					// converting to ns
  fprintf(file,"Interval, fract, realprt: %f %f %d\n",t,fract,realprt);
  
  struct timespec delay={realprt,fract};			// structure holding an interval broken down into seconds and nanoseconds.
  double *times=(double *)malloc(samples*sizeof(double));	// array to save the distance between timestamps after each sampling period
  double *timeday=(double *)malloc(samples*sizeof(double));	// array to save the timestamps after each sampling period (# = samples-1)
  
  /* Calculation of sample time*/
  fprintf(file,"calculating...\n");
  double mean=0;
  t2=t;
  realprt=(int) t2;
  fract=(t2-realprt)*pow(10,9);
  delay.tv_sec=realprt;
  delay.tv_nsec=fract;
  /* begin sampling*/
  t2=t;
  fprintf(file,"\nExecution for %d second intervals:\n",sec);
  gettimeofday(&startwtime,NULL);				// first sample
  timeday[0]= startwtime.tv_usec/1.0e6 + startwtime.tv_sec;
  
  
  while(i<samples)
  {
    clock_nanosleep(CLOCK_REALTIME,0,&delay,NULL);
    gettimeofday(&endwtime,NULL);
    times[i-1]= (double)((endwtime.tv_usec - startwtime.tv_usec)			// stores the real intervals
				/1.0e6 + endwtime.tv_sec - startwtime.tv_sec);	
    timeday[i]= endwtime.tv_usec/1.0e6 + endwtime.tv_sec;				// stores the timestamps	
	
	/* time sampling preparation for next sample*/
	mean=t-times[i-1];	// change back to i maybe
	t2=t2+mean;
	realprt=(int)t2;
	fract=(t2-realprt)*1000000000;
	delay.tv_sec=realprt;
	delay.tv_nsec=fract;
	i++;
	startwtime=endwtime;
  }
  
  double sum=0;			// total sum of time measured by our timer
  for(int y=0; y<samples; y++)
  {  
	fprintf(file1,"Sample %d: %fs\n",y+1,timeday[y]);
	sum+=times[y];
  }
  for(int y=0; y<(samples-1); y++)
  {
	fprintf(file,"Interval %d: %fs\n",y+1,times[y]);  
  }
  
  fprintf(file2,"Expected - Real sampling timestamp difference k,(timeday[0]+k*sec)-timeday[k]:\n"); 
  fprintf(file2,"Difference 1: 0\n"); 
  for(int k=1; k<(samples-1); k++)
  {
	fprintf(file2,"Difference %d: %fs\n",k+1,(timeday[0]+k*sec)-timeday[k]);  
  }
  
  fprintf(file,"Precision (sum-(samples-1)*sec): %fs\n",(sum-(samples-1)*sec));			// total precision 
  fprintf(file,"Duration of sampling (according to timestamps -> timeday[samples-1]-timeday[0]): %fs\n",timeday[samples-1]-timeday[0]);
 
}
