#include <stdio.h>
#include <stdlib.h>
#include "hangfun.h"


int main()
{	
	char *word,*guess,letter,wordcount[20];
	int i=0,success=0,fail=0,m=0;		
	printf("First user: Enter a word with 20 characters max.\n");
	scanf("%s",&wordcount);
	
	while(wordcount[i]!= '\0'){
		m=(i++)+1;
	}
	
	
	if ((word=(char *)malloc(m*sizeof(char)))==NULL){
		printf("There isn't enough memory for allocation.\n");
		exit(1);
	}
	
	
	
	if ((guess=(char *)malloc((m+1)*sizeof(char)))==NULL){
		printf("There isn't enough memory for allocation.\n");
		exit(2);
	}
	
	guess[m+1]='\0';
	
	word=wordcount;
	
	
	for(i=0;i<m;i++){
		guess[i]='*';
	}

	for(i=0;i<24;i++){
			printf("\n");
	}
	hangman(fail);

	
	while((success<m) && (fail<6)){
		
		printf("\nSecond user: The word is: %s.\n",guess);
	
	
		printf("Enter letter guess.\n");
		fflush(stdin);
		scanf("%c",&letter);
		for(i=0;i<24;i++){
			printf("\n");
		}
		
		int scan=0;
		for(i=0;i<m;i++){
			if (word[i]==letter && guess[i]=='*'){
				guess[i]=letter;
				success++;
				scan=1;
			}
		}
		if (scan==1){
			printf("Bravo!\n");
			hangman(fail);
		}
		else{
			printf("Sorry...\n");
			fail++;
			hangman(fail);
		}
	}
	
	if (success==m)
		printf("You win!(the word was %s)\n",word);
	else
		printf("You lost...(the word was %s)\n",word);
		
	// free(word); 
	// free(guess);       
	//δεν έβαλα τις free διότι κολλάει το πρόγραμμα στο τέλος

	return 0;	
}




