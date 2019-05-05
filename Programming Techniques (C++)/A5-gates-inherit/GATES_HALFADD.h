
											// Den to ekana me bool gia na mhn to periorisw stis eisodous/eksodous

class gate{
protected:
	int x;									// Eisodos 1
	int z;									// Eksodos
public:							
	int setinputG(int k);					// Thetei tis eisodous
};

int gate::setinputG(int k)
{
	x=k;
}





class orgate:public gate{					
protected:
	int y;									// Eisodos 2
public:
	int setinputOG(int l);					// Thetei tis eisodous
	int getoutputOG();						// Lambanei thn eksodo
};



int orgate::setinputOG(int l)
{
	y=l;
}

int orgate::getoutputOG()
{
 	return z=x || y;						// Telesths enwshs
}




class andgate:public orgate{
public:		
	int getoutputAG();						// Lambanei thn eksodo	
};

int andgate::getoutputAG()
{
 	return z=x && y;						// Telesths tomhs
}






class notgate:public gate{
public:		
	int getoutputNG();						// Lambanei thn eksodo	
};


int notgate::getoutputNG()					
{
 	return z=(!x);							// Telesths symplirwma
}







class halfadder{
protected:							// To CARRY einai idio me to ANDGATE, gia to SUM efarmozoyme thn idiothta XOR = AB' + A'B
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
	agC.setinputG(k);
	agC.setinputOG(l);
	ngB.setinputG(l);
	ngA.setinputG(k);
	agA.setinputG(k);
	agA.setinputOG(ngB.getoutputNG());
	agB.setinputG(ngA.getoutputNG());
	agB.setinputOG(l);
	ogALL.setinputG(agA.getoutputAG());
	ogALL.setinputOG(agB.getoutputAG());
}

int halfadder::getoutputHA1(){							// eksodos gia to CARRY
	return c=agC.getoutputAG();
	
}


int halfadder::getoutputHA2(){
	return s=ogALL.getoutputOG();					// eksodos gia to SUM
}
