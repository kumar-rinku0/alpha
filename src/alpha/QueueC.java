
package alpha;

import java.util.*;

public class QueueC {
    public void maximumOfSubarrayOfSizeK(int arr[], int k) { // overall time complexity -> O(n)
        Deque<Integer> dq = new LinkedList<>();
        int i=0;
        while(i < k) {
            while(!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) {
                dq.removeLast();
            }
            dq.add(i); // addLast O(1)
            i++;
        }
        while(i < arr.length) {
            System.out.print(arr[dq.peek()] + " ");
            while(!dq.isEmpty() && arr[dq.peek()] <= i-k) { // peekFirst Or removeFirst
                dq.remove();
            }
            while(!dq.isEmpty() && arr[dq.peekLast()] <= arr[i]) {
                dq.removeLast();
            }
            dq.add(i); // addLast
            i++;
        }
        System.out.println(arr[dq.peek()]);
    }
//    static class Queue {
//        static Stack<Character> st1 = new Stack<>();
//        static Stack<Character> st2 = new Stack<>();
//        
//        public boolean isEmpty() {
//            return st1.isEmpty();
//        }
//        public void add(char data) {
//            st1.push(data);
//        }
//        public int remove() {
//            if(st1.isEmpty()) {
//                System.out.println("Queue Empty!");
//                return -1;
//            }
//            while(!st1.isEmpty()) {
//                st2.add(st1.pop());
//            }
//            int val = st2.pop();
//            while(!st2.isEmpty()) {
//                st1.add(st2.pop());
//            }
//            return val;
//        }
//        public int peek() {
//            if(st1.isEmpty()) {
//                System.out.println("Queue Empty!");
//                return -1;
//            }
//            while(!st1.isEmpty()) {
//                st2.add(st1.pop());
//            }
//            int val = st2.peek();
//            while(!st2.isEmpty()) {
//                st1.add(st2.pop());
//            }
//            return val;
//        }
//    }
    static class Stack {
        static Queue<Integer> q1 = new LinkedList<>();
        static Queue<Integer> q2 = new LinkedList<>();
        public boolean isEmpty() {
            return q1.isEmpty() && q2.isEmpty();
        }
        public void push(int data) {
            if(q1.isEmpty()) {
                q1.add(data);
                while(!q2.isEmpty()) {
                    q1.add(q2.remove());
                }
            } else {
                q2.add(data);
                while(!q1.isEmpty()) {
                    q2.add(q1.remove());
                }
            }   
        }
        public int peek() {
            return q1.isEmpty() ? q2.peek() : q1.peek();
        }
        public int pop() {
            return q1.isEmpty() ? q2.remove() : q1.remove();
        }
    }
    public static void printInSteam(String str) {
       Queue<Character> q = new LinkedList<>();
       int feq[] = new int[26];
       for(int i=0; i<str.length(); i++) {
           char ch = str.charAt(i);
           q.add(ch);
           feq[ch-'a']++;
           while(!q.isEmpty() && feq[q.peek()-'a'] > 1) {
               q.remove();
           }
           if(q.isEmpty()) {
               System.out.print(-1 + " ");
           } else {
               System.out.print((char)q.peek() + " ");
           }
       }
    }
    public static void interLeave(Queue<Integer> q) {
        int size = q.size()/2;
        Queue<Integer> first = new LinkedList<>();
        while(size != 0) {
            first.add(q.remove());
            size--;
        }
        while(!first.isEmpty()) {
            q.add(first.remove());
            q.add(q.remove());
        }
    }
    public static void reverseQueue(Queue<Integer> q) {
        Stack st = new Stack();
        while(!q.isEmpty()) {
            st.push(q.remove());
        }
        while(!st.isEmpty()) {
            q.add(st.pop());
        }
    }
    public static void reverseKFirst(Queue<Integer> q) {
        Stack st = new Stack();
        int size = q.size()/2;
        while(size-- != 0) {
            st.push(q.remove());
        }
        while(!st.isEmpty()) {
            q.add(st.pop());
        }
        size = q.size()/2;
        while(size-- != 0) {
            q.add(q.remove());
        }
    }
    public static void printBinaryGenerate(int n) {
        Queue<String> qu = new LinkedList<>();
        qu.add("1");
        while(n-- != 0) {
            String s1 = qu.remove();
            System.out.println(s1+ " ");
            String s2 = s1;
            qu.add(s1+"0");
            qu.add(s2+"1");
        }
        
    }
//    public static void main(String args[]) {
////        printBinaryGenerate(5);
////        printInSteam("aabccxb");
//        Queue<Integer> q = new LinkedList<>();
//        for(int i=1; i<=10; i++) {
//            q.add(i);
//        }
//        System.out.println(q);
//        reverseKFirst(q);
//        System.out.println(q);
////        interLeave(q);
////        System.out.println(q);
////        reverseQueue(q);
////        System.out.println(q);
//    }
}
