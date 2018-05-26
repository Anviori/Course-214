#include <iostream>
#include <cstdlib>
#include <ctime>
#include <random>
#include <algorithm>

static int qCompNum, hCompNum;						// Variables to hold the number of comparisons done by Quicksort and Heapsort

/*
	Paritions an array using a 'pivot'. Any element less than the
	pivot is to the left of it, and any element greater is to the right.
*/
int partition(int data[], int start, int end) {
	int pivot = data[end];							// Pivot is chosen to be the last element in the array
	int pIndex = start;								// Scanning will take place at the start of the array

	// ----- PARTITION THE ARRAY ----- //
	for (int i = start; i < end; i++) {
		if (data[i] <= pivot) {
			qCompNum++;								// Increase the number of the comparisons done by the algorithm
			std::swap(data[i], data[pIndex]);		// Any element less than the pivot will placed to the
			pIndex++;								// left of pIndex. Otherwise, the element is unchanged (leaving the larger ones to the right).		
		}
	}
	std::swap(data[pIndex], data[end]);				// Finally, swap the pivot with the element at pIndex

	return pIndex;									// Return the pivot index
}

/*
	Sort an array using the Quicksort algorithm.
*/
void quicksort(int data[], int start, int end) {
	if (start < end) {								// If partition has at least 2 elements
		// ----- PARTITION THE ARRAY ----- //
		int pIndex = partition(data, start, end);	// Get index of pivot

		// ----- SORT THE PARTITIONS ----- //
		quicksort(data, start, (pIndex - 1));		// Sort the left partitions of the array
		quicksort(data, (pIndex + 1), end);			// Sort the right partitions of the array
	}
}

/*
	Turns an array in a Max Heap.
*/
void heapify(int data[], int size, int root) {
	// -------- FIND THE LARGEST NODE AMONG ROOT AND CHILDREN -------- //
	int largest = root;								// Initialize the largest as the root node
	int l = 2 * root + 1;							// Left child
	int r = 2 * root + 2;							// Right child

	if (l < size && data[l] > data[largest]) {		// If the left child is larger than root
		hCompNum++;									// Increase the number of the comparisons done by the algorithm
		largest = l;								// make 'largest' the left child
	}
	if (r < size && data[r] > data[largest]) {		// If the right child is larger than root
		hCompNum++;									// Increase the number of the comparisons done by the algorithm
		largest = r;								// make 'largest' the right child
	}

	// --------- CONTINUE HEAPIFYING IF ROOT IS NOT LARGEST --------- //
	if (largest != root) {
		hCompNum++;									// Increase the number of the comparisons done by the algorithm
		std::swap(data[root], data[largest]);		// Swap the root node with the current 'largest' element
		heapify(data, size, largest);				// Heapify sub-tree
	}
}

/*
	Sort an array using the Heapsort algorithm.
*/
void heapsort(int data[], int size) {
	// ------- BUILD MAX HEAP ------ //
	for (int i = size / 2 - 1; i >= 0; i--) {		// Initially rearrange the array to create a Max-Heap
		heapify(data, size, i);						// (The root node being the largest node in the array)
	}

	// --- SORT THE CREATED HEAP --- //
	for (int i = size - 1; i >= 0; i--) {
		std::swap(data[0], data[i]);				// Swap the root node (largest element) with the last element in the heap/array
		heapify(data, i, 0);						// Heapify the root node to get the next largest element at the root again
	}
}

/*
	Calculate the Mean of an array.
*/
float findMean(int arr[], int size) {
	float mean = 0.0;									// Initialize mean to 0
	for (int i = 0; i < size; i++) {				// Iterate through the array and sum all the elements
		mean += arr[i];
	}
	return mean / size;								// Return the mean
}

/*
	Calculate the Median of an array.
*/
float findMedian(int arr[], int size) {
	heapsort(arr, size);										// First, sort the array
	float Median = 0.0;
	if (size % 2 != 0) {							
		Median = arr[size / 2];									// If size is odd, Median = middle element
	}
	else {														
		Median = (arr[(size - 1) / 2] + arr[size / 2]) / 2.0;	// If size is even, Median = avg of two middle elements
	}
	return Median;												// Return Median
}

/*
	Calculate the Standard deviation of an arry.
*/
float findStandardDeviation(int data[], float mean, int size) {
	float standardDeviation = 0.0;
	for (int i = 0; i < size; i++) {
		standardDeviation += pow(data[i] - mean, 2);
	}
	return sqrt(standardDeviation / size);
}


