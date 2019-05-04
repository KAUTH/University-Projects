#include <stdio.h>
#include <math.h>
#define MAX 3

int navigator(float *d_dis,float *x,float *y,int n, float sx,float sy,float *min_dis,int *h_id)
{
  int i;
  float dis;
  *min_dis=sqrt(pow(x[0]-sx,2)+pow(y[0]-sy,2));
  *h_id=0;
  for(i=1;i<n;i++){
    dis=sqrt(pow(x[i]-sx,2)+pow(y[i]-sy,2));
    if(*min_dis>dis){
      *min_dis=dis;
      *h_id=i;
    }
  }
  if(*min_dis<d_dis[*h_id] && *min_dis>0.7*d_dis[*h_id])return 2;
  if(*min_dis<=0.7*d_dis[*h_id])return 1;
  return 0;
}

void main()
{
  int i,k,n,h_id;
  char ids[MAX][11];
  float x[MAX],y[MAX],d_dis[MAX],min_dis,sx,sy,dx,dy,R;
  printf("Δώστε τις συντεταγμένες (x,y)του ραδιόφαρου που επισημαίνει την περιοχή\n");
  scanf("%f %f",&dx,&dy);
  printf("Δώστε την ακτίνα της περιοχής = ? ");
  scanf("%f",&R);
  printf("Δώστε τον αριθμό των υφάλων (Μέγιστο %d) = ? ",MAX);
  scanf("%d",&n);
  for(i=0;i<n;i++){
    printf("Για τον υπ' αριθμό %d ύφαλο\n",i+1);
    printf("Δώστε την ταυτότητα = ? ");
    scanf("%s",ids[i]);
    printf("Δώστε τις συντεταγμένες (x,y)\n");
    scanf("%f %f",&x[i],&y[i]);
    printf("Δώστε την ελάχιστη απόσταση προσέγγισης = ? ");
    scanf("%f",&d_dis[i]);
  }
  for(;;){
    printf("Δώστε τις συντεταγμένες (x,y) του πλοίου\n");
    scanf("%f %f",&sx,&sy);
    if(sx==0 && sy==0)break;
    if(sqrt(pow(dx-sx,2)+pow(dy-sy,2))>R)continue;
    k=navigator(d_dis,x,y,n,sx,sy,&min_dis,&h_id);
    if(k==2)printf("Προσοχή ***Κίτρινος συναγερμός ***\n");
    if(k==1)printf("Προσοχή ***Κόκκινος συναγερμός ***\n");
    printf("Πλησιέστερος ύφαλος ο %s \n",ids[h_id]);
    printf("Απόσταση = %f\n",min_dis);
    printf("Απόσταση ασφαλείας = %f\n",d_dis[h_id]);
  }
}
