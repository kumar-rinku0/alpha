package alpha;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;
import java.util.Collections;

public class GreedyC {

    // solution based on greedy approch/Algorithm
    public static int activitySelection(int start[], int end[], ArrayList<Integer> list) {
        int activity[][] = new int[start.length][3];
        for (int i = 0; i < start.length; i++) {
            activity[i][0] = i;
            activity[i][1] = start[i];
            activity[i][2] = end[i];
        }
        Arrays.sort(activity, Comparator.comparingDouble(o -> o[2]));
        int maxActivity = 0;
        maxActivity = 1;
        list.add(activity[0][0]);
        int lastEnd = activity[0][2];
        for (int i = 1; i < activity.length; i++) {
            if (activity[i][1] >= lastEnd) {
                maxActivity++;
                list.add(activity[i][0]);
                lastEnd = activity[i][2];
            }
        }
        return maxActivity;
    }

    public static int activitySelection(int start[], int end[]) {

        // sort besed on ending time {alrady sorted to ending time }
        // select activity or count activitys
        int maxActivity = 0;
        ArrayList<Integer> list = new ArrayList<>();
        maxActivity = 1;
        list.add(0);
        int lastEnd = end[0];
        for (int i = 1; i < end.length; i++) {
            if (start[i] >= lastEnd) {
                maxActivity++;
                list.add(i);
                lastEnd = end[i];
            }
        }
        for (int i : list) {
            System.out.print("activity" + i + " ");
        }
        return maxActivity;
    }

    static class ComparatorClass implements Comparator<Pair> {

        @Override
        public int compare(Pair ob1, Pair ob2) {
            return ob1.b - ob2.b;
        }
    }

