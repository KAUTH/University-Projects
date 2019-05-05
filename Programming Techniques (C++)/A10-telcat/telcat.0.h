#include <iostream>
#include <string>

using namespace std;


class telcat{
private:
	string name;									// onoma
	int *number;									// arithmos thlefwnou
public:
	telcat(string nom,int numero);					// dhmiourgia antikeimenoy
	~telcat();										// katastrofh antikeimenou
	void setobj(telcat *obj, string name, int number);							// anathesi timhs se ena sigekrimeno antikeimeno
	void viewcat();
};



telcat::telcat()
{
	name = nom;
	number = numero;
	cout << "Created object." << endl;
}


telcat::~telcat()
{
	cout << "Deleted object." << endl;
}

void telcat::setobj(telcat *obj, string name, int number)
{
	*obj(name,number);
}


void telcat::viewcat()
{
	cout << name << number;
}




