#include <stdio.h>
#include <process.h>

float nroot(float x,float e,int max,int *rep);
float froot(float x1,float x0,float e,int max,int *rep);

float f(float x){return x*x-5*x+6;}
float fd(float x){return 2*x-5;}

void main()
{ float x,x1,x0,e,r;
  int max,rep=0;
  printf("Δώστε τον μέγιστο αριθμό επαναλήψεων για τις συναρτήσεις nroot και froot = ? ");
  scanf("%d",&max);
  printf("\nΓια τη συνάρτηση nroot\n");
  printf("Δώστε την αρχική τιμή του x = ? ");
  scanf("%f",&x);
  printf("Δώστε την ακρίβεια e = ? ");
  scanf("%f",&e);
  r=nroot(x,e,max,&rep);
  if(rep==0)
    printf("Για την τιμή x = %f η παράγωγος της f(x) μηδενίζεται\n",r);
  else if(rep > max){
    printf("Μετά από %d επαναλήψεις δε βρέθηκε ρίζα με ακρίβεια %f\n",max,e);
    printf("Τελευταία προσέγγιση για τη ρίζα η τιμή %f\n",r);
  }
  else
    printf("Μετά από %d επαναλήψεις η τιμή που βρέθηκε για τη ρίζα είναι η %f με ακρίβεια e = %f\n",rep,r,e);
  printf("\nΓια τη συνάρτηση froot\n");
  printf("Δώστε τις αρχικές τιμές για το x0 και x1 = ? ");
  scanf("%f %f",&x0,&x1);
  printf("Δώστε την ακρίβεια e = ? ");
  scanf("%f",&e);
  rep=0;
  r=froot(x1,x0,e,max,&rep);
  if(rep==0)
    printf("Κατά την εξέλιξη του αλγορίθμου η διαφορά f(x1)-f(x0) βρέθηκε ίση με το 0\n");
  else if(rep > max){
    printf("Μετά από %d επαναλήψεις δε βρέθηκε ρίζα με ακρίβεια %f \n",max,e);
    printf("Τελευταία προσέγγιση για τη ρίζα η τιμή %f\n",r);
  }
  else
    printf("Μετά από %d επαναλήψεις η τιμή που βρέθηκε για τη ρίζα είναι η %f με ακρίβεια e = %f\n\n",rep,r,e);
}

float nroot(float x,float e,int max,int *rep)
{ 
  float xn,R;
  if(fd(x)==0){
    *rep=0;
     return x;
  }
  (*rep)++;
  if(*rep>max)return x;
  xn=x-f(x)/fd(x);
  if(((xn-x)>0?xn-x:x-xn)>e)
    R=nroot(xn,e,max,rep);
  else
    R=xn;
  return R ;
}

float froot(float x1,float x0,float e,int max,int *rep)
{
  float xn,R;
  if((f(x1)-f(x0))==0){
    *rep=0;
     return x1;
  }
  (*rep)++;
  if(*rep>max)return x1;
  xn=x1-(x1-x0)*f(x1)/(f(x1)-f(x0));
  if(((xn-x1)>0?xn-x1:x1-xn)>e){
    x0=x1;
    x1=xn;
    R=froot(x1,x0,e,max,rep);
  }
  else
    R=xn;
  return R ;
}
