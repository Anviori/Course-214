#pragma once
#include "Term.h"
#include <fstream>

class Poly {
public:
	Poly();
	Poly(const Poly &copy);
	~Poly();

	void operator = (const Poly &copy);
	int getCount();

	// Public Polynomial functions
	void readBuild(std::ifstream &fin);
	void print();
	void polyProduct(Poly Q);
	
private:
	// Private Polynomial Functions
	void termProduct(Term P, Term Q);
	void add(Poly P, Poly Q);
	int exponent();

	// Private Queue functions
	void enqueue(Term term);
	void dequeue();
	void dequeue(Term &term);
	void retrieve(Term &term);
	void clear();
	bool empty();
	int size();

	// Member Variables/Pointers
	int count;
	Term *front;
	Term *rear;
};

