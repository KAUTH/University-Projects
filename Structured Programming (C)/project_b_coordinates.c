#include <stdio.h>
#define N 10
int main (){
int np; 			/*np einai ta number of points */
int n,i,j;          /*n bohthaei sthn arithmish twn shmeiwn */
for( ; ; ){
	printf("Enter the number of points you wish to examine, maximum ten.\n");
	scanf("%d",&np);
	if (np>N){
		printf("You exceeded the maximum number of points.\n");
		continue;}
	else {
	break;
	}
}  
/*Mexri auto to kommati kalyptw to b) ths shmeiwshs apo thn ekfwnhsh */
float a[N];  	/*tetmhmenes */
float b[N];  	/*tetagmenes */
float f[N][N];  /*apostaseis sto tetragwno */
float ppmin;		/*point pmin */
float maxval=f[0][1];
int maxpos1;  
int maxpos2;                   
for(i=0;i<np;i++){
	n=i+1;
	printf("Enter the x coordinate of # %d point.\n",n);
	scanf("%f",&a[i]);
	printf("Enter the y coordinate of # %d point.\n",n);		/*Prepei na lambanei kai to Pmin */
	scanf("%f",&b[i]);                   
}
printf("Enter Pmin.\n");
scanf("%f",&ppmin);

for(i=0;i<np;i++){
	for(j=0;j<np;j++){
		if (i<j)
	    	f[i][j]=(a[i]-a[j])*(a[i]-a[j])+(b[i]-b[j])*(b[i]-b[j]);                                        
	}
}
maxpos1=0;
maxpos2=0;
for(i=0;i<np;i++){
	for(j=0;j<np;j++){
			if (i<j){
	    		if (f[i][j]>maxval){
					maxval=f[i][j];
					maxpos1=i;
					maxpos2=j; 
			}
		}
	}
}


int w;        /*elegxos gia perissotera apo ena max*/
int tmaxpos1[N];
int tmaxpos2[N];
w=0;
tmaxpos1[w]=maxpos1;
tmaxpos2[w]=maxpos2;
for(i=0;i<np;i++){
	for(j=0;j<np;j++){
		if (i<j){
	   		if (f[i][j]==maxval){
				tmaxpos1[w]=i;
				tmaxpos2[w]=j; 
				w++;
			}
		}
	}
}

int y=0;
for(y=0;y<w;y++){
printf("For these, central station, coordinates the data is:\n");
float xc;
float yc;
xc=(a[tmaxpos1[y]]+a[tmaxpos2[y]])/2;
yc=(b[tmaxpos1[y]]+b[tmaxpos2[y]])/2;
printf("The coordinates of the central station are x=%f and y=%f.\n",xc,yc);


float g[N];/*pinakas gia thn apostash sto tetragwno apo ton kentriko stathmo */
for (i=0;i<np;i++){
	g[i]=((xc-a[i])*(xc-a[i])+(yc-b[i])*(yc-b[i]));
}

float maxdist;		/*maxdist sto tetragwno apo kentriko gia na brw to pmin */
maxdist=g[0];
for(i=0;i<np;i++){
	if (g[i]>maxdist){
		maxdist=g[i]; 
		}
	}
float pmin;
pmin=maxdist*ppmin;
printf("The minimum P of the central station is %f.\n",pmin);

float p[N];
int k;
for (i=0;i<np;i++){ 
k=i+1;  /*bohthaei sthn arithmhsh */
p[i]=pmin/g[i];
printf("The P for # %d point is %f.\n",k,p[i]);
}
}

return 0;
}
