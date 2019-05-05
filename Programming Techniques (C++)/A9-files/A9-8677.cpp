#include <iostream>
#include <fstream>
#include <string>
#include <typeinfo>


using namespace std;


class WrongType : public exception{							// h eksairesh
public:
	char* printmessage();								
}; 

char* WrongType::printmessage()
{
	return "Wrong type of variable";
}


class empl{													// h taksi
private:
	string name;
	double sal;
public:	
	empl(string n, double s);								// contructor gia eisagvgh apo pliktrologio
	empl();													// contructor gia eisagvgh apo arxeio
};


empl::empl(string n, double s)
{
	WrongType wt;
	if((n == "!")||(s == '!')){
		cout << "You entered !" << endl;
	}

	
	if( ( typeid(n) != typeid(string()) ) || ( typeid(s) != typeid(double()) ) ){			
		throw wt;
	}
	else{
		name = n;												
		sal = s;												
	}
	
}


empl::empl()
{
	WrongType wt;
	ifstream in("employee.txt");
	
	
	string temps;
	double tempd;
	
	in >> temps;
	in >> tempd;
	if( (temps == "!") || (tempd == '!') ){
		cout << "You entered !" << endl;
	}
	
	if( ( typeid(temps) != typeid(string()) ) || ( typeid(tempd) != typeid(double()) ) ){
		throw wt;
	}
	else{
		name = temps;
		sal = tempd;
	}
	in.close();
}
	
	

int main()
{
	int i,j;
	string onoma;
	double misthos;
	
	try{														// eisagvgh apo arxeio (10 antikeimena)
		for(i=0;i<10;i++){
			empl employee();
		}
	}
	catch(WrongType wt){
		cout << wt.printmessage() << endl;
	}
	
	try{														// eisagvgh apo xrhsth (10 antikeimena)
		for (j=0;j<10;j++){
			cout << "Enter name." << endl;
			cin >> onoma;
		
			cout << "Enter salary." << endl;
			cin >> misthos;
		
			empl employee(onoma,misthos);
		}
	}
	catch(WrongType wt){
		cout << wt.printmessage() << endl;
	}
	
	return 0;
}
	
	
	
	
	
	
	
	

