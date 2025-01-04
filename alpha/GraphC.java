package alpha;

import java.util.*;

public class GraphC {

    static class Edge {

        int scr, dest, wet;

        public Edge(int s, int d, int w) {
            this.scr = s;
            this.dest = d;
            this.wet = w;
        }

        public Edge(int s, int d) {
            this.scr = s;
            this.dest = d;
        }
    }

    static class Pair implements Comparable<Pair> {

        int n;
        int w;

        Pair(int n, int w) {
            this.n = n;
            this.w = w;
        }

        @Override
        public int compareTo(Pair ob) {
            return this.w - ob.w;
        }
    }

    static int count;

    private boolean isSafe(int matrix[][], int i, int j, boolean vis[][]) {
        int n = matrix.length;
        int m = matrix[0].length;
        return (i >= 0 && i < n && j >= 0 && j < m && matrix[i][j] == 1 && !vis[i][j]);
    }

    private void dfs(int matrix[][], int i, int j, boolean vis[][]) {
        vis[i][j] = true;
        int dir[][] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        for (int k = 0; k < 8; k++) {
            int r = i + dir[k][0];
            int c = j + dir[k][1];
            if (isSafe(matrix, r, c, vis)) {
                count++;
                dfs(matrix, r, c, vis);
            }
        }
    }

