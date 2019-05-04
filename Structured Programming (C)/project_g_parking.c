#include <stdio.h>
#include <time.h>
#include <conio.h>
void main()
{
  FILE *fp;
  time_t c_time,in_time;
  long c_num,f_size=0,fc_num;
  int i,fl,r_size;
  float fee,sum=0,f_time;
  char c;
  r_size=sizeof(long)+sizeof(time_t);
  fp=fopen("D:/ST_FILES/Lesons/DOM-PRO/2012/ergasies/program/data","w+b");
  if(fp==NULL){
    printf("Δεν μπορεί να ανοίξει το αρχείο\n");
    return;
  }
  printf("Δώστε τη χρέωση για κάθε ώρα παραμονής στο σταθμό = ? ");
  scanf("%f",&fee);
  for(;;){
    do{
      printf("Πιέστε <Ι> αν το αυτοκίνητο βρίσκεται στην είσοδο\n");
      printf("Πιέστε <Ε> αν το αυτοκίνητο βρίσκεται στην έξοδο\n");
      printf("Πιέστε <O> για να τερματιστεί το πρόγραμμα\n");
      fflush(stdin);
      c=getch();
      if(f_size==0 && c=='E'){
	printf("Δεν υπάρχουν αυτοκίνητα στο σταθμό\n");
	c=' ';
      }
    }while(c!='I' && c!='E' && c!='O');
    if(c=='O')break;
    printf("Δώστε τον αριθμό κυκλοφορίας του αυτοκινήτου = ? ");
    scanf("%ld",&c_num);
    c_time=time(NULL);
    if(c=='I'){
      fseek(fp,f_size*r_size,SEEK_SET);
      fwrite(&c_num,sizeof c_num,1,fp);
      fwrite(&c_time,sizeof c_time,1,fp);
      f_size++;
    }
    if(c=='E'){
      rewind(fp);
      fl=1;
      for(i=0;i<f_size;i++){
	fseek(fp,i*r_size,SEEK_SET);
	fread(&fc_num,sizeof fc_num,1,fp);
	if(fc_num==c_num){
	  fl=0;
	  break;
        }
      }
      if(fl){
        printf("Δεν έχει καταχωρηθεί αυτοκίνητο με αριθμό %ld\n",c_num);
	continue;
      }
      fread(&in_time,sizeof in_time,1,fp);
      f_time=(c_time-in_time)/3600.;
      sum+=f_time*fee;
      printf("Αριθμός κυκλοφορίας %ld\n",c_num);
      printf("Χρόνος ώρες         %f\n",f_time);
      printf("Χρέωση              %f Ευρώ\n",f_time*fee);
      fseek(fp,(f_size-1)*r_size,SEEK_SET);
      fread(&c_num,sizeof c_num,1,fp);
      fread(&c_time,sizeof c_time,1,fp);
      fseek(fp,i*r_size,SEEK_SET);
      fwrite(&c_num,sizeof c_num,1,fp);
      fwrite(&c_time,sizeof c_time,1,fp);
      f_size--;
    }
  }
  printf("Εισπράχτηκαν συνολικά %f Ευρώ\n",sum);
  
  printf("Στο σταθμό έχουν μείνει %ld αυτοκίνητα με αριθμούς κυκλοφορίας\n",f_size);
  for(i=0;i<f_size;i++){
    fseek(fp,i*r_size,SEEK_SET);
    fread(&c_num,sizeof c_num,1,fp);
    printf("%ld\n",c_num);
  }
  fclose(fp);
}
