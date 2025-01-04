
package alpha;

import java.util.Stack;

public class LinkedListC {
    public static class Node {
        int data;
        Node next;
        
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    static Node head;
    static Node tail;
    static int size;
    
    public void addFirst(int data) {
        // 1. create node or NewNode
        // 2. newnode.next -> head
        // 3. head -> newnode
        
        Node newNode = new Node(data);
        size++;
        if(head == null) {
            head = tail = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }
    public void addLast(int data) {
        // 1. create node or newnode
        // 2. tailnode ka next node -> newnode
        // 3. tail -> newnode
        Node newNode = new Node(data);
        size++;
        if(head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }
    public int removeFirst() {
        if(head == null) {
            System.out.println("Nothing To Remove!");
            return -1;
        }
        size--;
        if(head.next == null) {
            int val = head.data;
            head = tail = null;
            return val;
        }
        int val = head.data;
        head = head.next;
        return val;
    }
    public int removeLast() {
        if(head == null) {
            System.out.println("Nothing To Remove!");
            return -1;
        } else if(head.next == null) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        Node temp = head;
        while(temp.next.next != null) {
            temp = temp.next;
        }
        int val = temp.next.data;
        temp.next = null;
        size--;
        tail = temp;
        return val;
    }
    public void addIdx(int idx, int data) {
        if(idx == 0) {
            addFirst(data);
            return;
        }
        Node newNode = new Node(data);
        size++;
        Node temp = head;
        int i= 0;
        while(i < idx-1) {
            temp = temp.next;
            i++;
        }
        newNode.next = temp.next;
        temp.next = newNode;
    }
    public int itrativeSearch(int key) {
        int idx = 0;
        Node temp = head;
        while(temp != null) {
            if(temp.data == key) {
                return idx;
            }
            temp = temp.next;
            idx++;
        }
        return -1;
    }
    private int helper(Node head, int key) {
        if(head == null) {
            return -1;
        }
        if(head.data == key) {
            return 0;
        }
        int idx = helper(head.next, key);
        return idx == -1 ? -1 : idx+1;
    }
    public int recursiveSearch(int key) {
        return helper(head, key);
    }
    public void reverse() {
        Node prev = null;
        Node curr = tail = head;
        while(curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }
    public int removeNthNodeFromLast(Node head, int n) {
        int size = 0;
        Node temp = head;
        while(temp != null) {
            temp = temp.next;
            size++;
        }
        int idx = size - n;
        int i= 0;
        temp = head;
        while(i < idx -1) {
            temp = temp.next;
            i++;
        }
        int val = temp.next.data;
        temp.next = temp.next.next;
        return val;
    }
    private Node findMid(Node head) {
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    public boolean isPalindrome(Node head) {
        if(head == null || head.next == null) {
            return true;
        }
        // 1. find mid using slow-fast approch
        // 2. reverse second part of linkedlist
        // 3. check frist and second part in a loop
        Node mid = findMid(head);
        Node prev = null;
        Node curr = mid;
        Node next;
        while(curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        Node right = prev;
        Node left = head;
        
        while(right != null) {
            if(left.data != right.data) {
                return false;
            }
            right = right.next;
            left = left.next;
        }
        return true;
    }
    public boolean isCycle(Node head) {
        Node slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(fast == slow) {
                return true;
            }
        }
        return false;
    }
    public void removeCycle(Node head) {
        // 1. detect cycle
        // 2. slow will become head and { slow and fast will inc by one repidly while they not meet }
        // 3. set prev of fast node  -> null
        boolean flag = false;
        Node slow = head, fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                flag = true;
                break;
            }
        }
        if(!flag) {
            return;
        }
        slow = head;
        Node prev = null;
        while(slow != fast) {
            prev = fast;
            fast = fast.next;
            slow = slow.next;
        }
        prev.next = null;
    }
    private Node getMid(Node head) {
        Node slow = head;
        Node fast = head.next;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
    private Node merge(Node leftHead, Node rightHead) {
        Node mergeList = new Node(-1);
        Node temp = mergeList;
        while(leftHead != null && rightHead != null) {
            if(leftHead.data < rightHead.data) {
                temp.next = leftHead;
                temp = temp.next;
                leftHead = leftHead.next;      
            } else {
                temp.next = rightHead;
                temp = temp.next;
                rightHead = rightHead.next;
            }
        }
        while(leftHead != null) {
            temp.next = leftHead;
            temp = temp.next;
            leftHead = leftHead.next;
        }
        while(rightHead != null) {
            temp.next = rightHead;
            temp = temp.next;
            rightHead = rightHead.next;
        }
        return mergeList.next;
    }
    public Node mergeSort(Node head) {
        if(head == null || head.next == null) {
            return head;
        }
        // 1. find mid and seprate into two lists
        // 2. recursive calls for left and right part
        // 3. merge both sorted linked lists
        Node mid = getMid(head);
        Node rightHead = mid.next;
        mid.next = null;
        Node leftSorted = mergeSort(head);
        Node rightSorted = mergeSort(rightHead);
        
        return merge(leftSorted, rightSorted);
    }
    public void zigZag(Node head) {
        // 1. find mid
        
        Node mid = getMid(head);
        Node rightHead = mid.next;
        mid.next = null;
        
        // 2. reverse second part
        
        Node prev = null;
        Node curr = rightHead;
        while(curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        rightHead = prev;
        
        // 3. merge both parts
        Node nextL, nextR; // head -> lefthead, rightHead -> righthead
        while(head != null && rightHead != null) {
            nextL = head.next;
            head.next = rightHead;
            nextR = rightHead.next;
            rightHead.next = nextL;
            
            head = nextL;
            rightHead = nextR;
            
        }
        
    }
    public Node zigZagList(Node head) {
        //  1. find mid of list
        //  2. reverse list second half
        //  3. use tongle to choose a list 
        Node mid = getMid(head);
        Node rightHead = mid.next;
        mid.next = null;
        
        Node prev = null;
        Node curr = rightHead;
        while(curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        rightHead = prev;
        
        Node list = new Node(-1);
        Node temp = list;
        boolean tongle = false;
        while(head != null && rightHead != null) {
            if(!tongle) {
                temp.next = head;
                head = head.next;
                temp = temp.next;
            } else {
                temp.next = rightHead;
                rightHead = rightHead.next;
                temp = temp.next;
            }
            tongle = tongle == false ? true : false;
        }
        while(head != null) {
            temp.next = head;
            head = head.next;
            temp = temp.next;
        }
         while(rightHead != null) {
            temp.next = rightHead;
            rightHead = rightHead.next;
            temp = temp.next;
        }
       
        return list.next;
    }
    public void printList() {
        Node curr = head;
        while(curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }
    public void printList(Node head) {
        Node curr = head;
        while(curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.next;
        }
        System.out.println("null");
    }
    public void deleteNtoM(int n, int m, Node head) {
        if(n+m > size) {
            System.out.println("Out Of Bound Exeption!");
            return;
        }
        int i =0;
        Node temp = head;
        while(i < n-1) {
            temp = temp.next;
            i++;
        }
        i = 0;
        Node temp2 = temp;
        while(i <= m) {
            temp2 = temp2.next;
            i++;
        }
        temp.next = temp2;
        size -= m;
    }
    public boolean isPalindromeStack(Node head) {
        if(head == null) {
            return false;
        }
        Stack<Integer> st = new Stack<>();
        Node curr = head;
        while(curr != null) {
            st.add(curr.data);
            curr = curr.next;
        }
        while(head != null) {
            if(head.data != st.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }
    public void swapNode(Node head) {
        
    }
}
class Main extends LinkedListC {
     public static void main(String args[]) {
        LinkedListC ll = new LinkedListC();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addLast(3);
        ll.addLast(2);
        ll.addLast(1);
        ll.addLast(8);
        ll.printList();
//        ll.deleteNtoM(2, 2, head);
        boolean falg = ll.isPalindromeStack(head);
        ll.printList();
        System.out.println(falg);
//        head = ll.zigZagList(head);
//        head = ll.mergeSort(head);
//        ll.printList(head);
//        tail.next = head.next;
//        System.out.println(ll.isCycle(head));
//        ll.printList();
//        ll.removeCycle(head);
//        ll.printList();
//        System.out.println(ll.isPalindrome(head));
//        ll.printList();
 
        
//        ll.removeFirst();
//        ll.printList();
//        ll.removeLast();
//        ll.printList();
//        System.out.println(ll.recursiveSearch(3));
        
        
        
        
        
    }
}
