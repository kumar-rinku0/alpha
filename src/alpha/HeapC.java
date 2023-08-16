package alpha;

import java.util.*;

/**
 *
 * @author rinku
 */
public class HeapC {

    static class Pair implements Comparable<Pair> {

        int a;
        int b;
//        int c;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
//            this.c = c;
        }

        @Override
        public int compareTo(Pair ob) {
            return this.b - ob.b;
        }
    }

    // qutions
    public int minimumTimeRequiredToFillNslots(int n, int k, int arr[]) {
        int time = 0;
        Queue<Integer> qu = new LinkedList<>();
        boolean vis[] = new boolean[n + 1];
        for (int i = 0; i < k; i++) {
            qu.add(arr[i]);
            vis[arr[i]] = true;
        }
        while (!qu.isEmpty()) {
            for (int i = 0; i < qu.size(); i++) {
                int val = qu.remove();
                if (val - 1 >= 1 && !vis[val - 1]) {
                    vis[val - 1] = true;
                    qu.add(val - 1);
                }
                if (val + 1 <= n && !vis[val + 1]) {
                    vis[val + 1] = true;
                    qu.add(val + 1);
                }
            }
            time++;
        }
        return time - 1;
    }
    public int pathWithMINEffort(int cell[][]) { // dp, graph, dijikastra algorithm, PriorityQueue
        int n = cell.length;
        int m = cell[0].length;
        int dir[] = {-1, 1, 0, 0};
        int dic[] = {0, 0, -1, 1};
        int dist[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        dist[0][0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0, 0, cell[0][0]});
        while (!pq.isEmpty()) {
            int pair[] = pq.remove();
            int i = pair[0], j = pair[1];
            if (i == n - 1 && j == m - 1) {
                break;
            }
            for (int k = 0; k < 4; k++) {
                int r = i + dir[k];
                int c = j + dic[k];
                if (r < 0 || r >= n || c >= n || c < 0) {
                    continue;
                }
                int cost = pair[2] + cell[r][c];
                if(cost < dist[r][c])
                    pq.add(new int[] { r, c, dist[r][c] = cost });
            }
        }
        return dist[n-1][m-1];
    }
    static class Point {
        Pair coordinate;
        int distsq;
        Point(Pair c, int d) {
            this.coordinate = c;
            this.distsq = d;
        }
    }
    public void kNearbyCars(Pair arr[], int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> (a.distsq - b.distsq));
        for(Pair i : arr) {
            int distsq = i.a * i.a + i.b * i.b;
            pq.add(new Point(new Pair(i.a, i.b), distsq)); 
        }
        System.out.println("Coordinates Are : ");
        for(int i=0; i<k; i++) {
            Pair coordinate = pq.remove().coordinate;
            System.out.println("coordinate -> (" + coordinate.a + ", " + coordinate.b + ")");
        }
    }
    public void slidingWindowMax(int arr[], int k) {
        PriorityQueue<Pair> pq = new PriorityQueue<>(Collections.reverseOrder());
        int newarr[] = new int[arr.length - k + 1];
        for (int i = 0; i < k; i++) {
            pq.add(new Pair(i, arr[i]));
        }
        newarr[0] = pq.peek().b;
        for (int i = k; i < arr.length; i++) {
            while (!pq.isEmpty() && pq.peek().a <= (i - k)) {
                pq.remove();
            }
            pq.add(new Pair(i, arr[i]));
            newarr[i - k + 1] = pq.peek().b;
        }
        for (int i = 0; i < newarr.length; i++) {
            System.out.print(newarr[i] + " ");
        }
        System.out.println();
    }

    public int connectRopes(int arr[]) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            pq.add(arr[i]);
        }
        int cost = 0;
        while (pq.size() >= 2) {
            int rope1 = pq.remove();
            int rope2 = pq.remove();
            int newrope = rope1 + rope2;
            cost += newrope;
            pq.add(newrope);
            System.out.println(newrope);
        }
        if (!pq.isEmpty()) {
            pq.remove();
        }
        return pq.isEmpty() ? cost : -1;
    }

    public void weakestSoldier(int arr[][]) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < arr[0].length; j++) {
                if (arr[i][j] == 1) {
                    count++;
                }
            }
            pq.add(new Pair(i, count));
        }
        while (!pq.isEmpty()) {
            System.out.print(pq.remove().a + " ");
        }
        System.out.println();
    }

    static class Heap {

        ArrayList<Integer> list = new ArrayList<>();

        public void add(int val) {
            list.add(val);

            int x = list.size() - 1;
            int par = (x - 1) / 2;

            while (list.get(x) < list.get(par)) {
                int temp = list.get(x);
                list.set(x, list.get(par));
                list.set(par, temp);

                x = par;
                par = (x - 1) / 2;
            }
        }

        public int peek() {
            return list.get(0);
        }

        private void heapify(int i) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int minIdx = i;
            if (left < list.size() && list.get(left) < list.get(minIdx)) {
                minIdx = left;
            }
            if (right < list.size() && list.get(right) < list.get(minIdx)) {
                minIdx = right;
            }
            if (minIdx != i) {
                Integer temp = list.get(minIdx);
                list.set(minIdx, list.get(i));
                list.set(i, temp);

                heapify(minIdx);
            }
        }

        public int remove() {
            int data = list.get(0);
            // swap from last 
            int temp = list.get(list.size() - 1);
            list.set(list.size() - 1, list.get(0));
            list.set(0, temp);

            // remove last 
            list.remove(list.size() - 1);

            // heapify
            heapify(0);
            return data;
        }

        public boolean isEmpty() {
            return list.isEmpty();
        }

    }

    public void heapify(int arr[], int i, int n) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int minIdx = i;
        if (left < n && arr[left] < arr[minIdx]) {
            minIdx = left;
        }
        if (right < n && arr[right] < arr[minIdx]) {
            minIdx = right;
        }
        if (minIdx != i) {
            int temp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = temp;

            heapify(arr, minIdx, n);
        }
    }

    public void heapSort(int arr[]) {
        int n = arr.length;
        for (int i = n / 2; i >= 0; i--) {
            heapify(arr, i, n);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, 0, i);
        }
    }
}
