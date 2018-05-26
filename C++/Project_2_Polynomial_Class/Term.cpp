#include "Term.h"

Term::Term(float coeff, int exp, Term *n) {
	coefficient = coeff;
	exponent = exp;
	next = n;
}
