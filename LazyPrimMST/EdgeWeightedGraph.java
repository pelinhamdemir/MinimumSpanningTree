import java.util.ArrayList;
import java.util.List;

public class EdgeWeightedGraph {
    List<Edge> edges=new ArrayList<>();
    private  int V;
    private  Bag<Edge>[] adj;
    public EdgeWeightedGraph(int V)    {
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Edge>();    }
    public void addEdge(Edge e)    {
        int v = e.either(), w = e.other(v);
        if (v >= 0 && v < V && w >= 0 && w < V) {
            adj[v].add(e);
            adj[w].add(e);
            edges.add(e);
        } }
    public Iterable<Edge> adj(int v)
    {  return adj[v];  }
    public Iterable<Edge> edges(int v)
    {  return edges;  }

    public int V() {
        return V;
    }
}