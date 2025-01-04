
package alpha;

import java.util.Arrays;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Objects;

public class StackC {
    static class Node {
        int data;
        Node next;
        Node(int data) {
            this.data= data;
            this.next = null;
        }
    }
    static class Stack {
        static Node head;
        // isEmpty()
        public boolean isEmpty() {
            return head == null;
        }
        // push();
        public void push(int data) {
            Node newNode = new Node(data);
            if(isEmpty()) {
                head = newNode;
                return;
            }
            newNode.next = head;
            head = newNode;
        }
        // pop();
        public int pop() {
            if(isEmpty()) {
                return -1;
            }
            int top = head.data;
            head = head.next;
            return top;
        }
        // peek();
        public int peek() {
            if(isEmpty()) {
                return -1;
            }
            return head.data;
        } 
        public void pushAtBottom(int data) {
            if(isEmpty()) {
                push(data);
                return;
            }
            int top = pop();
            pushAtBottom(data);
            push(top);
        }
    }
    public static void stackSpan(int stocks[], int span[]) {
        Stack st = new Stack();
        st.push(0); // pushing index of stocks array
        span[0] = 1;
        for(int i=1; i<stocks.length; i++) {
            while(!st.isEmpty() && stocks[i] >= stocks[st.peek()]) {
                st.pop();
            }
            span[i] = st.isEmpty() ? i+1 : i-st.peek();
            st.push(i);
        }
    }
    public static void nextGreater(int arr[], int nextValue[]) {
        Stack st = new Stack();
        st.push(arr.length-1);
        nextValue[arr.length-1] = -1;
        for(int i=arr.length-2; i>=0; i--) {
            while(!st.isEmpty() && arr[i] >= arr[st.peek()]) {
                st.pop();
            }
            nextValue[i] = st.isEmpty() ? -1 : arr[st.peek()];
            st.push(i);
        }
    }
    
    public static void nextGreaterBruteForce(int arr[], int nextValue[]) {
        for(int i=0; i<arr.length; i++) {
            for(int j=i+1; j<arr.length; j++) {
                if(arr[i] < arr[j]) {
                    nextValue[i] = arr[j];
                    break;
                }
                if(j == arr.length-1) {
                    nextValue[i] = -1;
                }
            }
            if(i == arr.length-1) {
                nextValue[i] = -1;
            }
        }
    }
    
    public static void main(String args[]) {
//        int stocks[] = {7, 8, 1, 4};
//        int span[] = new int[stocks.length];
//        nextGreater(stocks, span);
//        System.out.println(Arrays.toString(span));
//        int arr[] = {2, 1, 5, 6, 2, 3};
//        int flag = OuterClass.maxAreaRecHistogram(arr);
//        System.out.println(flag);
//        Stack st = new Stack();
//        for(int i=0; i<20; i++) {
//            st.push(i);
//        }
//        st.pushAtBottom(20);
//        while(!st.isEmpty()) {
//            System.out.println(st.pop());
//        }
    }
}
class OuterClass {
    public static boolean validPeranthisis(String str) {
        Stack<Character> st = new Stack<>();
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == '(' || ch == '{' || ch == '[') {
                st.push(ch);
            } else {
                if(st.isEmpty()) return false;
                else if((ch == ')' && st.peek() == '(') ||(ch == '}' && st.peek() == '{') || (ch == ']' && st.peek() == '[') ) {
                    st.pop();
                } else {
                    return false;
                }
            }
        }
        return st.isEmpty();
    }
    public static boolean duplicateParathisis(String str) {
        Stack<Character> st = new Stack<>();
        for(int i=0; i<str.length(); i++) {
            char ch = str.charAt(i);
            if(!st.isEmpty() && ch == ')') {
                int count = 0;
                while(!st.isEmpty() && st.peek() != '(') {
                    st.pop();
                    count++;
                }
                if(count == 0) return true;
                else st.pop();
            } else {
                st.push(ch);
            }
        }
        return !st.isEmpty();
    }
    private static int[] smallerInLeft(int arr[]) {
        Stack<Integer> st = new Stack<>();
        int smallerLeft[] = new int[arr.length];
        for(int i=0; i<arr.length; i++) {
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            smallerLeft[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }
        return smallerLeft;
    }
    private static int[] smallerInRight(int arr[]) {
        Stack<Integer> st = new Stack<>();
        int smallerRight[] = new int[arr.length];
        for(int i=arr.length-1; i>=0; i--) {
            while(!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            smallerRight[i] = st.isEmpty() ? arr.length : st.peek();
            st.push(i);
        }
        return smallerRight;
    }
    public static int maxAreaRecHistogram(int arr[]) {
//         to find left and right smaller indx
        int leftSmaller[] = smallerInLeft(arr); 
        int rightSmaller[] = smallerInRight(arr);
        int area[] = new int[arr.length];
        int max = 0;
        for(int i=0; i<arr.length; i++) {
            area[i] = arr[i] * (rightSmaller[i] - leftSmaller[i] - 1);
            max = Math.max(area[i], max);
        }
        return max;
    }
    
}

