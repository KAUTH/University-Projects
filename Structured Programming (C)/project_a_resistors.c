#include <stdio.h>
int main()
{
float r1=0;
float r2=0;
float r3=0;
float r4=0;
float V=0;
float n=0;
float k=0;                            /*n einai h timh ths antistashs kai k to Imax ths*/
float r_total1=0; /*r_total olikh antistash, n_total plithos antistasewn, rr_total antistrofh antistash */
float rr_total2=0;
float n_total1=0;
float n_total2=0;
float inf=1.0/0.0;
float r_total2=inf;
int on=1;
printf("Enter value of r1.\n");
scanf("%f",&r1);
printf("Enter value of r2.\n");
scanf("%f",&r2);
printf("Enter value of r3.\n");
scanf("%f",&r3);
printf("Enter value of r4.\n");
scanf("%f",&r4);
printf("Enter value of V.\n");
scanf("%f",&V);
for( ; ; ){
	printf("Enter value of resistance.\n");
	scanf("%f",&n);
	printf("Enter value of maximum I for r.\n");
	scanf("%f",&k);
	if(n<=V/k){
		printf("The I passing the resistance is greater than Imax.\n");
		continue;
}
	else if((r1<=n && n<=r2) && !(r3<=n && n<=r4)) { /* sthn ousia einai diafora duo sunolwn, h alliws tomh me to symplhrwmatiko tou*/
		r_total1+=n;
		n_total1+=1;
		printf("There are %f resistances in team one.\n",n_total1);
		printf("There are %f resistances in team two.\n",n_total2);
		printf("The total resistance, in series, of team one is %f.\n",r_total1);
		if (r_total2==inf)
			printf("The total resistance, in parallel, of team two is 0.000000.\n");
		else
			printf("The total resistance, in parallel, of team two is %f.\n",r_total2);
	}
	else if((r3<=n && n<=r4) && !(r1<=n && n<=r2)){
	 	rr_total2=1/r_total2+1/n;
	 	r_total2=1/rr_total2;
	 	n_total2+=1;
	 	printf("There are %f resistances in team one.\n",n_total1);
		printf("There are %f resistances in team two.\n",n_total2);
		printf("The total resistance, in series, of team one is %f.\n",r_total1);
		printf("The total resistance, in parallel, of team two is %f.\n",r_total2);
	}
	else if ((r1<=n && n<=r2) && (r3<=n && n<=r4)){     /*o.n. einai to order number, bohthaei sthn diataksh */
		if ( on%2){
			r_total1+=n;
			n_total1+=1;
			printf("There are %f resistances in team one.\n",n_total1);
			printf("There are %f resistances in team two.\n",n_total2);
			printf("The total resistance, in series, of team one is %f.\n",r_total1);
			if (r_total2==inf)
				printf("The total resistance, in parallel, of team two is 0.000000.\n");
			else
				printf("The total resistance, in parallel, of team two is %f.\n",r_total2);
		}
		else{
	 		rr_total2=1/r_total2+1/n;
	 		r_total2=1/rr_total2;
	 		n_total2+=1;
	 		printf("There are %f resistances in team one.\n",n_total1);
			printf("There are %f resistances in team two.\n",n_total2);
			printf("The total resistance, in series, of team one is %f.\n",r_total1);
			printf("The total resistance, in parallel, of team two is %f.\n",r_total2);
		}
	 	on++;
	}
	else {
		printf("The given resistance value does not belong in [r1,r2] or [r3,r4].\n");
	}
continue;	
}
return 0;	
}		
