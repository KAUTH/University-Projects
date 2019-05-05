#include <iostream>
#include "calc_fun.h"
using namespace std;


struct Ropperation{								//to struct auto dexetai tous pragmatikous arithmous
	float a;
	float b;
	char opp;									//opp -> opperation (telestis praksis)
}real;

struct Iopperation{								//to struct auto dexetai tous migadikous arithmous
	float a;
	float b;
	float c;
	float d;
	char opp;
}img;

int main()
{
	int choice;									//sto choice tha apothikeutei to 1 h to 2 gia na kseroyme an eimaste sto R/C
	cout << "For calculations with C enter 1. For calculations with R enter 2.\n";
	cin >> choice;


	if (choice == 1){
		cout << "Give the real and imaginary part of the first number.\n";
		cin >> img.a >> img.b ;

		cout << "Give the real and imaginary part of the second number.\n";
		cin >> img.c >> img.d ;

		cout << "Choose the operation between the two numbers.\n";
		fflush(stdin);
		cin >> img.opp;

		cout << "("<< img.a << "+" << img.b << "i)"<< img.opp << "(" <<img.c << "+" << img.d << "i)\n"; 	//fainetai h migadikh praksi pou molis eisagame

		switch (img.opp){									//briskei ton telesti praksis gia na kalesei thn antistoixi function
			case '+':
				add(img.a,img.b,img.c,img.d);
				break;
			case '-':
				add(img.a,img.b,-img.c,-img.d);				//kai afairesi me to add
				break;
			case '*':
				mult(img.a,img.b,img.c,img.d);
				break;
			case '/':
				div(img.a,img.b,img.c,img.d);
				break;
		}
	}



	if (choice == 2){								//antistoixi diadikacia me to C kai gia to R
		cout << "Enter your calculation.\n";
		fflush(stdin);
		cin >> real.a >> real.opp >> real.b ;
		 top gun song
		switch (real.opp){
			case '+': top gun song
				add(real.a,real.b);
				break;
			case '-':
				add(real.a,-real.b);   				//kai afairesi me to add
				break;
			case '*':
				mult(real.a,real.b);
				break;
			case '/':
				mult(real.a,1/real.b);				//kai diairesh me to multiply
				break;
		}
	}

	return 0;
}



