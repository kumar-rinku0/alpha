
package alpha;

/**
 *
 * @author rinku
 */
public class TriesC {
    static class Node {
        Node child[] = new Node[26];
        boolean end = false;
        int fq;
        
        Node() {
            for(int i=0; i<26; i++) {
                child[i] = null;
            }
            this.fq = 1;
        }
    }
    static Node node = new Node();
    public void insert(String str) {
        Node root = node;
        for(int i=0; i<str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            if(root.child[idx] == null) {
                root.child[idx] = new Node();
            } else {
                root.child[idx].fq++;
            }
            if(i == str.length()-1) {
                root.child[idx].end = true;
            }
            root = root.child[idx];
        }
    }
    public boolean search(String str) {
        Node root = node;
        for(int i=0; i<str.length(); i++) {
            int idx = str.charAt(i) - 'a';
            if(root.child[idx] == null){
                return false;
            }
            if(i == str.length()-1 && !root.child[idx].end) {
                 return false;
            }
            root = root.child[idx];
        }
        return true;
    }
    public boolean wordBreak(String str) {
        if(str.length() == 0) {
            return true;
        }
        for(int i=1; i<=str.length(); i++) {
            if(search(str.substring(0, i)) && wordBreak(str.substring(i))) {
                return true;
            }
        }
        return false;
    }
    public void printPrifix(Node root, String str) {
        if(root == null) {
            return;
        }
        if(root.fq == 1) {
            System.out.println(str);
            return;
        }
        for(int i=0; i<root.child.length; i++) {
            if(root.child[i] != null) {
                printPrifix(root.child[i], str+(char)(i+'a'));
            }
        }
    }
    public int countNodes(Node root) {
        if(root == null) {
            return 0;
        }
        int count = 0;
        for(int i=0; i<26; i++) {
            if(root.child[i] != null) {
                count += countNodes(root.child[i]);
            }
        }
        return count +1;
    }
    public int countOfUniqueSubstring(String str) {
        // 1. insert all suffex of string in trie
        for(int i=0; i<str.length(); i++) {
            insert(str.substring(i));
        }
        // 2. count all nodes of trie(count all unique prefix of suffex) 
        return countNodes(node);
    }
    static String finalString = "";
    public void longestWordWithAllPrefixes(Node root, String str) {
        if(root == null) {
            return;
        }
        for(int i=0; i<26; i++) {
            if(root.child[i] != null && root.child[i].end) {
                longestWordWithAllPrefixes(root.child[i], str+(char)(i+'a'));
            }
            if(finalString.length() < str.length()) {
                finalString = str;
            }
        }
    }
    public static void main(String args[]) {
        TriesC tc = new TriesC();
//        int val = tc.countOfUniqueSubstring("apple");
//        System.out.println(val);
        String arr[] = {"ap", "banana", "a", "app", "apply", "apple", "appl", "applyed"};
        for(String i : arr) {
            tc.insert(i);
        }
        tc.longestWordWithAllPrefixes(node, "");
        System.out.println(finalString);
//        System.out.println(tc.search("all"));
//        System.out.println(tc.wordBreak("duckdogdove"));
//        node.fq = -1;
//        tc.printPrifix(node, "");

    }
}
