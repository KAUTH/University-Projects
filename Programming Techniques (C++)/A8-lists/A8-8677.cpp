#include <iostream>
#include <cstdlib>

using namespace std;


template <class T> class Node{							// H class node
private:
	T data;
	Node<T> *next = '/0';
public:
	Node();
	Node(Node<T>* n);
	~Node();
	Node<T>* add();
	T get();
};

template <class T> T Node<T>::get()
{
	return data;
}


template <class T> Node<T>::Node(Node<T>* n)			// deixnei sthn epomenhnode kai deinei tuxaia timh
{
	T randval = rand();
	data = randval;
	this->next = n;
}

template <class T> Node<T>::Node()
{
	T randval = rand();
	data = randval;
}

template <class T> Node<T>::~Node()						// destructor
{

}

template <class T> Node<T>* Node<T>::add()
{
	return next;
}




int main()
{
	Node<int> o1,o2,o3,o4;								// deinei sto epomeno node
	o1.Node(&o2);
	o2.Node(&o3);
	o3.Node(&o4);


	cout << o1.get() <<endl;							// pairnoume ta data
	cout << o2.get() <<endl;
	cout << o3.get() <<endl;
	cout << o4.get() <<endl;
	
	
	return 0;
}
