#include <iostream>
using namespace std;



class calendar{
	int *day;									// pointer pou deixnei thn hmera
	int *month;									// pointer pou deixnei ton mhna
	int *year;									// pointer pou deixnei thn xronia
public:
	calendar operator+(calendar c2); 			// operator gia prosthesi hmeromhniwn
	calendar operator-(calendar c2); 			// operator gia afairesi hmeromhniwn

	calendar(int d, int m, int y);				// constructor
	calendar(const calendar &copcal);			// copy constructor

	int getoutputday();							// prosbasi sto day
	int getoutputmonth();						// prosbasi sto month
	int getoutputyear();						// prosbasi sto year

	~calendar();								// destructor
};


calendar::calendar(int d, int m, int y)			// constructor
{
	day = new int;
	month = new int;
	year = new int;
	
	*day = d;
	*month = m;
	*year = y;
}

calendar::~calendar()							// destructor
{
		cout << "Freeing memory.\n";
		
		delete day;
		delete month;
		delete year;
}

calendar calendar::operator+(calendar c2)		// operator gia prosthesi hmeromhniwn
{
	calendar c3(0,0,0);							// arxikopoihsh tou antikeimenou
	
	
	int tmpday = *day + *c2.day;				// tmpday: proswrinh timh athrismatos
	int rd = tmpday/30;							
	
	if (rd < 1) rd = 0;							// rd(reminder): an xreiastei na prostethei "kratoumeno"
	if (rd >= 1) 
		*c3.day = tmpday - 30;
	else 
		*c3.day = tmpday;
	
	
	
	int tmpmonth = rd + *month + *c2.month;		// omoiws
	int rm = tmpmonth/12;
	
	if (rm < 1) rm = 0;
	if (rm >= 1) 
		*c3.month = tmpmonth - 12;
	else 
		*c3.day = tmpmonth;
	
	
	
	*c3.year = rm + *year + *c2.year;
	
	return c3;
 } 


calendar calendar::operator-(calendar c2)		// operator gia afairesi hmeromhniwn
{
	calendar c3(0,0,0);							// arxikopoihsh tou antikeimenou
	
	
	int sd = 0;
	int tmpday = *day - *c2.day;				// tmpday: proswrinh timh diaforas
	
	if (tmpday < 0){							// sd: an xreiastei "kapelaki" sthn afairesh
		*c3.day = 30 + *day - *c2.day; 
		sd = 1;
	}
	else {
		*c3.day = tmpday;
	}
	
	
	
	int sm = 0;									// omoiws
	int tmpmonth = *month - *c2.month - sd ;
	
	if (tmpmonth < 0){
		*c3.month = 12 + *month - *c2.month - sd; 
		sm = 1;
	}
	else{
		*c3.month = tmpmonth;
	}
	


	*c3.year = *year - *c2.year - sm;
	
	return c3;
 } 
 
 
 calendar::calendar(const calendar &copcal)		// copy constructor
 {
 	day = new int;
	month = new int;
	year = new  int;

	*day = *copcal.day;
	*month = *copcal.month;
	*year = *copcal.year;
 	
 }
 
 
 int calendar::getoutputday()		// prosbasi sto day
{
 	return *day;
}

int calendar::getoutputmonth()		// prosbasi sto month
{
 	return *month;
}

int calendar::getoutputyear()		// prosbasi sto year
{
 	return *year;
}
