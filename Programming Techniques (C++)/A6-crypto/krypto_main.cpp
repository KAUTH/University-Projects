#include <iostream>
#include <string>
#include "krypto_classes.h"

using namespace std;


int main()
{
	Caesar c_enc("HELLO");			// mhnyma pros kryptografish
	
	c_enc.setn(2);					// gia n = 2
	
	c_enc.Encryption();				//kryptografish
	
	string resEncCes = c_enc.getEncrypted();		// pairnv kryptografimeno mhnyma

	cout << resEncCes << endl;
	
	c_enc.Decryption();								// pairnv apokryptografimeno mhnyma
	
	cout << c_enc.getDecrypted() << endl;
 
 
 
	XOR x_enc("HELLO");				// mhnyma pros kryptografish
	
	x_enc.set_key('k');				// gia key = 'k'

	x_enc.Encryption();				//kryptografish
	
	string resEncXOR = x_enc.getEncrypted();		// pairnv kryptografimeno mhnyma

	cout << resEncXOR << endl;

	x_enc.Decryption();								// pairnv apokryptografimeno mhnyma

	cout << x_enc.getDecrypted();
 
 return 0;
}

