#include <iostream>
#include <new>
#include "exc_class.h"

int main()
{
	
	safetable table1;									
	
	try{												// elegxos gia yperbash sta stoixeia tou pinaka
		table1[1];
		cout << "Value of A[1]:" << table1[1] << endl; 	// an brethei eksairesi den tha ektelestei
	}
	catch(MyOutofBoundException me){
		cout << me.printmessage() << endl;
	}
	
	try{
		table1[2];
		cout << "Value of A[2]:" << table1[2] << endl;	// tha emfanistei auto to mhnyma
	}
	catch(MyOutofBoundException me){
		cout << me.printmessage() << endl;				
	}
	
	try{
		table1[14];
		cout << "Value of A[14]:" << table1[14] << endl;
	}
	catch(MyOutofBoundException me){					// edw tha ektelestei h catch
		cout << me.printmessage() << endl;
	}
	
	
	safetable table2(20);								// omoiws
	
	try{
		table2[1];
		cout << "Value of A[1]:" << table2[1] << endl;
	}
	catch(MyOutofBoundException me){
		cout << me.printmessage() << endl;
	}
	
	try{
		table2[2];
		cout << "Value of A[2]:" << table2[2] << endl;
	}
	catch(MyOutofBoundException me){
		cout << me.printmessage() << endl;
	}
	
	try{
		table2[32];
		cout << "Value of A[32]:" << table2[32] << endl;
	}
	catch(MyOutofBoundException me){
		cout << me.printmessage() << endl;
	}
	
	
	
	
	return 0;
}
