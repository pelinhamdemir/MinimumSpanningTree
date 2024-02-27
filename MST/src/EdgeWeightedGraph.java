import java.util.*;

class EdgeWeightedGraph {
    private List<Edge> edges = new ArrayList<>();
    private int V;
    private Bag<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public void addEdge(Edge e) {
        int v = e.either(), w = e.other(v);
        if (v >= 0 && v < V && w >= 0 && w < V) {
            adj[v].add(e);
            adj[w].add(e);
            edges.add(e);
        }
    }

    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    public Iterable<Edge> edges() {
        return edges;
    }



    public static class dijkstra {
        public Edge[] edgeTo;
        public long[] distTo;
        public EdgeWeightedGraph graph;
        public dijkstra(EdgeWeightedGraph graph){
            this.graph=graph;
            edgeTo=new Edge[graph.V];
            distTo=new long[graph.V];
        }


        public void dijkstra(EdgeWeightedGraph G, int s, int destination) {
            boolean[] visited=new boolean[G.V];
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingDouble(v -> distTo[v]));

            for (int v = 0; v < G.V; v++) {
                distTo[v] = Long.MAX_VALUE / 2;
                visited[v]=false;
                edgeTo[v]=null;
            }
            distTo[s] = 0;
            pq.add(s);


            while (!pq.isEmpty()) {
                int v = pq.poll();
                visited[v]=true;
                for (Edge e : G.adj(v))
                    relax(e, pq,visited);
            }
        }

        private void relax(Edge e, PriorityQueue<Integer> pq,boolean[] visited) {
            int v = e.either(), w = e.other(v);
            if (distTo[w] > distTo[v] + e.getWeight()&&!visited[w]) {
                distTo[w] = distTo[v] + e.getWeight();
                edgeTo[w] = e;
                if (pq.contains(w)) {
                    pq.remove(w);
                    pq.add(w);
                } else {
                    pq.add(w);
                }
            }
        }


        public List<Edge> shortestPathTo(int destination) {
            List<Edge> path = new ArrayList<>();
            for (Edge e = edgeTo[destination]; e != null; e = edgeTo[e.either()]) {
                if(path.contains(e))
                    break;
                path.add(e);
            }
            Collections.reverse(path);
            return path;
        }

        public long distanceTo(int destination) {
            return distTo[destination];
        }
    }
}