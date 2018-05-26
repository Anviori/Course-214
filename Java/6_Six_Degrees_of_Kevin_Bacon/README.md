Six Degrees of Kevin Bacon
======

Introduction:
======

Algorithms and Datastructures sixth assginment. This assignment introduces
the use of finding the shortest path between two points. This is explored by
making the game "Six Degrees of Kevin Bacon". This game finds the fastest
way to relate an actor in a movie to another actor in another movie.

- Actors in movies are modeled as a graph, with each actor as a node; the edges
connected to actors they share movies with. 

- This graph is a unweighted and undirected, so finding the shortest path 
between actors that share movies is done using a Breadth First Search.

- The Open Movie Database API is used to get the actors of various movies.

- To access the API, Big Data is used, an open source data parser that supports
XML and CSV files.

- All movies and actors will be sorted using the Comparable and Comparator
interaces. 