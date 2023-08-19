
package alpha;

/**
 *
 * @author rinku
 */
public class TrieC {
    static class Node {

        Node child[];
        boolean eow;

        Node() {
            this.child = new Node[26];
            for (int i = 0; i < 26; i++) {
                child[i] = null;
            }
            this.eow = false;
        }
    }

   
    static Node node = new Node();
    public static Node insert(String word) {
        Node root = node;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';
            if (root.child[idx] == null) {
                root.child[idx] = new Node();
            }
            if (i == word.length() - 1) {
                root.child[idx].eow = true;
            }
            root = root.child[idx];
        }
        return root;
    }
    
    public static boolean search(String key) {
        Node root = node;
        for(int i=0; i<key.length(); i++) {
            int idx = key.charAt(i) - 'a';
            if(root.child[idx] == null) {
                return false;
            }
            if(i == key.length()-1 && !root.child[idx].eow) {
                return false;
            }
            root = root.child[idx];
        }
        return true;
    }
    public boolean wordBreak(String key) {
        if(key.length() == 0) {
            return true;
        }
        for(int i=0; i<key.length(); i++) {
            String firstPartOfString = key.substring(0, i+1);
            String secondPartOfString = key.substring(i+1);
            if(search(firstPartOfString) && wordBreak(secondPartOfString)) {
                return true;
            }
        }
        return false;
    }
    public boolean startsWith(String key) {
        Node root  = node;
        for(int i=0; i<key.length(); i++) {
            int idx = key.charAt(i)-'a'; 
            if(root.child[idx] == null) {
                return false;
            }
            root = root.child[idx];
        }
        return true;
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
        return count+1;
    }
    public static String ans = "";
    public void longestWord(Node root, StringBuilder temp) {
        if(root == null) {
            return;
        }
        for(int i=0; i<26; i++) {
            if(root.child[i] != null && root.child[i].eow == true) {
                temp.append((char)(i+'a'));
                if(temp.length() > ans.length()) {
                    ans = temp.toString();
                }
                longestWord(root.child[i], temp);
                temp.deleteCharAt(temp.length()-1);
            }
        }
    }
}
