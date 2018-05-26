#pragma once
#define NULL 0

/*
	Term representing 
*/
struct Term {
	float coefficient;
	int exponent;
	Term *next;
	Term(float coefficient = 0, int exponent = 0, Term *next = NULL);
};