/*
	This program generates 5000 random integers and stores them in an array.
	The data in the array is sorted using the Quicksort and Heapsort algorithms.
	The number of comparisons performed by each algorithm is used to find their 
	respectinve minimum, maximum, mean, median, and standard deviation.
*/
int main() {
	// -------------------------- VARIABLES -------------------------- //
	int quicksortData[5000] = {};								// Array to store Quicksort copy of the data (Initialized to 0)
	int heapsortData[5000] = {};								// Array to store Heapsort copy of the data (Initialized to 0);
	int qCompNumArr[30] = {};									// Array to store Quicksort comparisons (Initialized to 0)	
	int hCompNumArr[30] = {};									// Array to store Heapsort comparisons (Initialized to 0)	
	std::random_device rGenerator;								// Generate stochastic/non-deterministic random numbers
	std::uniform_int_distribution<int> distribution(0, 1000000);// Set range of numbers to be generated (0 to 10^6)

	// ---------------------- START COMPUTATION ---------------------- //
	for (int i = 0; i < 30; i++) {							// Perform sorting 30 times
	// ------- POPULATE ARRAY WITH RANDOMLY GENERATED NUMBERS ------- //
		for (int j = 0; j < 5000; j++) {					// Generate 5000 random numbers and insert into data array
			quicksortData[j] = distribution(rGenerator);			
		}
		for (int i = 0; i < 5000; i++) {					// Make copy of data array for use with Heapsort algorithm
			heapsortData[i] = quicksortData[i];	
		}

	// ------------ SORT ARRAY USING QUICKSORT ALGORITHM ------------ //
		qCompNum = 0;										// Initialize comparisons amount to 0 before sorting
		quicksort(quicksortData, 0, 4999);					// Sort using Quicksort
		qCompNumArr[i] = qCompNum;							// Place current number comparisons in comparison array

	// ------------ SORT ARRAY USING HEAPSORT ALGORITHM ------------ //
		hCompNum = 0;										// Initialize comparisons amount to 0 before sorting
		heapsort(heapsortData, 5000);						// Sort using Quicksort
		hCompNumArr[i] = hCompNum;							// Place current number comparisons in comparison array

	}

	// -------------------- PROCESS THE DATA -------------------- //
	// ----- QUICKSORT DATA ----- //
	int qMin, qMax;
	float qMean, qMedian, qStandardD;

	qMin = *std::min_element(qCompNumArr, qCompNumArr + 30);	// Get the smallest comparison count
	qMax = *std::max_element(qCompNumArr, qCompNumArr + 30);	// Get the largest comparison count
	qMean = findMean(qCompNumArr, 30);							// Get the Mean comparison count
	qMedian = findMedian(qCompNumArr, 30);						// Get the Median comparison count
	qStandardD = findStandardDeviation(qCompNumArr, qMean, 30);	// Get the Standard Deviation comparison count

	// ----- HEAPSORT DATA ------ //
	int hMin, hMax;
	float hMean, hMedian, hStandardD;

	hMin = *std::min_element(hCompNumArr, hCompNumArr + 30);	// Get the smallest comparison count
	hMax = *std::max_element(hCompNumArr, hCompNumArr + 30);	// Get the largest comparison count
	hMean = findMean(hCompNumArr, 30);							// Get the Mean comparison count
	hMedian = findMedian(hCompNumArr, 30);						// Get the Median comparison count
	hStandardD = findStandardDeviation(hCompNumArr, hMean, 30);	// Get the Standard Deviation comparison count

	// -------------------- DISPLAY THE DATA -------------------- //
	// ----- QUICKSORT DATA ----- //
	printf("Quicksort Data: \n");
	printf("Minimum: %d\n", qMin);
	printf("Maximum: %d\n", qMax);
	printf("Mean: %.2f\n", qMean);
	printf("Median: %.2f\n", qMedian);
	printf("Standard Deviation: %.2f\n\n", qStandardD);

	// ----- HEAPSORT DATA ------ //
	printf("\Heapsort Data: \n");
	printf("Minimum: %d\n", hMin);
	printf("Maximum: %d\n", hMax);
	printf("Mean: %.2f\n", hMean);
	printf("Median: %.2f\n", hMedian);
	printf("Standard Deviation: %.2f\n\n", hStandardD);

	system("PAUSE");
	return 0;
}

