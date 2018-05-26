#include "Poly.h"
#include "Term.h"
#include <iostream>
#include <fstream>

/*
	Constructor initializes the Polynomial Queue
	to be empty.
*/
Poly::Poly() {
	front = rear = NULL;
	count = 0;
}

/*
	Copy Constructor.
*/
Poly::Poly(const Poly &copy) {
	if (!(copy.count == 0)) {
		// Create a copy of the first Term of 'copy' and make front point to it.
		Term *current = new Term(copy.front->coefficient, copy.front->exponent);
		front = current;

		// Create a Term that points to the Term after the front Term of 'copy'
		Term *temp = copy.front->next;

		while (temp != NULL) {
			// Create new Terms equal to those of the entire Polynomial Queue, copy.
			current->next = new Term(temp->coefficient, temp->exponent);
			current = current->next;
			temp = temp->next;
		}

		// Current is pointing to the last Term.
		current->next = NULL;
		rear = current;
		count = copy.count;
	}
	else {
		// If copy is empty, re-initialize 'this' to be an empty Polynomial Queue.
		front = rear = NULL;
		count = 0;
	}
}

/*
	Overloading assignment operator.
*/
void Poly::operator = (const Poly &copy) {
	// Create a copy of the first Term of 'copy' and make front point to it.
	Term *current = new Term(copy.front->coefficient, copy.front->exponent);
	front = current;

	// Create a Term that points to the Term after the front Term of 'copy'
	Term *temp = copy.front->next;

	while (temp != NULL) {
		// Create new Terms equal to those of the entire Polynomial Queue, copy.
		current->next = new Term(temp->coefficient, temp->exponent);
		current = current->next;
		temp = temp->next;
	}

	// Current is pointing to the last Term.
	current->next = NULL;
	rear = current;
	count = copy.count;
}

/*
	Destructor.
*/
Poly::~Poly() {
	clear();
}

/*
	Calculates the product of the two polynomials.
*/
void Poly::polyProduct(Poly Q) {
	// Temporary Terms used to hold the terms of the two
	// Polynomials Queues for calculations.
	Term termP, termQ, term;

	// Temporary Polynomials Queues used to hold
	// the intermediate and final products and sums.
	Poly partialProduct, oldSum, newSum;

	// Copy of Polynomial Queue, Q.
	Poly tempQ = Q;

	int pSize = size();

	for (int i = 0; i < pSize; i++) {
		dequeue(termP);
		for (int j = 0; j < Q.size(); j++) {
			// Find procut of current Term of P times all Terms of Q.
			tempQ.dequeue(termQ);
			partialProduct.termProduct(termP, termQ);
		}
		// Re-initialize Polynomial Queue copy of Q.
		tempQ = Q;	

		// Add common Terms.
		newSum.add(oldSum, partialProduct);
		oldSum = newSum;

		// Clear partialProduct Polynomial Queue of previous values.
		partialProduct.clear();
	}

	// Store final result (product of P and Q) in Polynomial P.
	int fSize = newSum.size();
	for (int i = 0; i < fSize; i++) {
		newSum.dequeue(term);
		enqueue(term);
	}
}

/*
	Multiply Term P with Term Q.
*/
void Poly::termProduct(Term P, Term Q) {
	// Create a new term with a coeffient equal to the product of P&Q's coefficients,
	// and an exponent equal to the sum of P&Q's exponents.
	Term product((P.coefficient * Q.coefficient), (P.exponent + Q.exponent));
	
	// Add the product of the two Terms to the partialProduct Polynomial Queue.
	enqueue(product);
}

/*
	Add the common Terms of the given Polynomial Queues.
*/
void Poly::add(Poly P, Poly Q) {
	// Clear newSum Polynomial Queue of previous values.
	clear();
	// While either of the passed in Polynomial Queues is not empty.
	while (!P.empty() || !Q.empty()) {
		Term termP, termQ;
		// If the exponent of the term of P is greater than that of Q,
		// enqeue that term (Both Queues are in decending order of exponents).
		if (P.exponent() > Q.exponent()) {
			P.dequeue(termP);
			enqueue(termP);
		}
		// If the exponent of the term of Q is greater than that of P,
		// enqeue that term.
		else if (Q.exponent() > P.exponent()) {
			Q.dequeue(termQ);
			enqueue(termQ);
		}
		// If the exponent are equal, enqueue a term with the same exponent and 
		// a sum of the coefficients (as long as the sum is not 0).
		else {
			P.dequeue(termP);
			Q.dequeue(termQ);
			if (termP.coefficient + termQ.coefficient != 0) {
				Term sum(termP.coefficient + termQ.coefficient, termP.exponent);
				enqueue(sum);
			}
		}
	}
}

/*
	Return the value of the exponent in the front Term of the
	calling Polynomial Queue.
	Returns -1 if Polynomial Queue is empty.
*/
int Poly::exponent() {
	if (empty()) {
		return -1;
	}
	else {
		return front->exponent;
	}
}

