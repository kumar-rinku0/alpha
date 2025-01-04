
package alpha;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ArraylistC {
    // O(n^2)
    private static ArrayList<Integer> lonelyNum(ArrayList<Integer> list) {
        ArrayList<Integer> newList = new ArrayList<>();
        for(int i=0; i<list.size(); i++) {
            int itam = list.get(i);
            if(!list.contains(itam+1) && !list.contains(itam-1)) {
                int data = list.remove(i);
                if(!list.contains(data)) {
                    newList.add(data);
                }
            }
        }
        return newList;
    }
    // O(n * long n)
    private static ArrayList<Integer> lonelyNum(ArrayList<Integer> list, int o) {
        ArrayList<Integer> newlist = new ArrayList<>();
        Collections.sort(list); // O(n * long n)
        for(int i=1; i<list.size()-1; i++) {
            if(list.get(i-1) + 1 < list.get(i) && list.get(i) + 1 < list.get(i+1)) {
                newlist.add(list.get(i));
            }
        } // O(n)
        if(list.size() == 1) {
            newlist.add(list.get(0));
        }
        else if(list.size() > 1) {
            if(list.get(0)+1 < list.get(1)) {
                newlist.add(list.get(0));
            }
            if(list.get(list.size()-2)+1 < list.get(list.size()-1)) {
                newlist.add(list.get(list.size()-1));
            }
        }
        return newlist;
    }
    private static int mostFrequent(ArrayList<Integer> list, int key) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<list.size()-1; i++) {
            if(key == list.get(i)) {
                map.put(list.get(i+1), map.getOrDefault(list.get(i+1), 0)+1);
            }
        }
        int feq = -1;
        int ans = 0;
        for(int i : map.keySet()) {
            if(feq < map.get(i)) {
                feq = map.get(i);
                ans = i;
            }
        }
        return ans;
    }
    // O(n^2) naive approch
    private static int[] beautifulArrayList(int n) {
        ArrayList<Integer> newlist = new ArrayList<>();
        newlist.add(1);
        for(int i=2; i<=n; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for(Integer j : newlist) {
                if(2 * j <= n) {
                    temp.add(2 * j);
                }
            }
            for(Integer j : newlist) {
                if(2 * j-1 <= n) {
                    temp.add(2 * j-1);
                }
            }
            newlist = temp;
        }
        int arr[] = new int[newlist.size()];
        int j =0;
        for(Integer i : newlist) {
            arr[j++] = i;
        }
        return arr;
    }
    // O(n) devide&conquer
    private static ArrayList<Integer> beautifulArrayList2(int n) {
        ArrayList<Integer> newlist = new ArrayList<>();
        devideconquer(1, 1, newlist, n); // static method 
        return newlist;
    }
    private static void devideconquer(int start, int inc, ArrayList<Integer> list, int n) {
        if(start + inc > n) {
            list.add(start);
            return;
        }
        devideconquer(start, 2 * inc , list, n);
        devideconquer(start+inc, 2 * inc, list, n);
    }
    private static void changeRowColTo0(int arr[][]) {
        int n = arr.length;
        int m = arr[0].length;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j] == 0) {
                    // loop in col
                    for(int k=0; k<m; k++) {
                        arr[i][k] = -1;
                    }
                    // loop in row
                    for(int k=0; k<n; k++) {
                        arr[k][j] = -1;
                    }
                }
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j] == -1) {
                   arr[i][j] = 0;
                }
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    private static void nextGreaterEle(ArrayList<Integer> list) {
        int i=list.size()-2;
        while(i >= 0 && list.get(i) > list.get(i+1)) {
            i--;
        }
        int j=list.size()-1;
        while(i >= 0 && j >= 0 && list.get(i) > list.get(j)) {
            j--;
        }
        Collections.swap(list, i, j);
        reverseList(list, i+1, list.size()-1);
    }
    private static void reverseList(ArrayList<Integer> list, int i, int j) {
        while(i < j) {
            Collections.swap(list, i++, j--);
        }
    }
    public static void main(String args[]) {
        
//        int arr[][] = {{1,0,1,1}, {1,1,1,1}, {1,1,1,1}, {1,1,1,1}, {1,1,1,0}};
//        for(int i[] : arr) {
//            for(int j : i) {
//                System.out.print(j+" ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        changeRowColTo0(arr);
        
//        ArrayList<Integer> list = beautifulArrayList2(4);
//        System.out.println(list);
//        
//        int arr[] = beautifulArrayList(4);
//        System.out.println(Arrays.toString(arr));
//        
        ArrayList<Integer> baselist = new ArrayList<>();
        baselist.add(1);
        baselist.add(3);
        baselist.add(5);
        baselist.add(4);
        baselist.add(2);
        System.out.println(baselist);
        nextGreaterEle(baselist);
        System.out.println(baselist);
//        int val = mostFrequent(baselist, 2);
//        ArrayList<Integer> list = lonelyNum(baselist, 0);
//        System.out.println(val);


    }
}
