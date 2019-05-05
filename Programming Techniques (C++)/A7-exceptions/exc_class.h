using namespace std;

class MyOutofBoundException : public exception{			
public:
	char* printmessage();								// emfanizei to mhnyma ths eksairehs
}; 

char* MyOutofBoundException::printmessage()
{
	return "Table out of Bounds";
}


class safetable{
private :
	int *array;
	int size;
public:
	safetable();
	safetable(int x);
	int operator[](int i);
	~safetable();
};



safetable::safetable()									// aplos constructor, me 10 stoixeia pinakas
{
	int j;
	size = 10;
	array = new int [size];	
	for(j = 0; j < size; j++){							// arxikopoiw ton pinaka
		array[j] = j;
	}
}


safetable::safetable(int x)								// constructor, me x stoixeia pinakas
{
	int j;
	size = x;
	array = new int [size];
	for(j = 0; j < size; j++){							// arxikopoiw ton pinaka
		array[j] = j;
	}
}


int safetable::operator[](int i)						// epistrefei stoixeia tou pinaka ths class kai anagnvrizei thn eksairesh
{
	MyOutofBoundException me;							
	if (i<0 || i> size-1)
			throw me;
	else{
		return array[i];
	}
}


safetable::~safetable()									// destructor: emfanizei mhnyma kai apeleythervnei thn desmeymenh mnhmh
{
	cout << "Destructing" << endl;
	delete [] array;
}
