Minimum Spanning Tree (MST) using Lazy Prim Algorithm


The objective of this assignment is to implement a solution for constructing a Minimum Spanning Tree (MST) using the Lazy Prim algorithm. The problem involves processing a file containing coordinates of edges, working with graph representations, and prioritizing edges based on the length between them to build an optimal spanning tree. The length between edges is calculated using the Euclidean distance formula.

Lazy Prim Algorithm


The Lazy Prim algorithm is a greedy algorithm used to find the Minimum Spanning Tree (MST) of a connected, undirected graph. It starts with an arbitrary vertex and repeatedly adds the shortest edge that connects a vertex in the MST to a vertex outside the MST. This process continues until all vertices are included in the MST.

Implementation


The implementation involves the following steps:

Read the file containing coordinates of edges and construct the graph representation.
Implement the Lazy Prim algorithm to construct the Minimum Spanning Tree.
Calculate the length between edges using the Euclidean distance formula.
Prioritize edges based on their lengths to build an optimal spanning tree.
Output the Minimum Spanning Tree along with its total length.
