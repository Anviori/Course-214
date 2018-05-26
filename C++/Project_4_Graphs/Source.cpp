#include <iostream>
#include <fstream>
#include <vector>
#include "graph.h"

/*
	This program creates a directed graph from
	edge list, using adjacency list to represent
	the graph.
	The vertices and edges are read from a file.
*/

// ============================================= MAIN ============================================= //
int main() {
	// ============ VARIABLE INITIALIZATION ============ //
	float w = 0.0;		// Distance (weight) of the edge
	int vertices = 0,	// Number of vertices
		vi = 0,			// First vertex number 
		vj = 0;			// Second vertex number

	std::ifstream fin("inputfile.txt");	 // File with graph data

	// =================== INPUT FILE VALIDATION ==================== // 
	if (!fin) {
		std::cerr << "Cannot open " << "inputfile.txt" << std::endl;
		exit(1);
	}

	// =================================== BUILD GRAPH =================================== //
	graph g;
	fin >> vertices;					// Get the number of virtices 
	g.setVectorSize(vertices);			// Re-size the vector to the number of vertices

	fin >> vi;							// Get first vertex number (index to AdjList)
	while (vi != -1) {
		fin >> vj;						// Get next vertex number
		fin >> w;						// Get current edge weight
		g.insertAdjList(vi, vj, w);		// Add the edge to the Adjacency List
		fin >> vi;						// Get next vertex number (index to AdjList)
	}

	// ============================== PRINT ADJACENCY LIST ============================== //
	g.print();	// Print the Adjacency List

	// =========================== PRINT ADJACENCY LIST (DFT) =========================== //
	g.dft();	// Print the DFT of the graph

	// =========================== PRINT ADJACENCY LIST (BFT) =========================== //
	g.bft();	// Print the BFT of the graph

	// ====================== PRINT ADJACENCY LIST (Shortest Path) ====================== //
	g.shortestPath(0);	// Print the shortest path from 0 to all other nodes using 
						// Dijkstra's algorithm

	g.deleteV();	// Delete vector (free up memory)

	system("PAUSE");
	return 0;
}