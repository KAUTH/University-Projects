#include <stdio.h>
#include <alloc.h>
void main()
{
  int **connection,i,j,n,*m,*set1,*set2,node1,node2,fl,*flg,k=0,l;
  float **weight,min,sum=0;;
  printf("Δώστε τον αριθμό των κορυφών n = ? ");
  scanf("%d",&n);
  m=(int *)malloc(n*sizeof(int));
  flg=(int *)malloc(n*sizeof(int));
  set1=(int *)malloc(n/2*sizeof(int));
  set2=(int *)malloc(n/2*sizeof(int));
  if(m == NULL || flg == NULL || set1 == NULL || set2 == NULL){
    printf("Δεν υπάρχει αρκετή διαθέσιμη μνήμη (Θέση 1)\n");
    return;
  }
  for(i=0;i<n;i++){
    printf("Δώστε τον αριθμό των κορυφών που συνδέονται με την κορυφή %d = ? ",i);
    scanf("%d",&m[i]);
  }
  connection=(int **)malloc(n*sizeof(int *));
  if(connection==NULL){
    printf("Δεν υπάρχει αρκετή διαθέσιμη μνήμη (Θέση 2)\n");
    return;
  }
  for(i=0;i<n;i++){
    connection[i]=(int *)malloc(m[i]*sizeof(int));
    if(connection[i]==NULL){
      printf("Δεν υπάρχει αρκετή διαθέσιμη μνήμη (Θέση 3)\n");
      return;
    }
  }
  weight=(float **)malloc(n*sizeof(float *));
  if(weight==NULL){
    printf("Δεν υπάρχει αρκετή διαθέσιμη μνήμη (Θέση 4)\n");
    return;
  }
  for(i=0;i<n;i++){
    weight[i]=(float *)malloc(m[i]*sizeof(float));
    if(weight[i]==NULL){
      printf("Δεν υπάρχει αρκετή διαθέσιμη μνήμη (Θέση 5)\n");
      return;
    }
  }
  for(i=0;i<n;i++){
    flg[i]=0;
    printf("Δώστε τους αριθμούς των κορυφών που συνδέονται με την κορυφή %d\n",i);
    for(j=0;j<m[i];j++)
      scanf("%d",&connection[i][j]);
    printf("Δώστε τα αντίστοιχα βάρη\n");
    for(j=0;j<m[i];j++)
      scanf("%f",&weight[i][j]);
  }

  do{
    fl=1;
    for(i=0;i<n;i++){
      if(flg[i])continue;
      for(j=0;j<m[i];j++){
        if(flg[connection[i][j]])continue;
        if(fl){
          node1=i;
	  node2=connection[i][j];
	  min=weight[i][j];
	  fl=0;
	  continue;
        }
        if(weight[i][j]<min){
          min=weight[i][j];
	  node1=i;
	  node2=connection[i][j];
        }
      }
    }
    if(!fl){
      flg[node1]=1;
      flg[node2]=1;
      set1[k]=node1;
      set2[k]=node2;
      sum+=min;
      k++;
    }
  }while(!fl);
  if(k<n/2)
    printf("Δεν έγινε πλήρης διαχωρισμός του γραφήματος\n");
  for(i=0;i<k;i++){
    for(j=0;j<k;j++){
      if(i==j)continue;
      for(l=0;l<m[set1[i]];l++){
	if(connection[set1[i]][l]==set2[j]){
	  sum+=weight[set1[i]][l];
	  break;
	}
      }
    }
  }
  printf("\n Ομάδα A\n");
  for(i=0;i<k;i++)
    printf("%3d ",set1[i]);
  printf("\n\n Ομάδα Β\n");
  for(i=0;i<k;i++)
    printf("%3d ",set2[i]);
  printf("\nΣυνολικό βάρος των ακμών που θα αναιρεθούν   %f\n",sum);
  free(m);
  free(set1);
  free(set2);
  free(flg);
  for(i=0;i<n;i++){
    free(connection[i]);
    free(weight[i]);
  }
  free(connection);
  free(weight);



}