    private void bfs(int matrix[][], int i, int j, boolean vis[][]) {
        Queue<Pair> qu = new LinkedList<>();
        int dir[][] = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};
        qu.add(new Pair(i, j));
        while (!qu.isEmpty()) {
            Pair p = qu.remove();
            int x = p.n, y = p.w;
            if (!vis[x][y]) {
//                vis[x][y] = true;
                for (int k = 0; k < 8; k++) {
                    int r = x + dir[k][0];
                    int c = y + dir[k][1];
                    if (isSafe(matrix, r, c, vis)) {
                        count++;
                        vis[r][c] = true;
                        qu.add(new Pair(r, c));
                    }
                }
            }
        }
    }

    public void largestMatrixInBooleanRegion(int matrix[][]) {
        int n = matrix.length;
        int m = matrix[0].length;
        int result = 0;
        boolean vis[][] = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1 && !vis[i][j]) {
                    count = 1;
                    bfs(matrix, i, j, vis);
                    result = Math.max(result, count);
                }
            }
        }
        System.out.println("Largest Martix in boolean REGION = " + result);
    }

    private static void calculateIndegree(ArrayList<Edge> graph[], int indegree[]) {
        for (ArrayList<Edge> list : graph) {
            for (int j = 0; j < list.size(); j++) {
                int neibour = list.get(j).dest;
                indegree[neibour]++;
            }
        }
    }

    private boolean isCycleUtil(ArrayList<Edge> graph[], int curr, boolean vis[], int par[]) {
        Queue<Integer> qu = new LinkedList<>();
        qu.add(curr);
        while (!qu.isEmpty()) {
            int u = qu.remove();
            for (int i = 0; i < graph[u].size(); i++) {
                int v = graph[curr].get(i).dest;
                if (!vis[v]) {
                    vis[v] = true;
                    qu.add(v);
                    par[v] = curr;
                } else {
                    if (par[u] != v) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean cycleBFS(ArrayList<Edge> graph[]) {
        int V = graph.length;
        int par[] = new int[V];
        Arrays.fill(par, -1);
        boolean vis[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i] && isCycleUtil(graph, i, vis, par)) {
                return true;
            }
        }
        return false;
    }

    public void createGraph(ArrayList<Edge> graph[]) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList();
        }
        graph[0].add(new Edge(0, 1, 10));
        graph[0].add(new Edge(0, 2, 15));
        graph[0].add(new Edge(0, 3, 30));
        graph[1].add(new Edge(1, 0, 10));
        graph[1].add(new Edge(1, 3, 40));
        graph[2].add(new Edge(2, 0, 15));
        graph[2].add(new Edge(2, 3, 50));
        graph[3].add(new Edge(3, 1, 40));
        graph[3].add(new Edge(3, 2, 50));

    }

    public boolean isBipartite(ArrayList<Edge> graph[]) {
        int V = graph.length;
        int color[] = new int[V];
        Arrays.fill(color, -1);
        Queue<Integer> qu = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                qu.add(i);
                color[i] = 0;
                while (!qu.isEmpty()) {
                    int curr = qu.remove();
                    for (int j = 0; j < graph[curr].size(); j++) {
                        int neibour = graph[curr].get(j).dest;
                        if (color[neibour] == -1) {
                            color[neibour] = color[curr] == 0 ? 1 : 0;
                            qu.add(neibour);
                        } else if (color[neibour] == color[curr]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void bfs(ArrayList<Edge> graph[], int start, boolean visited[]) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            int curr = q.remove();
            if (!visited[curr]) {
                visited[curr] = true;
                System.out.print(curr + " ");
                for (int i = 0; i < graph[curr].size(); i++) {
                    Edge e = graph[curr].get(i);
                    if (!visited[e.dest]) {
                        q.add(e.dest);
                    }
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge> graph[], int curr, boolean visited[]) {
        System.out.print(curr + " ");
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited);
            }
        }
    }

    private static void printAllPaths(ArrayList<Edge> graph[], int curr, String path, int tar, boolean visited[]) {
        if (curr == tar) {
            System.out.println(path + tar);
            return;
        }
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            int neibour = graph[curr].get(i).dest;
            if (!visited[neibour]) {
                printAllPaths(graph, neibour, path + curr + " ", tar, visited);
            }
        }
        visited[curr] = false;
    }

    public boolean isCycleUndirected(ArrayList<Edge> graph[], int curr, int par, boolean visited[]) {
        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edge e = graph[curr].get(i);
            if (visited[e.dest] && e.dest != par) {
                return true;
            } else if (!visited[e.dest] && isCycleUndirected(graph, e.dest, curr, visited)) {
                return true;
            }
        }
        return false;
    }

    private static void MSTprimAlgo(ArrayList<Edge> graph[], boolean visited[], int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int cost = 0;
        pq.add(new Pair(src, 0));
        while (!pq.isEmpty()) {
            Pair p = pq.remove();
            if (!visited[p.n]) {
                visited[p.n] = true;
                cost += p.w;
                for (int i = 0; i < graph[p.n].size(); i++) {
                    Edge e = graph[p.n].get(i);
                    if (!visited[e.dest]) {
                        pq.add(new Pair(e.dest, e.wet));
                    }
                }
            }
        }
        System.out.println(cost);
    }
    

    private static void findBridge(ArrayList<Edge> graph[], int curr, int sortTime[], int destTime[], boolean vis[], int time, int par) {
        vis[curr] = true;
        sortTime[curr] = destTime[curr] = ++time;
        for (int i = 0; i < graph[curr].size(); i++) {
            int neibour = graph[curr].get(i).dest;
            if (par == neibour) {
                continue;
            } else if (vis[neibour]) {
                sortTime[curr] = Math.min(sortTime[curr], destTime[neibour]);
            } else {
                findBridge(graph, neibour, sortTime, destTime, vis, time, curr);
                sortTime[curr] = Math.min(sortTime[curr], sortTime[neibour]);
                if (destTime[curr] < sortTime[neibour]) {
                    System.out.println("Bridge : " + curr + " ---> " + neibour);
                }
            }
        }
    }

    private static void tarsonAlgorithm(ArrayList<Edge> graph[], int V) {
        int sortTime[] = new int[V];
        int destTime[] = new int[V];
        boolean vis[] = new boolean[V];
        int time = 0;
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                findBridge(graph, i, sortTime, destTime, vis, time, -1);
            }
        }
    }
    
    private int primAlgorithm(ArrayList<Edge> graph[], int src) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int V = graph.length;
        boolean vis[] = new boolean[V];
        pq.add(new Pair(0, 0));
        int cost = 0;
        while(!pq.isEmpty()) {
            Pair p = pq.remove();
            if(!vis[p.n]) {
                vis[p.n] = true;
                cost += p.w;
                for(int i=0; i<graph[p.n].size(); i++) {
                    Edge neighbour = graph[p.n].get(i);
                    if(!vis[neighbour.dest]) {
                        pq.add(new Pair(neighbour.dest, neighbour.wet));
                    }
                }
            }
        }
//        System.out.println("Cost is MST : " + cost);
        return cost;
    }

    public static void main(String args[]) {
        int V = 6;
        GraphC gp = new GraphC();
        ArrayList<Edge> graph[] = new ArrayList[V];
        gp.createGraph(graph);
        gp.primAlgorithm(graph, 0);
//        System.out.println(dijistraAlgo(graph, 0));
        //tarsonAlgorithm(graph, V);
        //printAllPaths(graph, 0, "", 5, new boolean[V]);
//        MSTprimAlgo(graph, new boolean[V], 0);
    }
}
