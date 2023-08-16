package alpha;

import java.util.*;

public class BinaryTreeC {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    static int idx = -1;
    public static Node buildTree(int arr[]) {
        idx++;
        if (arr[idx] == -1) {
            return null;
        }
        Node newNode = new Node(arr[idx]);
        newNode.left = buildTree(arr);
        newNode.right = buildTree(arr);

        return newNode;
    }

    // preorder!(dfs)
    private static void printTree(Node root) {
        if (root == null) {
            System.out.print("-1 ");
            return;
        }
        System.out.print(root.data + " ");
        printTree(root.left);
        printTree(root.right);
    }

    // inorder(dfs)
    private static void inorder(Node root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    // postorder(dfs)
    private static void postorder(Node root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    // level order(bfs)
    private static void levelorder(Node root) {
        Queue<Node> qu = new LinkedList<>();
        qu.add(root);
        qu.add(null);
        while (!qu.isEmpty()) {
            Node curr = qu.remove();
            if (curr == null) {
                System.out.println();
                if (!qu.isEmpty()) {
                    qu.add(null);
                }
            } else {
                System.out.print(curr.data + " ");
                if (curr.left != null) {
                    qu.add(curr.left);
                }
                if (curr.right != null) {
                    qu.add(curr.right);
                }
            }

        }
    }

    private static int height(Node root) {
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    private static int countOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return countOfNodes(root.left) + countOfNodes(root.right) + 1;
    }

    private static int sumOfNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return sumOfNodes(root.left) + sumOfNodes(root.right) + root.data;
    }

    private static int diameter(Node root) {
        if (root == null) {
            return 0;
        }
        int leftDiameter = diameter(root.left);
        int rightDiameter = diameter(root.right);
        int maxDiam = Math.max(leftDiameter, rightDiameter);
        int diamIncludingSelf = height(root.left) + height(root.right) + 1;
        return Math.max(maxDiam, diamIncludingSelf);
    }
    static class Pair {
        int d;
        int h;
        public Pair(int d, int h) {
            this.d = d;
            this.h = h;
        }
    }
    private static Pair diameter2(Node root) {
        if(root == null) {
            return new Pair(0, 0);
        }
        Pair leftPair = diameter2(root.left);
        Pair rightPair = diameter2(root.right);
        int dia = Math.max(leftPair.d, rightPair.d);
        int height = Math.max(leftPair.h, rightPair.h) + 1;
        int diaForSelf = leftPair.h + rightPair.h + 1;
        Pair newPair = new Pair(Math.max(dia, diaForSelf), height);
        return newPair;
    }
    private static boolean isIdentical(Node root, Node subRoot) {
        if(root == null && subRoot == null) {
            return true;
        } else if(root == null || subRoot == null || root.data != subRoot.data) {
            return false;
        }
        if(!isIdentical(root.left, subRoot.left) || !isIdentical(root.right, subRoot.right)) {
            return false;
        }
        
        return true;
    }
    private static boolean isSubTree(Node root, Node subRoot) {
        if(root == null) {
            return false;
        }
        if(root.data == subRoot.data) {
            if(isIdentical(root, subRoot)) {
                return true;
            }
        }
        return isSubTree(root.left, subRoot) || isSubTree(root.right, subRoot);
    }
    static class Info {
        int key;
        Node node;
        Info(int key, Node node) {
            this.key = key;
            this.node = node;
        }
    }
    public static void topView(Node root) {
        HashMap<Integer, Node> map = new HashMap<>();
        Queue<Info> qu = new LinkedList<>();
        qu.add(new Info(0, root));
        map.put(0, root);
        int min = 0;
        int max = 0;
        while(!qu.isEmpty()) {
            Info curr = qu.remove();
            if(!map.containsKey(curr.key)) {
                map.put(curr.key, curr.node);
            }
            if(curr.node.left != null) {
                qu.add(new Info(curr.key-1, curr.node.left));
                min = Math.min(min, curr.key -1);
            }
            if(curr.node.right != null) {
                qu.add(new Info(curr.key+1,curr.node.right));
                max = Math.max(max, curr.key +1);
            }
        }
        for(int i=min; i<=max; i++) {
            System.out.print(map.get(i).data + " ");
        }
    }
    private static void kthLevel(Node root, int k) {
        Queue<Node> qu = new LinkedList<>();
        qu.add(root);
        qu.add(null);
        int i = 1;
        while(!qu.isEmpty()) {
            Node curr = qu.remove();
            if(curr == null) {
                if(!qu.isEmpty()) {
                    qu.add(null);
                    i++;
                } else {
                    break;
                }
            } else {
                if(k == i)
                    System.out.print(curr.data + " ");
                if(curr.left != null) {
                    qu.add(curr.left);
                }
                if(curr.right != null) {
                    qu.add(curr.right);
                }
            }
        }   
    }

    private static void kthLevelRecursive(Node root, int level, int k) {
        if(root == null) {
            return;
        }
        if(k == level) {
            System.out.print(root.data + " ");
            return;
        }
        kthLevelRecursive(root.left, level+1, k);
        kthLevelRecursive(root.right, level+1, k);
    }
    private static int lca(Node root, int n1, int n2) {
        ArrayList<Integer> list1 = new ArrayList<>();
        helper(root, n1, list1);
        ArrayList<Integer> list2 = new ArrayList<>();
        helper(root, n2, list2);
        int value = -1;
        int size = list1.size() < list2.size() ? list1.size() : list2.size();
        for(int i=0; i<size; i++) {
            if(list1.get(i) == list2.get(i)) {
                value = list1.get(i);
            }
        }
        return value;
    }
    private static boolean helper(Node root, int data, ArrayList<Integer> list) {
        if(root == null) {
            return false;
        }
        if(root.data == data) {
            return true;
        }
        list.add(root.data);
        if(helper(root.left, data, list)) {
            return true;
        }
        if(helper(root.right, data, list)) {
            return true;
        }
        list.remove(list.size()-1);
        return false;
    }
    private static Node lca2(Node root, int n1, int n2) {
        if(root == null || root.data == n1 || root.data == n2) {
            return root;
        }
        Node left = lca2(root.left, n1, n2);
        Node right = lca2(root.right, n1, n2);
        if(right == null) {
            return left;
        }
        if(left == null) {
            return right;
        }
        return root;
    }
    
    private static int minDistance(Node root, int n1, int n2) {
        Node lca = lca2(root, n1, n2);
        return lcaDist(lca, n1) + lcaDist(lca, n2);
    }
    private static int lcaDist(Node root, int data) {
        if(root == null) {
            return -1;
        }
        if(root.data == data) {
            return 0;
        }
        int leftDistance = lcaDist(root.left, data);
        int rightDistance = lcaDist(root.right, data);
        if(leftDistance == -1 && rightDistance == -1) {
            return -1;
        } else if(leftDistance == -1) {
            return rightDistance + 1;
        } else {
            return leftDistance + 1;
        }
    }
    private static int transformSum(Node root) {
        if(root == null) {
            return 0;
        }
//        int leftsum = transformSum(root.left);
//        int rightsum = transformSum(root.right);
        int val = root.data;
        root.data = transformSum(root.left) + transformSum(root.right);
        return val + root.data;
    }
    private static void printKthAncestor(Node root, int k) {
        Queue<Node> qu = new LinkedList<>();
        int i = 0;
        qu.add(root);
        qu.add(null);
        while(!qu.isEmpty()) {
            Node curr = qu.remove();
            if(curr == null) {
                if(!qu.isEmpty()) {
                    qu.add(null);
                    i++;
                }
            } else {
                if(i == k-1) {
                    System.out.print(curr.data + " ");
                }
                if(curr.left != null) {
                    qu.add(curr.left);
                }
                if(curr.right != null) {
                    qu.add(curr.right);
                }
            }
        }
    }
    private static Node invertBinaryTree(Node root) {
        if(root == null) {
            return null;
        }
        Node left = invertBinaryTree(root.left);
        Node right = invertBinaryTree(root.right);
        root.right = left;
        root.left = right;
        return root;
    }
    private static boolean isUniversal(Node root) {
        if(root == null) {
            return true;
        }
        if(root.left != null && root.left.data != root.data) {
            return false;
        }
        if(root.right != null && root.right.data != root.data) {
            return false;
        }
        return isUniversal(root.left) && isUniversal(root.right);
    }
    private static int minDistanceBitweenTwoNodes(Node root, int x, int y) {
        Node lca = lca2(root, x, y);
        return lcaDistance(lca, x) + lcaDistance(lca, y);
    }
    private static int lcaDistance(Node root, int val) {
        if(root == null) {
            return -1;
        }
        if(val == root.data) {
            return 0;
        }
        int left = lcaDistance(root.left, val);
        int right = lcaDistance(root.right, val);
        if(left == -1 && right == -1) {
            return -1;
        } else if(left == -1) {
            return right+1;
        } else {
            return left+1;
        }
    }
    private static Node removeLeafNodeX(Node root, int x) {
        if(root == null) {
            return null;
        }
        root.left = removeLeafNodeX(root.left, x);
        root.right = removeLeafNodeX(root.right, x);
        if(root.left == null && root.right == null && root.data == x) {
            return null;
        } 
        return root;
    }
    private static Pair maxPathOrDiameter(Node root) {
        if(root == null) {
            return new Pair(0, 0);
        }
        Pair left = maxPathOrDiameter(root.left);
        Pair right = maxPathOrDiameter(root.right);
//        int maxOfLRDia = Math.max(left.d, right.d);
        int height = Math.max(left.h, right.h) + 1;
        return new Pair(Math.max(left.h + right.h + 1, Math.max(left.d, right.d)), height);
        
    }
    public static void main(String args[]) {
//        int arr[] = {2, 2, 2, -1, -1, 2, -1, -1, 2, -1, 2, -1, 2, -1, -1};
        int arr[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, 7, -1, -1};
//        int arr[] = {1, 3, 3, -1, -1, 2, -1, -1, 3, -1, -1 };
//        int arr[] = {1, 2, 3, 4, 5, 6, -1, -1, -1, -1, -1, 7, -1, 8, -1, 9, -1, 10, -1, -1, 11, -1, -1};
//        int sub[] = {4, 5, 6, -1, -1, 7, -1, -1, -1};
        BinaryTreeC tree = new BinaryTreeC();
        Node root = tree.buildTree(arr);
        printTree(root);
//        invertBinaryTree(root);
//        removeLeafNodeX(root, 3);
        System.out.println();
        System.out.println("max path edges : " + --maxPathOrDiameter(root).d );
//        System.out.println(isUniversal(root) + " " + transformSum(root)  + " " );
//        printKthAncestor(root, 3);
        System.out.println();
//        System.out.println(isUnivalued(root).flag);
//        int val = transformSum(root);
//        System.out.println(isUnivalued(root).flag);
        printTree(root);
        System.out.println();
        
//        System.out.println(val);
//        kthLevel(root, 1);
//        System.out.println(minDistance(root, 2, 7));
        
//        kthLevelRecursive(root,1, 3);
        
//        topView(root);
//        idx = -1;
//        Node subRoot = tree.buildTree(sub);
//        levelorder(root);
//        System.out.println();
//        levelorder(subRoot);
//        System.out.println();
//        System.out.println(isSubTree(root, subRoot));
//        System.out.println();
//        System.out.println(diameter2(root).d);


    }
}
