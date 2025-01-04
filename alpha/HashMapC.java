
package alpha;

import java.util.LinkedList;
import java.util.ArrayList;
/**
 *
 * @author rinku
 */
public class HashMapC {
    
    public int subarraySumEqualToK(int arr[], int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        for(int i=0; i<arr.length; i++) {
            sum += arr[i];
            if(map.containsKey(sum)) {
                if(sum == k) {
                    count++;
                }
            } else {
                map.put(sum, i);
            }
        }
        return count;
    }
    public int largestSubarraySumZero(int arr[]) {
        int length = 0;
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<arr.length; i++) {
            sum += arr[i];
            if(map.containsKey(sum)) {
                int curr = i - map.get(sum);
                length = Math.max(curr, length);
            } else {
                map.put(sum, i);
            }
        }
        return length;
    }
    public void findItineraryFromTikets(String arr[][]) {
        HashMap<String, String> fromTo = new HashMap<>();
        for(String str[] : arr) {
            fromTo.put(str[0], str[1]);
        }
        HashMap<String, String> toFrom = new HashMap<>();
        for(String str : fromTo.keySet()) {
            toFrom.put(fromTo.get(str), str);
        }
        String start = null;
        for(String str : fromTo.keySet()) {
            if(!toFrom.containsKey(str)) {
                start = str;
            }
        }
        System.out.print(start);
        for(String str : fromTo.keySet()) {
            System.out.print(" -> " + fromTo.get(start));
            start = fromTo.get(start);
        }
        System.out.println();
    }
    public int intersection(int arr1[], int arr2[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<arr1.length; i++) {
            map.put(arr1[i], 0);
        }
        int count = 0;
        for(int i=0; i<arr2.length; i++) {
            if(map.containsKey(arr2[i])) {
                count++;
                map.remove(arr2[i]);
            }
        }
        return count;
    }
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {
            return false;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for(int i=0; i<t.length(); i++) {
            char ch = t.charAt(i);
            if(map.containsKey(ch)) {
                if(map.get(ch) == 1) {
                    map.remove(ch);
                } else {
                    map.put(ch, map.getOrDefault(ch, 0) -1);
                }
            } else {
                return false;
            }
        }
        return map.isEmpty();
    }
    public static class HashMap <K, V> {
        private class Node {
            K key;
            V value;
            Node(K key, V value) {
                this.key = key;
                this.value = value;
            }
        }
        private int n;
        private int N;
        private LinkedList<Node> buckets[];
        @SuppressWarnings("unchecked")
        HashMap() {
            this.N = 4;
            this.buckets = new LinkedList[N];
            for(int i=0; i<N; i++) {
                buckets[i] = new LinkedList<>();
            }
        }
        private int hashFuction(K key) {
            return Math.abs(key.hashCode()) % N;
        }
        private int searchInBucket(K key, int bi) {
            LinkedList<Node> ll = buckets[bi];
            for(int i=0; i<ll.size(); i++) {
                if(key.equals(ll.get(i).key)) {
                    return i;
                }
            }
            return -1;
        }
        private void rehash() {
            LinkedList<Node> oldBucket[] = buckets;
            N = N * 2;
            n = 0;
            buckets = new LinkedList[N];
            for(int i=0; i<N; i++) {
                buckets[i] = new LinkedList<>();
            }
            for(LinkedList<Node> ll : oldBucket) {
                for(int j=0; j<ll.size(); j++) {
                    put(ll.get(j).key, ll.get(j).value);
                }
            }
        }
        
        public void put(K key, V value) {
            int bi = hashFuction(key);
            int di = searchInBucket(key, bi);
            if(di == -1) {
                buckets[bi].add(new Node(key, value));
                n++;
            } else {
                buckets[bi].get(di).value = value;
            }
            double lamda = n/N;
            if(lamda > 2.0) {
                System.out.println("rehash at " + n + " to " + N);
                rehash();
            }
        }
        
        public V get(K key) {
            int bi = hashFuction(key);
            int di = searchInBucket(key, bi);
            if(di == -1) {
                return null;
            } else {
                return buckets[bi].get(di).value;
            }
        }
        
        public V remove(K key) {
            int bi = hashFuction(key);
            int di = searchInBucket(key, bi);
            if(di == -1) {
                return null;
            } else {
                n--;
                return buckets[bi].remove(di).value;
            }
        }
        
        public V getOrDefault(K key, V value) {
            int bi = hashFuction(key);
            int di = searchInBucket(key, bi);
            if(di == -1) {
                return value;
            } else {
                return buckets[bi].get(di).value;
            }
        }
        
        public ArrayList<K> keySet() {
            ArrayList<K> list = new ArrayList<>();
            for(LinkedList<Node> ll : buckets) {
                for(Node n : ll) {
                    list.add(n.key);
                }
            }
            return list;
        }
        
        public boolean containsKey(K key) {
            int bi = hashFuction(key);
            int di = searchInBucket(key, bi);
            return di != -1;
        }
        
        public boolean isEmpty() {
            return n==0;
        }
    }
}
