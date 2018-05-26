#include <iostream>
#include <cstdlib>
#include <random>
#include "hash.h"

/*
	item Struct Constructor.
	Initializes data and pointers.
*/
hash::item::item(int num, item *following, item *preceding) {
	number = num;
	next = following;
	previous = preceding;
}

/*
	Hash Constructor.
	Initializes Hash Table to be empty.
	Initializes number of collisions and comparisons to 0.
*/
hash::hash() {
	for (int i = 0; i < tableSize; i++) {
		HashTable[i] = NULL;
	}
	collisions = 0;
	comparisons = 0;
}

/*
	Gets the number of comparisons.
*/
int hash::getComparisons() {
	return comparisons;
}

/*
	Gets the number of collisions.
*/
int hash::getCollisions() {
	return collisions;
}

/*
	Generate index for Hash Table. 
*/
int hash::Hash(int key) {
	int p = 10589;							// Prime number larger than the maximum value for a key
	int a = 86, b = 8;						// Constants. (1 <= a <= p - 1) (0 <= b <= p -1)
	return (((a * key + b) % p) % tableSize);
}

/*
	Add an item into the Hash Table.
	Maintains chained List sorted in ascending order.
*/
void hash::insert(int data) {
	int index = Hash(data);				// Get Hash Table index 

	// --- If the bucket is empty --- //
	if (HashTable[index] == NULL) {
		comparisons++;					// Update the number of comparisons done
		item *newItem = new item(data);
		HashTable[index] = newItem;
	}
	// --- If bucket is not empty --- //
	else {
		item *temp = HashTable[index];	// Temporary pointer to traverse the list
		collisions++;					// Update the number of collisions done

		// Maintain list sorted in ascending order
		while (temp->number < data && temp->next != NULL) {
			comparisons++;										// Update the number of comparisons done
			temp = temp->next;
		}
		if (temp->number != data) {
			item *newItem = new item(data);
			
			if (temp->number < data && temp->next == NULL) {	// If last item in the List
				temp->next = newItem;
				newItem->previous = temp;
			}
			else if (temp->previous == NULL) {					// If first item in the List
				newItem->next = temp;
				temp->previous = newItem;
				HashTable[index] = newItem;
			}
			else {												// If any inner item in the List
				newItem->next = temp;
				newItem->previous = temp->previous;
				temp->previous->next = newItem;
				temp->previous = newItem;
			}
		}
		comparisons += 3;										// Update the number of comparisons done
	}
}

/*
	Gets the number of non-empty buckets of the Hash Table.
*/
int hash::getNumBuckets() {
	int count = 0;

	for (int i = 0; i < tableSize; i++) {
		if (HashTable[i] != NULL) {
			count++;
		}
	}
	return count;
}