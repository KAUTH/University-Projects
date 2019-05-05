#include <stdio.h>



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