    public static int maxLengthChainOfPair(ArrayList<Pair> list) {
        int count = 0;
        Collections.sort(list, new ComparatorClass());
//        Collections.sort(list);
        int lastEnd = list.get(0).b;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).a >= lastEnd) {
                count++;
            }
        }
        return count;
    }

    public static double fractinalKnapsack(int cap, int weight[], int value[]) {
        int max = 0;
        double retio[][] = new double[value.length][2];
        for (int i = 0; i < value.length; i++) {
            retio[i][0] = i;
            retio[i][1] = value[i] / (double) weight[i];
        }
        int totalCapacity = cap;
        Arrays.sort(retio, Comparator.comparingDouble(o -> o[1]));
        for (int i = retio.length - 1; i >= 0; i--) {
            int idx = (int) retio[i][0];
            if (totalCapacity >= weight[idx]) {
                totalCapacity -= weight[idx];
                max += value[idx];
            } else {
                max += (retio[i][1] * totalCapacity);
                break;
            }
        }
        return max;
    }

    public static ArrayList<Integer> indianCoinChange(Integer coins[], int amount) {
        Arrays.sort(coins, Collections.reverseOrder());
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= amount) {
                while (coins[i] <= amount) {
                    list.add(coins[i]);
                    amount -= coins[i];
                }
            }
        }
        return list;
    }

    static class Pair implements Comparable<Pair> {

        int a;
        int b;

        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair ob) {
            return this.b - ob.b;
        }
    }

    static class Job implements Comparable<Job> {

        String job;
        int deadline;
        double profit;

        Job(String j, int d, double p) {
            this.job = j;
            this.deadline = d;
            this.profit = p;
        }

        @Override
        public int compareTo(Job ob) {
            return (int) (ob.profit - this.profit);
        }
    }

    public static ArrayList<String> jobSequncing(ArrayList<Job> list) {
        Collections.sort(list);
        ArrayList<String> ansList = new ArrayList<>();
        int time = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).deadline > time) {
                time++;
                ansList.add(list.get(i).job);
            }
        }
        return ansList;
    }

    public static int chocolaProb(Integer hor[], Integer ver[]) {
        Arrays.sort(hor, Collections.reverseOrder());
        Arrays.sort(ver, Collections.reverseOrder());
        int h = 0, v = 0;
        int hp = 1, vp = 1;
        int cost = 0;
        while (h < hor.length && v < ver.length) {
            if (hor[h] <= ver[v]) {
                cost += (ver[v] * hp);
                vp++;
                v++;
            } else {
                cost += (hor[h] * vp);
                hp++;
                h++;
            }
        }
        while (v < ver.length) {
            cost += (ver[v] * hp);
            vp++;
            v++;
        }
        while (h < hor.length) {
            cost += (hor[h] * vp);
            hp++;
            h++;
        }
        return cost;
    }
    public static int maxBalancingSubstringPartition(String str) {
        if(str.length() == 0) {
            return 0;
        }
        int r = 0, l = 0, balancedStringCount = 0;
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == 'R') {
                r++;
            } else {
                l++;
            }
            if(r == l) {
                balancedStringCount++;
            }
        }
        return balancedStringCount;
    }
    public static int KthLargetOddNumInGivenRange(int L, int R, int K) {
        if(K > R && K < L) {
            return -1;
        }
        int count =0;
        for(int i=R; i>=L; i--) {
            if(i % 2 != 0) {
                count++;
            }
            if(count == K) {
                return i;
            }
        }
        return 0;
    }
    public static String smallestStringOfLengthAndSum(int n, int sum) {
        char arr[] = new char[n];
        Arrays.fill(arr, 'a');
        for(int i=n-1; i>=0; i--) {
            sum -= i;
            if(sum >= 0) {
                if(sum >= 26) {
                    arr[i] = 'z';
                    sum -= 26;
                } else {
                    arr[i] = (char)((sum-1) + 'a');
                    sum -= arr[i] - 'a' +1; // sum = 0;
                }
            } else {
                break;
            }
            sum += i;
        }
        return new String(arr);
    }
    // preches when price is highest and sell when price is lowest
    public static int bestTimeToBuySellStockOneTrasitionAllowed(int prices[]) {
        int maxProfit = 0;
        int buyStock = prices[0];
        for(int i=1; i<prices.length; i++) {
            int profit = prices[i] - buyStock;
            if(profit > maxProfit) {
                maxProfit = profit;
            }
            if(buyStock > prices[i]) {
                buyStock = prices[i];
            }
        }
        return maxProfit;
    }
    public static void main(String args[]) {
        int prices[] = {7, 1, 5, 3, 6, 4 };
        System.out.println(GreedyC.bestTimeToBuySellStockOneTrasitionAllowed(prices));
        
//        System.out.println(GreedyC.KthLargetOddNumInGivenRange(-3, 3, 1));
//        System.out.println(GreedyC.smallestStringOfLengthAndSum(5, 42));
        
//        System.out.println(GreedyC.maxBalancingSubstringPartition("LRRRRLLRLLRL"));
        
//        Integer hor[] = {2, 1, 3, 1, 4 };
//        Integer ver[] = {4, 1, 2 };
//        int val = chocolaProb(hor, ver);
//        System.out.println(val);
        
//        ArrayList<Job> list = new ArrayList<>();
//        list.add(new Job("job 1", 4, 20));
//        list.add(new Job("job 2", 1, 10));
//        list.add(new Job("job 3", 1, 40));
//        list.add(new Job("job 4", 1, 30));
//        ArrayList<String> ansList = jobSequncing(list);
//        System.out.println(ansList);

//        Integer coins[] = {1, 2, 5, 10, 20, 50, 100, 200, 500, 2000};
//        ArrayList<Integer> list = indianCoinChange(coins, 1359);
//        System.out.println(list.size());
//        System.out.println(list);
//        ArrayList<Pair> list = new ArrayList<>();
//        list.add(new Pair(39, 60));
//        list.add(new Pair(5, 24));
//        list.add(new Pair(5, 28));
//        list.add(new Pair(27, 40));
//        list.add(new Pair(50, 90));
//        int val = maxLengthChainOfPair(list);
//        System.out.println(val);
//        list.add(new Pair(5, 24));
//        int value[] = {120, 60, 100};
//        int weight[] = {30, 10, 20};
//        int cap = 50;
//        double val = fractinalKnapsack(cap, weight, value);
//        System.out.println(val);
//        int start[] = {1, 3, 0, 5, 8, 5 };
//        int end[] = {2, 4, 6, 7, 9, 9 };
//        ArrayList<Integer> list = new ArrayList<>();
//        System.out.println(activitySelection(start, end, list));
//        System.out.println(list);
    }
}
