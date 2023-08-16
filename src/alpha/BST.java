
package alpha;

import java.util.*;

public class BST extends BinaryTreeC{
//    static class Node {
//        int data;
//        Node left;
//        Node right;
//        public Node(int data) {
//            this.data = data;
//            this.left = null;
//            this.right = null;
//        }
//    }
    static class Info {
        boolean isValid;
        int min;
        int max;
        int sum;
        public Info(boolean isValid, int min, int max, int sum) {
            this.isValid = isValid;
            this.max = max;
            this.min = min;
            this.sum = sum;
        }
    }
    static int sumOfBST = 0;
    public Info sumOfLargetBSTinBT(Node root) {
        if(root == null) {
            return new Info(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }
        Info left = sumOfLargetBSTinBT(root.left);
        Info right = sumOfLargetBSTinBT(root.right);
        int sum = left.sum + right.sum + root.data;
        int min = Math.min(Math.min(left.min, right.min), root.data);
        int max = Math.max(Math.max(left.max, right.max), root.data);
        if(root.data <= left.max || root.data >= right.min) {
            return new Info(false, min, max, sum);
        }
        if(left.isValid && right.isValid) {
            sumOfBST = Math.max(sumOfBST, sum);
            return new Info(true, min, max, sum);
        }
        return new Info(false, min, max, sum);
    }
    public static Node insert(Node root, int val) {
        if(root == null) {
            return new Node(val);
        }
        if(root.data > val) {
            root.left = insert(root.left, val);
        }
        if(root.data < val) {
            root.right = insert(root.right, val);
        }
        return root;
    }
    public static void preorder(Node root) {
        if(root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }
    public static void inorder(Node root) {
        if(root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
    public static boolean search(Node root, int val) {
        if(root == null) {
            return false;
        }
        if(root.data == val) {
            return true;
        }
        if(root.data > val) {
            return search(root.left, val);
        }
        if(root.data < val) {
            return search(root.right, val);
        }
        return false;
    }
    public static Node deleteNode(Node root, int val) {
        // 1. search in left BST
        if(root.data > val) {
            root.left = deleteNode(root.left, val);
        }
        // 2. search in right BST
        else if(root.data < val) {
            root.right = deleteNode(root.right, val);
        } 
        // 3. root is seach node to delete
        else {
            // 1. if delete node has no childs
            if(root.left == null && root.right == null) {
                return null;
            } 
            // 2. if node has one child left or right
            if(root.left == null) {
                return root.right;
            } else if(root.right == null) {
                return root.left;
            }
            // 3. if node has 2 childs  
                // a. find inorder successor 
                // b. delete node become inorder successor
                // c. delele inorder successor
            Node IS = findInorderSuccessor(root.right); 
            root.data = IS.data;
            root.right = deleteNode(root.right, IS.data);
        }
        return root;
    }
    public static Node findInorderSuccessor(Node root) {
        while(root.left != null) {
            root = root.left;
        }
        return root;
    }
    public static void printRootToLeafPaths(Node root, ArrayList<Integer> path) {
        if(root == null) {
            return;
        }
        path.add(root.data);
        if(root.left == null && root.right == null) {
            for(int i : path) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        printRootToLeafPaths(root.left, path);
        printRootToLeafPaths(root.right, path);
        path.remove(path.size()-1);
    }
    private static void inorderBST(Node root, ArrayList<Integer> list) {
        if(root == null) {
            return;
        }
        inorderBST(root.left, list);
        list.add(root.data);
        inorderBST(root.right, list);
    }
    private static boolean isSorted(ArrayList<Integer> list) {
        for(int i=1; i<list.size(); i++) {
            if(list.get(i-1) >= list.get(i)) {
                return false;
            }
        }
        return true;
    }
    private static boolean validateBST(Node root) {
        ArrayList<Integer> list = new ArrayList<>();
        inorderBST(root, list);
        return isSorted(list);
    }
    private static boolean validateBSTApproch2(Node root, Node min, Node max) {
        if(root == null) {
            return true;
        }
        if(max != null && root.data >= max.data) {
            return false;
        }
        if(min != null && root.data <= min.data) {
            return false;
        }
        return validateBSTApproch2(root.left, min, root) && validateBSTApproch2(root.right, root, max);
    }
    private static Node mirrorInBST(Node root) {
        if(root == null) {
            return null;
        }
        Node left = mirrorInBST(root.left);
        Node right = mirrorInBST(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
    private static Node createBST(int arr[], int si, int ei) {
        if(si > ei) {
            return null;
        }
        int mid = si + (ei - si)/2;
        Node root = new Node(arr[mid]);
        root.left = createBST(arr, si, mid -1);
        root.right = createBST(arr, mid+1, ei);
        return root;
    }
    
//    public static void main(String args[]) {
//        
//        
//        
//        
////        int arr[] = {1, 1, 1, 1 };
//        int arr[] = { 6, 2, 1, 7, 3, 9, 0, 8, 5};
//        Arrays.sort(arr);
//        Node root = createBST(arr, 0, arr.length-1);
//        inorder(root);
//        System.out.println();
//        preorder(root);
//        System.out.println();
////        int arr[] = {12, 6, 5, 2, 8, 7, 9, 14, 18,};
////        Node root = null;
//        for(int i=0; i<arr.length; i++) {
//            root = insert(root, arr[i]);
//        }
//        inorder(root);
//        System.out.println();
//        root = mirrorInBST(root);
//        System.out.println();
//        inorder(root);
//        System.out.println();
//        printRootToLeafPaths(root, new ArrayList());
//        if(validateBSTApproch2(root, null, null)) {
//            System.out.println("validate");
//        } else {
//            System.out.println("NOT validate");
//        }
//        System.out.println();
//        System.out.println(deleteNode(root, 12).data);
//        System.out.println();
//        inorder(root);
//    }
}
