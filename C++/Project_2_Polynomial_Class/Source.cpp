#include <iostream>
#include "Poly.h"
#include <fstream>

int main() {
	// Open input file with polynomial paramerters.
	std::ifstream fin("inputfile.txt");

	if (!fin) {
		std::cerr << "Cannot open inputfile.txt" << std::endl;
		exit(1);
	}

	// Read and Build first Polynomial 
	Poly P;
	printf("Creating first Polynomial from file...");
	P.readBuild(fin);
	printf("\nFirst Polynomial created successfully.\n");

	// Read and Build second Polynomial
	Poly Q;
	printf("\nCreating second Polynomial from file...");
	Q.readBuild(fin);
	printf("\nSecond Polynomial created successfully.\n\n");

	// Multiply the two Polynomials: P = P * Q
	P.polyProduct(Q);

	// Print Result
	printf("Product:\n(");
	P.print();
	printf(")\n\nProduct has %d Terms.\n\n", P.getCount());
	
	system("PAUSE");
	return 0;
}