/*
	Gets the Term at the front of the Polynomial Queue.
*/
void Poly::retrieve(Term &term) {
	if (empty()) {
		printf("\nQueue is empty!\n");
	}
	else {
		// If Queue not empty, make referenced Term
		// equal to the Term pointed to by front.
		term = *front;
	}
}

/*
	Gets user input for Polynomial Paramaters. 
*/
void Poly::readBuild(std::ifstream &fin) {
	float coefficient = 0.0;
	int exponent = 0, lastExponent = 0;
	bool firstTerm = true;

	do {
		fin >> coefficient;

		if (coefficient != 0.0) {
			fin >> exponent;

			// Incorrect exponent when exponent is less than zero or greater 
			// than the previous exponet and it is not part of the first term.
			if (exponent < 0 || (exponent >= lastExponent && !firstTerm)) {
				exponent = 0;

				printf("\nIncorrect exponent entered.");
			}
			else {
				// Create new term and add it to the Polynomial Queue
				Term new_term(coefficient, exponent);
				enqueue(new_term);
				firstTerm = false;
			}
			lastExponent = exponent;
		}
	} while (coefficient != 0.0 && exponent != 0);
}

/*
	Enqueues a term on the Polynomial Queue.
*/
void Poly::enqueue(Term term) {
	// Create new Term with given parameters. 
	Term *temp = new Term(term.coefficient, term.exponent);

	if (temp == NULL) {
		// If no more memory, let the user know there is an overflow.
		printf("\nOverflow!\n");
		return;
	}
	if (empty()) {
		// If Polynomial Queue was empty, front and rear point to new Term.
		front = rear = temp;
		count++;
	}
	else {
		// Otherwise, previous Term points to new term
		// and new Term becomes new rear.
		rear->next = temp;
		rear = temp;
		count++;
	}
}

/*
	Dequeues a term from the Polynomial Queue.
*/
void Poly::dequeue(Term &term) {
	// Create a temporary pointer to the front of the Polynomial Queue.
	Term *temp = front;

	if (empty()) {
		// If Polynomial Queue was empty, there is nothing to dequeue.
		printf("\nQueue is empty!\n");
		return;
	}
	if (front == rear) {
		// If only one Term in Polynomial Queue, front and rear point to NULL.
		front = rear = NULL;
		count--;
	}
	else {
		// Otherwise, front points to the next Term.
		front = front->next;
		count--;
	}
	// Referenced Term, term, is made equal to the front of the Polynomial Queue.
	term.coefficient = temp->coefficient;
	term.exponent = temp->exponent;

	// The memeroy used by the previous Term is freed using 
	// the temporary pointer to front.
	delete temp;
}

/*
Dequeues a term from the Polynomial Queue.
*/
void Poly::dequeue() {
	// Create a temporary pointer to the front of the Polynomial Queue.
	Term *temp = front;

	if (empty()) {
		// If Polynomial Queue was empty, there is nothing to dequeue.
		printf("\nQueue is empty!\n");
		return;
	}
	if (front == rear) {
		// If only one Term in Polynomial Queue, front and rear point to NULL.
		front = rear = NULL;
		count--;
	}
	else {
		// Otherwise, front points to the next Term.
		front = front->next;
		count--;
	}
	// The memeroy used by the previous Term is freed using 
	// the temporary pointer to front.
	delete temp;
}

/*
	Prints Polynomial. 
*/
void Poly::print() {
	Term *printTerm = front;	
	bool firstTerm = true;

	// Iterate through the queue to print out all terms.
	while (printTerm != NULL) {
		// Initial check for first Term (to mask out initial '+' symbol if coefficient is positive). 
		if (firstTerm) {		
			firstTerm = false;
			if (printTerm->coefficient < 0) {
				printf("-");
			}     
		}
		else if (printTerm->coefficient < 0) {
			printf(" - ");
		}
		else {
			printf(" + ");
		}

		// Make negative coefficient positive.
		float r = (printTerm->coefficient >= 0) ? printTerm->coefficient : -(printTerm->coefficient);
		if (r != 1) {
			printf("%.1f", r);	// Never print a coefficient of 1.
		}
		if (printTerm->exponent > 1) {
			printf("x^%d", printTerm->exponent);	// Only print exponent if it's greater than 1.
		}
		if (printTerm->exponent == 1) {
			printf("x");
		}
		if (r == 1 && printTerm->exponent == 0) {
			printf("1");	// Print coefficient of 1 ONLY when exponent is 0.
		}
		printTerm = printTerm->next;	// Go print next Term.
	}
	if (firstTerm) {
		printf("0");	// If Polynomial Queue has no Terms, print 0.
	}
}

/*
	Clears the Polynomial Queue of all terms.
*/
void Poly::clear() {
	// Continually dequeue the Polynomial Queue until it is empty.
	while (!empty()) {
		dequeue();
	}
}

/*
	Checks if Polynomial Queue is empty.
*/
bool Poly::empty() {
	return count == 0;
}

/*
	Return the size of the Polynomial Queue.
*/
int Poly::size() {
	return count;
}

/*
	Return the number of Terms in the Queue.
*/
int Poly::getCount() {
	return count;
}