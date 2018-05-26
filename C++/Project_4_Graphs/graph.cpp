#include "graph.h"
#include <stack>	// For stack (dft)
#include <queue>	// For queue (bft)
#include <list>		// For list (printing shortest paths)
#include <functional>	// For Priority Queue
#include <iterator>	// For ostream_iterator (iterate through a list)
#include <iostream> // For cout
#include <sstream> // For ostringstream

const int maxWeight = std::numeric_limits<int>::max();	// 'Infinity'. For use with Dijkstra's Algorithm (Shortest Path)
typedef std::pair<int, int> p; // Pair, to hold the current vertex being processed. Used with Dijkstra's Alforithm (Shortest Path)

/*
	Intialize Edge pointer list to NULL.
*/
graph::graph() {
	vertex_list.clear();
}

/*
	Edge structure constructure. 
	Initializes the vertex, weight and the pointer to
	another vertex.
*/
graph::Edge::Edge(int node, float w, bool v, Edge *following) {
	vertex = node;
	weight = w;
	visited = v;
	next = following;
}

/*
	Re-size the vector to the number of vertices
*/
void graph::setVectorSize(int size) {
	this->vertex_list.resize(size);	
}

/*
	Inserts a new vertex and its connection to the Adjacency List. 
*/
void graph::insertAdjList(int index, int node, float w) {
	// ================ IF CURRENT VERTEX NOT CONNECTED ================ //
	if (vertex_list[index] == NULL) {
		Edge *newEdge = new Edge(node, w, false);	// New Edge to be inserted
		vertex_list[index] = newEdge;		// Insert Edge
	}
	// ===================== IF CURRENT VERTEX CONNECTED ===================== //
	else {
		// New Edge to be inserted, pointing to first node at current index
		Edge *newEdge = new Edge(node, w, false, vertex_list[index]);	
		// Insert edge
		vertex_list[index] = newEdge;		
	}
}

/*
	Prints, from the Adjacency List, the vertices and the nodes they are connected to.
*/
void graph::print() {
	printf("========================= (a) Print Graph =========================\n");
	for (int v = 0; v < vertex_list.size(); ++v) {
		printf("Vertex %d", v);
		Edge *tempPtr = vertex_list[v];	// Current vertex to be traversed 
		while (tempPtr != NULL)	{	// While the current vertex still has connections
			printf(" --> %d(%.1f)", tempPtr->vertex, tempPtr->weight);	// Traverse vertices connected to current vertex
			tempPtr = tempPtr->next;	// Go to the next connected vertex
		}
		printf("\n");
	}
}

/*
	Prints the graph, starting from the first vertex, using
	the depth-first-traversal algorithm.
*/
void graph::dft() {
	printf("\n====================== (b) Print Path (DFT) ======================\n");
	std::stack<int> s;
	s.push(0);	// Begin at vertex 0
	Edge *tempPtr = vertex_list[0];	// Initial pointer to traverse vertex 0 connections
	printf("DFT: %d", 0);
	while (!s.empty()) {
		while (tempPtr != NULL) {
			if (tempPtr->visited == false) {
				tempPtr->visited = true;	// Mark vertex as visited
				traverseVisited(tempPtr->vertex); // Mark other instances of vertex as visited
				s.push(tempPtr->vertex);	// Push vertex onto stack
				printf(" --> %d", tempPtr->vertex);
				tempPtr = vertex_list[tempPtr->vertex];	// Point to the connected node
				break;
			}
			tempPtr = tempPtr->next;	// Go check if next node is unvisited
		}
		if (tempPtr == NULL) {	// If current vertex has no more unvisited vertices
			s.pop(); // Pop vertex with no more connections
			if (!s.empty()) {
				tempPtr = vertex_list[s.top()];	// Point to previous stack entry to check for more unvisted vertices
			}
		}
	}
	printf("\n");
	refalsify();	// Make all vertices un-visited again (for use with other algorithms)
}

/*
	Helper function for dft & bft. Used to mark as visited any instance
	of the passed in vertex.
*/
void graph::traverseVisited(int vertex) {
	for (int v = 0; v < vertex_list.size(); ++v) {
		Edge *tempPtr = vertex_list[v];	// Pointer to current vertex to be traversed 
		while (tempPtr != NULL) {	// While the current vertex still has connections
			if (tempPtr->vertex == vertex) {
				tempPtr->visited = true;
			}
			tempPtr = tempPtr->next;	// Go to the next connected vertex
		}
	}
}

