import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LazyPrim {
    private boolean[] marked;   // MST vertices
    private Queue<Edge> mst= new LinkedList<>();    // MST edges
    private MinPQ<Edge> pq;     // PQ of edges

    public LazyPrim(EdgeWeightedGraph G) {

        pq = new MinPQ<Edge>(Comparator.comparingDouble(Edge::getWeight));

        marked = new boolean[G.V()];
        visit(G, 0);

        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.add(e);
            if (!marked[v]) visit(G, v);
            if (!marked[w]) visit(G, w);
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int otherVertex = e.other(v);
            if (!marked[otherVertex]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> mst() {
        return mst;

    }
}