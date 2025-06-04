# graph_algorithm_with_gui
We will first explain about the basic classes:
Node:
We implement all constructor interface functions and from pos id to the class fields: x, y, z, pos, str, tag, w
Edge:
We will implement the interface functions
Department fields: src, dest, w, info, tag
We will explain the two departments we were required to implement:
DWG:
Class variables:
hash map-Nodes __k = id__ __A [k] = Node __, hash map-edgek __k = dest__ __A [k] = (hash map __k = src__ __A [k] = Edge__)
, Arraylist -Edges
int change, numOfEdge.
Function:
1.Getnode,getEdge:
We used Hash map to bring vertex and side in O (1)
2. Adding a Node: we take Location and create a Node through it and update it in the Nose hashmap then we create a Hashmap in its appropriate and we add to change 1 
3. We update changes and add the rib to HASH MAP and Arraylist and add to number of edge 1 if it isnot exist.
4. node iter:
We create an Arraylist then convert it to an iterator and check if the change is palliative so if we make an exception.
5. edge iter:
We create an Arraylist then convert it to an iterator and check if the change is palliative so if we make an exception
6. remove node 
We will pass all the edge in hashmap and delete them and delet the node from hash map.
7.remove edge
We will delet it from hash map.
8.node size
We will return hash mapsize
9.edge size
We will return numberofedge
10.get mc
We will return mc
DWGA:
We will use  floyd warshall algorithm and in tsp we will find the nearest neighbor and we will try it from all the node 
EX2:
We will use j friend 
Test:
We will create g and clear it before all test and will try the function