/*
	Makes all vertices non-visited.
*/
void graph::refalsify() {
	for (int v = 0; v < vertex_list.size(); ++v) {
		Edge *tempPtr = vertex_list[v];	// Pointer to current vertex to be traversed 
		while (tempPtr != NULL) {	// While the current vertex still has connections
			tempPtr->visited = false;
			tempPtr = tempPtr->next;	// Go to the next connected vertex
		}
	}
}

/*
	Prints the graph, starting from the first vertex, using
	the breadth-first-traversal algorithm.
*/
void graph::bft() {
	printf("\n====================== (c) Print Graph (BFT) ======================\n");
	std::queue<int> q;
	q.push(0);	// Begin at vertex 0
	Edge *tempPtr = vertex_list[0];	// Initial pointer to traverse vertex 0 connections
	printf("BFT: %d", 0);
	while (!q.empty()) {
		q.pop();	// Pop vertex with no more connections
		while (tempPtr != NULL) {
			if (tempPtr->visited == false) {
				tempPtr->visited = true;	// Mark vertex as visited
				traverseVisited(tempPtr->vertex); // Mark other instances of vertex as visited
				q.push(tempPtr->vertex);	// Push vertex onto stack
				printf(" --> %d", tempPtr->vertex);
				tempPtr = tempPtr->next;	// Go check if next node is unvisited
			}
			else {
				tempPtr = tempPtr->next;	// Go check if next node is unvisited
			}
		}
		if (!q.empty()) {
			tempPtr = vertex_list[q.front()];  // Point to oldest queue entry to check for more unvisted vertices
		}
	}
	printf("\n");
	refalsify();	// Make all vertices un-visited again (for use with other algorithms)
}

/*
	Prints the graph, starting from the first vertex, to all other vertices using
	a Dijkstras Shortest Path Algorithm.
*/
void graph::shortestPath(int start) {
	printf("\n================= (d) Print Graph (Shortest Path) =================\n");

	std::vector<int> previousV(vertex_list.size(), -1);	// For storing vertices (used in printPaths())
	//previousV[0] = 0; // Intially save the source 

	// Priority queue that will keep track of 
	// of the vertex being processed (acts as a min heap)
	std::priority_queue<p, std::vector<p>, std::greater<p>> priorityQ;

	std::vector<int> distance(vertex_list.size(), maxWeight);	// Total distance vector. Vertices distance initially infinite 

	priorityQ.push(std::make_pair(0, start));	// Initially insert source into priority queue
	distance[start] = 0;	// Initialize start vertex to 0
	Edge *tempPtr; // Pointer to traverse adjacent vertices

	while (!priorityQ.empty()) {
		int v = priorityQ.top().second;	// Next vertex in queue. The vertex with 'minimum distance' from previous vertex
		priorityQ.pop();	// Pop already processed vertex
		tempPtr = vertex_list[v];	// Pointer to current vertex to be traversed 

		while (tempPtr != NULL) {	// While the current vertex still has connections
			int vertex = tempPtr->vertex;	// Get the value of the vertex adjacent to the current one
			float vWeight = tempPtr->weight;	// Get its weight

			if (distance[vertex] > (distance[v] + vWeight)) {	// Check if the path from adjacent vertext is longer
				distance[vertex] = (distance[v] + vWeight);	// Update the distance of the adjacent vertex
				priorityQ.push(std::make_pair(distance[vertex], vertex));	// Store next vertex to be processed
				previousV[vertex] = v;	// Save the previous vertex (used when printing path)
			}
			tempPtr = tempPtr->next;	// Go to the next connected vertex
		}
	}

	// Print distances
	printf("Source\tDestination\tDistance\n");
	for (int v = 0; v < vertex_list.size(); ++v) {
		printf("0\t%d\t\t%d\n", v, distance[v]);
	}
	// Print paths
	printf("\nPaths:\n");
	printf("0 --> 0\n");
	for (int v = 1; v < vertex_list.size(); ++v) {
		printf("0");
		printPaths(previousV, v);
		printf("\n");
	}
	printf("\n");
}

/*
	Prints the shortest path from 0 to
	passed in vertex.
*/
void graph::printPaths(std::vector<int> previousV, int vertex) {
	std::list<int> pathList;
	while (vertex != -1) {
		pathList.push_front(vertex);
		vertex = previousV[vertex];
	}

	// Iterate through list and print path	 
	for (std::list<int>::iterator it = pathList.begin(); it != pathList.end(); ++it) {
		if (*it != 0) {
			printf(" --> %d", *it);
		}
	}	
}

/*
	Destroys all elements in the vector.
*/
void graph::deleteV() {
	vertex_list.clear();
}
