
package alpha;


public class DubbleLL {
    public static class Node {
        int data;
        Node prev;
        Node next;
        
        public Node(int data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }
    public static Node head;
    public static Node tail;
    public static int size;
    public void addFirst(int data) {
        Node newNode = new Node(data);
        size++;
        if(head == null) {
            head = tail = newNode;
            return;
        }
        head.prev = newNode;
        newNode.next = head;
        head = newNode;
    }
    public int removeFirst() {
        if(head == null) {
            System.out.println("Nothing to remove!");
            return -1;
        } else if(head.next == null) {
            int val = head.data;
            head = tail = null;
            size = 0;
            return val;
        }
        int val = head.data;
        head = head.next;
        head.prev = null;
        return val;
    }
    public void printList() {
        Node temp = head;
        while(temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }
    public void printListRev() {
        Node temp = tail;
        while(temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.prev;
        }
        System.out.println("null");
    }
}
class Main {
    public static void main(String args[]) {
        DubbleLL ll = new DubbleLL();
        
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addFirst(5);
        ll.printList();
        ll.removeFirst();
        ll.printList();
        ll.printListRev();
        
    }
}
