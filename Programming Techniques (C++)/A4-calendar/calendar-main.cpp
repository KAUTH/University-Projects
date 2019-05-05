#include <iostream>
#include "calendar-class_fun.h"
using namespace std;


int main()
 {
 	
 	
 	int d1, d2, d5, d6;				// dokimastikes hmerominies
 	int m1, m2, m5, m6;
 	int y1, y2, y5, y6;
 	
 	cout << "Enter dates for c1, c2, c5, c6.\n";
 	
	cout << "c1:\n";
	cin >> d1 >> m1 >> y1;
	
	cout << "c2:\n";
 	cin >> d2 >> m2 >> y2;
 	
 	cout << "c5:\n";
 	cin >> d5 >> m5 >> y5;

 	cout << "c6:\n";
 	cin >> d6 >> m6 >> y6;
 	
 	
 	calendar c1(d1,m1,y1);
 	calendar c2(d2,m2,y2);
 	
	calendar c3 = c1 + c2;			// prosthesi hmerominiwn
 	calendar c4 = c2 - c1;			// afairesi hmerominiwn
 	
	cout << c3.getoutputday() <<" days " << c3.getoutputmonth() << " months " << c3.getoutputyear() << " years \n\n" ;
 	cout << c4.getoutputday() <<" days " << c4.getoutputmonth() << " months " << c4.getoutputyear() << " years \n\n" ;
 	
	calendar c5(d5,m5,y5), c6(d6,m6,y6);
 	
	c5 = c6;						// copy c6 to c5
 	
	cout <<"Copying c6 to c5, c5 = \n" << c5.getoutputday() << "/" << c5.getoutputmonth() << "/" << c5.getoutputyear() << "\n" ;
 	
 	return 0;
 }
