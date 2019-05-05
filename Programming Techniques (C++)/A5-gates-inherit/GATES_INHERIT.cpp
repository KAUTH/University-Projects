#include <iostream>
#include "GATES_HALFADD.h"
using namespace std;



int main()
{
	andgate kai;						// antikeimeno gia thn ANDGATE
	orgate h;							// antikeimeno gia thn ORGATE
	notgate oxi;						// antikeimeno gia thn NOTGATE
	halfadder hf;						// antikeimeno gia ton HALF-ADDER
	int i,j;
	
	
	
	cout << "The truth table for the AND gate is:\n";				// Ektypwnei ton pinaka alhtheias gia ANDGATE
	for (i=0;i<2;i++){
		for(j=0;j<2;j++){
				kai.setinputG(i);
				kai.setinputOG(j);
				cout << i <<" "  << j <<" "  << kai.getoutputAG() << '\n' ;
		}
	}
	
	cout << "\nThe truth table for the OR gate is:\n";				// Ektypwnei ton pinaka alhtheias gia ORGATE
	for (i=0;i<2;i++){
		for(j=0;j<2;j++){
				h.setinputG(i);
				h.setinputOG(j);
				cout << i <<" " << j <<" "<< h.getoutputOG() << '\n' ;
		}
	}
	
	cout << "\nThe truth table for the NOT gate is:\n";				// Ektypwnei ton pinaka alhtheias gia NOTGATE
	for (i=0;i<2;i++){
				oxi.setinputG(i);
				cout << i <<" "  << oxi.getoutputNG() << '\n' ;
	}
	
	
	cout << "\nThe truth table for the HALF-ADDER is:\n";			// Ektypwnei ton pinaka alhtheias gia HALF-ADDER
	for (i=0;i<2;i++){
		for(j=0;j<2;j++){
				hf.setinputHA(i,j);
				cout << i <<" "  << j <<" "  << hf.getoutputHA1() << " " << hf.getoutputHA2() << '\n' ;
		}
	}
	

	
	return 0;
}
