#include <iostream>
#include <cstdlib>
#include <random>
#include "hash.h"

/*
	This program creates uses the implemented hash class and 
	evaluates it's performance.
*/
int main() {
	std::random_device rGenerator;								// Generate stochastic/non-deterministic random numbers
	std::uniform_int_distribution<int> distribution(0, 9999);	// Set range of numbers to be generated (0 to 9,999)
	int comparisonsArr[10] = {};								// Holds number of comparisons for all trials
	int collisionsArr[10] = {};									// Holds number of collisions for all trials
	int bucketsUsed[10] = {};									// Holds number of buckets used for all trials
	
	// ----- START HASH TABLE INSERTION ----- //
	for (int i = 0; i < 10; i++) {								// Perform 10 trials to test hash insertion performance
		hash h;													

		for (int j = 0; j < 5000; j++) {
			h.insert(distribution(rGenerator));
		}

		comparisonsArr[i] = h.getComparisons();
		collisionsArr[i] = h.getCollisions();
		bucketsUsed[i] = h.getNumBuckets();

		// --- DISPLAY NUMBER OF COLLISIONS FOR CURRENT TRIAL --- //
		printf("Trial %d: Number of Collisions: %d\t", (i + 1), h.getCollisions());
		// --- DISPLAY NUMBER OF COMPARISONS FOR CURRENT TRIAL --- //
		printf("Number of Comparisons: %d", h.getComparisons());
		// --- DISPLAY NUMBER OF BUCKETS USED --- //
		printf("\tNumber of buckets used: %d/1021\n", bucketsUsed[i]); 
	}

	// --- DISPLAY AVERAGE NUMBER OF COLLISIONS AND COMPARISONS FOR ALL TRIALS --- //
	float aCollisions = 0.0;
	float aComparisons = 0.0;
	float aBucketsUsed = 0.0;
	for (int i = 0; i < 10; i++) {
		aCollisions += collisionsArr[i];
		aComparisons += comparisonsArr[i];
		aBucketsUsed += bucketsUsed[i];
	}
	printf("\nAverage Collisions: %.0f\t Average Comparisons: %.0f\t Average Buckets Used: %.0f\n\n", (aCollisions / 10), (aComparisons / 10), (aBucketsUsed / 10));

	system("PAUSE");
	return 0;
}
