class andgate{
	int x;						// Eisodos 1
	int y;						// Eisodos 2
	int z;						// Eksodos
public:
	int setinputAG(int k,int l);		// Thetei tis eisodous
	int getoutputAG();					// Lambanei thn eksodo
};

class orgate{
	int x;						// Omoiws me ANDGATE
	int y;
	int z;
public:
	int setinputOG(int k,int l);
	int getoutputOG();
};

class notgate{
	int x;						// Eisodos
	int z;						// Eksodos
public:
	int setinputNG(int k);		// Thetei thn eisodo
	int getoutputNG();			// Lambanei thn eksodo
};


int andgate::setinputAG(int k, int l)
{
	x=k;
	y=l;
}

int andgate::getoutputAG()
{
 	return z=x && y;				// Telesths tomhs
}

int orgate::setinputOG(int k, int l)
{
	x=k;
	y=l;
}

int orgate::getoutputOG()
{
 	return z=x || y;			// Telesths enwshs
}

int notgate::setinputNG(int k)
{
	x=k;
}

int notgate::getoutputNG()
{
 	return z=(!x);			// Telesths symplirwma
}


class halfadder{					// To CARRY einai idio me to ANDGATE, gia to SUM efarmozoyme thn idiothta XOR = AB' + A'B
	andgate agC,agA,agB;			//agC /agA/ agB: ANDGATE gia to CARRY/ to AB'/ to A'B
	orgate ogALL;					//ogALL: ORGATE gia to AB' + A'B
	notgate ngB,ngA;				//ngB /ngA: NOTGATE gia to B/ to A
	int c,s;						// c: carry, s: sum
public:
	int setinputHA(int k,int l);
	int getoutputHA1();
	int getoutputHA2();
};

int halfadder::setinputHA(int k, int l){				//xrhsimopoioume katallhla ta antikeimena gia na thesoume eisodous kai na paroume tis eksodous
	agC.setinputAG(k,l);
	ngB.setinputNG(l);
	ngA.setinputNG(k);
	agA.setinputAG(k,ngB.getoutputNG());
	agB.setinputAG(ngA.getoutputNG(),l);
	ogALL.setinputOG(agA.getoutputAG(),agB.getoutputAG());
}

int halfadder::getoutputHA1(){							// eksodos gia to CARRY
	return c=agC.getoutputAG();
	
}


int halfadder::getoutputHA2(){
	return s=ogALL.getoutputOG();					// eksodos gia to SUM
}
