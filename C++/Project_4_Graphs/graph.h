#pragma once
#include <vector>

class graph{
private:
	/*
		Struct to hold vertices connections
		along with the associated weight.
	*/
	struct Edge {
		int vertex;		// Vertex name
		float weight;	// Weight of edge
		bool visited;	// Vertex visited or not (for DFT & BFT)
		Edge *next;		// Pointer to next vertex
		Edge(int node = 0, float w = 0, bool visited = false, Edge *next = NULL);	// Struct constructor
	};

	std::vector<Edge*> vertex_list;	// Stores pointers to vertices

	void traverseVisited(int vertex); // DFT helper function
	void refalsify(); // Refalsify vertices to be used with olther algorithms
	void printPaths(std::vector<int> previousV, int vertex); // Prints the shortest paths

public:
	graph();
	void setVectorSize(int size);	// Set the size of the vector (number of vertices)
	void insertAdjList(int index, int node, float w);	// Insert vertext to list (with their weight)
	void print();	// Print vertices
	void dft();	// Print vertices using dft
	void bft(); // Print vertices using bft
	void shortestPath(int start); // Print vertices using Dijkstra's shortest pasth algorithm
	void deleteV();
};

