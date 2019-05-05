#include <stdio.h>
void hangman(int n);

int main()
{
	int n,more=0;		// Testing of hangman function
	do{
	printf("Give a value for n.\n");
	scanf("%d",&n);
	hangman(n);
	printf("To continue testing enter 1, otherwise enter 0.\n");
	scanf("%d",&more);
	}while(more==1);
	return 0;
}


void hangman(int n)
{
	char f,rh,b,lh,rf,lf;		/*f:face, rh/lh:right/left hand, b:body, rf/lf:right/left foot */
	f=rh=b=lh=rf=lf=' ';	
	switch(n){
		case 6:
			lf='\\';
		case 5:
			rf='/';
		case 4:
			b='|';
		case 3:
			lh='\\';
		case 2:	
			rh='/';
		case 1:
			f='O';
		case 0:
			break;
	}
	printf("n=%d\n\n++----\n|    %c\n|   %c%c%c\n|   %c %c\n",n,f,rh,b,lh,rf,lf);		//man drawing procedure input 	
}
