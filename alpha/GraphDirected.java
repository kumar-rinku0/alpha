package alpha;

import java.util.*;

public class GraphDirected extends GraphC {

    @Override
    public void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList();
        }
        graph[0].add(new Edge(0, 1, 2));
        graph[0].add(new Edge(0, 2, 4));
        graph[1].add(new Edge(1, 3, 7));
        graph[1].add(new Edge(1, 2, 1));
        graph[2].add(new Edge(2, 4, 3));
        graph[3].add(new Edge(3, 5, 1));
        graph[4].add(new Edge(4, 3, 2));
        graph[4].add(new Edge(4, 5, 5));
    }

    private static void topologi(ArrayList<Edge> graph[], int curr, boolean visited[], Stack<Integer> st) {
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!visited[e.dest]) {
                topologi(graph, e.dest, visited, st);
            }
        }
        st.add(curr);
    }
    public int connectingCities(int cities[][]) {
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> (a.w - b.w));
        boolean vis[] = new boolean[cities.length];
        pq.add(new Pair(0, 0));
        int finalCost = 0;
        while(!pq.isEmpty()) {
            Pair p = pq.remove();
            if(!vis[p.n]) {
                vis[p.n] = true;
                finalCost += p.w;
                for(int i=0; i<cities[p.n].length; i++) {
                    if(cities[p.n][i] != 0) {
                        pq.add(new Pair(i, cities[p.n][i]));
                    }
                }
            }
        }
        return finalCost;
    }

    public static void topologicalSort(ArrayList<Edge> graph[]) {
        Stack<Integer> st = new Stack();
        boolean visited[] = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (!visited[i]) {
                topologi(graph, i, visited, st);
            }
        }
        while (!st.isEmpty()) {
            System.out.print(st.pop() + " ");
        }
    }

    private static int[] dijkastraAlgorithm(ArrayList<Edge> graph[], int V, int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int dis[] = new int[V];
        for (int i = 0; i < V; i++) {
            if (i != src) {
                dis[i] = Integer.MAX_VALUE;
            }
        }
        boolean vis[] = new boolean[V];
        pq.add(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair p = pq.remove();
            if (!vis[p.n]) {
                vis[p.n] = true;
                for (int i = 0; i < graph[p.n].size(); i++) {
                    Edge e = graph[p.n].get(i);
                    int u = p.n;
                    int v = e.dest;
                    int w = e.wet;
                    if (!vis[v] && dis[u] + w < dis[v]) {
                        dis[v] = dis[u] + w;
                        pq.add(new Pair(v, dis[v]));
                    }
                }
            }
        }
        return dis;
    }

    private static void bellmanFordAlgo(ArrayList<Edge> graph[], int src) {
        int V = graph.length;
        int dist[] = new int[V];
        for (int i = 0; i < V; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < V - 1; i++) {
            for (ArrayList<Edge> graph1 : graph) {
                for (int k = 0; k < graph1.size(); k++) {
                    Edge e = graph1.get(k);
                    int u = e.scr;
                    int v = e.dest;
                    int w = e.wet;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                    }
                }
            }
        }
        for (int i = 0; i < V; i++) {
            System.out.print(dist[i] + " ");
        }
    }
    
    public boolean isCycle(ArrayList<Edge> graph[], boolean visited[]) {
        for (int i = 0; i < graph.length; i++) {
            if (isCycleUndirected(graph, i, -1, visited)) {
                return true;
            }
        }
        return false;
    }

    private static void articulationPoint(ArrayList<Edge> graph[], int curr, int par, int time, int dis[], int low[], boolean isArticulation[], boolean vis[]) {
        vis[curr] = true;
        dis[curr] = low[curr] = ++time;
        int child = 0;
        for (int i = 0; i < graph[curr].size(); i++) {
            int v = graph[curr].get(i).dest;
            if (!vis[v]) {
                articulationPoint(graph, v, curr, time + 1, dis, low, isArticulation, vis);
                low[curr] = Math.min(low[curr], low[v]);
                if (dis[curr] <= low[v] && par != -1) {
                    isArticulation[curr] = true;
                }
                child++;
            } else {
                low[curr] = Math.min(low[curr], dis[v]);
            }
        }
        if (par == -1 && child > 1) {
            isArticulation[curr] = true;
        }

    }
    
    public int dijistraAlgo(ArrayList<Edge> graph[], int src) {
        int V = graph.length;
        int dist[] = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean vis[] = new boolean[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));
        while(!pq.isEmpty()) {
            Pair p = pq.remove();
            int u = p.n;
            if(!vis[u]) {
                vis[u] = true;
                for(int i=0; i<graph[u].size(); i++) {
                    int v = graph[u].get(i).dest;
                    int w = graph[u].get(i).wet;
                    if(dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }
        return dist[dist.length-1];
    }

    public static void main(String args[]) {
        GraphDirected gd = new GraphDirected();
        int V = 6;
        ArrayList<Edge> graph[] = new ArrayList[V];
        gd.createGraph(graph);
        System.out.println(gd.dijistraAlgo(graph, 0));
        boolean vis[] = new boolean[V];
        int dis[] = new int[V];
        int low[] = new int[V];
        boolean isArticulation[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                articulationPoint(graph, i, -1, 0, low, dis, isArticulation, vis);
            }
        }

    }
}
