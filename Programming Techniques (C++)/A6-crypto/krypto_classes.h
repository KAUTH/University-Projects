using namespace std;

class Crypto{
protected:
	string Message;
	string Encrypted;
	string Decrypted;
public:
	string virtual Encryption() = 0;		// gnhsia
	string virtual Decryption() = 0;		// gnhsia
	Crypto(string msg);						// constructor pou arxikopoieitai apo tis derived
	string getMessage();					
	void setMessage(string msg);
	string getEncrypted();
	string getDecrypted();
};


Crypto::Crypto(string msg)
{
	Message = msg;
}

string Crypto::getMessage()
{
	return Message;						
}

void Crypto::setMessage(string msg)
{
	Message = msg;						
}

string Crypto::getEncrypted()
{
	return Encrypted;
}

string Crypto::getDecrypted()
{
	return Decrypted;
}





class Caesar: public Crypto{					// kryptografish tou kaisara
protected:
	int n;										// "kleidi" tou caesar
public:
	void setn(int ni);							// arxikopoiv to n
	string Encryption();
	string Decryption();
	Caesar(string msg): Crypto(msg)	{ }			// o constructor dinei thn timh ston base constructor
};


void Caesar::setn(int ni)
{
	n = ni;
}


string Caesar::Encryption()
{
	int beg = 'A';								// gia na brw ton ASCII kodiko
	int i, m;
	char *str = new char;						// allagmeno mhnyma
	for(i=0; Message[i]!='\0'; i++){
		m = Message[i] - beg;
		char enlet = (m + n)%26 + beg;			// enlet: encrypted letter	
		str[i] = enlet;
		Encrypted = str;						// to allagmeno metabibazetai to kryptografimeno
	}
	return Encrypted;
}


string Caesar::Decryption()						// h antistrofh diadikasia
{
	int beg = 'A';
	int i, m;
	char *str = new char;
	for(i=0; Message[i]!='\0'; i++){
		m = Encrypted[i] - beg;
		char enlet = (m - n)%26 + beg;			
		str[i] = enlet;
		Decrypted = str;
	}
	return Decrypted;
}





 
class XOR: public Crypto{						// kryptografish XOR
protected:
	char key;									// "kleidi" tou XOR
public:
	void set_key(char k);						// arxikopoiv to key
	string Encryption();
	string Decryption();
	XOR(string msg): Crypto(msg) { }			// o constructor dinei thn timh ston base constructor
};


void XOR::set_key(char k)
{
	key = k;
}

string XOR::Encryption()						
{
	int i;
	char enlet;
	char *str = new char;						// allagmeno mhnyma
	for(i=0; Message[i]!='\0'; i++){
		enlet = Message[i]^key;					// enlet: encrypted letter	
		str[i] = enlet;
		Encrypted = str;						// to allagmeno metabibazetai to kryptografimeno
	}
	return Encrypted;
}

string XOR::Decryption()						// h antistrofh diadikasia
{
	int i;
	char enlet;
	char *str = new char;
	for(i=0; Message[i]!='\0'; i++){
		enlet = Encrypted[i]^key;
		str[i] = enlet;
		Decrypted = str;
	}
	return Decrypted;
}

