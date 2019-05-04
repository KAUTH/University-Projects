#include <stdio.h>
#define N 5
int main(){
	int connection[N][N-1],weight[N][N-1],noc[N],pt[N]; 	/*noc:number of connections, pt:positioning table */
	int i,j,bgn,end;										/*bgn: beginning */
	for(i=0;i<N;i++)
		pt[i]=1;									
	for(i=0;i<N;i++){ 
		printf("Enter with how many other points #%d point is connected.\n",i);
		scanf("%d",&noc[i]);
		printf("Enter how the #%d point connects with the others, in ascending order.\n",i);
		for(j=0;j<noc[i];j++)
			scanf("%d",&connection[i][j]);
		printf("Enter with what weight #%d point connects with the others, in ascending order.\n",i);
		for(j=0;j<noc[i];j++)
			scanf("%d",&weight[i][j]);
		}
	printf("Enter the destination of the points(beginning and end).\n");
	scanf("%d",&bgn);
	scanf("%d",&end);
	
	for(i=0;i<3;i++){
	for(j=0;j<2;j++)
			printf("#1%d\n",connection[i][j]);
		}
		for(i=0;i<3;i++){
	for(j=0;j<2;j++)
			printf("#2%d\n",weight[i][j]);
		}
	
	
	int minweight=0,totalweight=0,mindest[N];
	int k=0,l=bgn,m;
	mindest[0]=bgn;
	pt[bgn]=0;
	
	
	for ( ;l!=end ; ){
			for (j=0;j<noc[l];j++){
				printf("eftasa eksw edw %d %d\n",mindest[k],minweight);
				printf("##1 %d\n",k);
				if (weight[l][j+1]<=weight[l][j] && pt[connection[l][j+1]]!=0 ){
					mindest[k+1]=connection[l][j+1];
					minweight=weight[l][j+1];
					printf("eftasa ws edw %d %d\n",mindest[k],minweight);
					printf("eftasa ws edw, totalweight= %d\n",totalweight);
					m=j+1;
					printf("##2 %d\n",k);
					break;
				}
				else if(pt[connection[l][j]]!=0){
					mindest[k+1]=connection[l][j];
					minweight=weight[l][j];
					m=j;
					printf("eftasa ws edw deuterh ep %d %d\n",mindest[k],minweight);
					printf("eftasa ws edw, totalweight= %d\n",totalweight);
					printf("##3 %d\n",k);
					break;
				}			
		}
		k++;
		l=connection[l][m];
		pt[connection[l][m]]=0;
		totalweight+=minweight;
		printf("##4 %d\n",k);
		printf("to l einai %d\n",l);	
		printf("eftasa ws edw, totalweight= %d\n",totalweight);	
			
		
	}
	printf("eftasa ws edw, totalweight= %d\n",totalweight);
	printf("##5 %d\n",k);	
	if (l!=end)
		printf("The destination could not be reached.\n");
	else{
		printf("The minimum destination is:\n");
		for(i=0;i<k+1;i++)
			printf("%d\n",mindest[i]);
		printf("The total weight is: %d\n",totalweight);
}
			
			
	
	return 0;
}
