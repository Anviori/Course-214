#include <iostream>
#include <cstdlib>
#include <random>
#pragma once

/*
	Hash Table Class.
*/
class hash {
private:
	static const int tableSize = 1021;	// Size of the Hash Table
	int collisions;						// Number of collisions
	int comparisons;					// Number of comparisons 

	// --- Item Structure to be stored in the Hash Table --- //
	struct item {
		int number;													// Key/Data value
		item *next;													// Pointer to next item
		item *previous;												// Pointer to previous item
		item(int num = 0, item *next = NULL, item *previous = NULL);	// Struct Constructor
	};

	item *HashTable[tableSize];		   // Hash Table 

	int Hash(int key);				   // Generate Hash index

public:
	hash();							   // Constructor
	int getComparisons();			   // Returns the number of comparisons
	int getCollisions();			   // Returns the number of collisions
	int getNumBuckets();			   // Returns how many non-empty buckets are in the Hash Table
	void insert(int data);			   // Insert an item into the Hash Table
};
