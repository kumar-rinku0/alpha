package alpha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Arrays;

public class Pattern {

    public static void main(String args[]) {
//        int tar = 0;
//        System.out.println(isIndex(new int[] {4,5,6,7,0,1,2}, 2));
//        int matrix[][] = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13, 14, 15, 16}}; 
//        spiralMatrix(matrix);
        String str = "abcab";
//        System.out.println(countVowels(str));
//        str = "ApnaCollage";
//        System.out.println(str.replace("l", str));
        //System.out.println(stringCompresson(str));
//        int val = returnSubstringCount(str);
//        System.out.println(val);
//        printPermutation("abc", "");
//        System.out.println(getIthBit(10,19));
//        printStringNumber("a1sdL356FHA79dJ0");
//        System.out.println();
//        towerOfHanoi(4, "souce", "helper", "destination");
//        System.out.println(countsubstring(str, 0, str.length() - 1, str.length()));
//        int val = countKDist("abc", 2);
//        System.out.println(val);

          String arr[] = {"gianna dior", "sunny leone", "sophia leone", "alyx star", "lily lou", "aubree valentine", "valentina nappi", "aria lee" };
          mergeSort(arr, 0, arr.length-1);
          //Arrays.sort(arr);
          System.out.println(Arrays.toString(arr));
          int arr2[] = {2, 2, 1, 1, 1, 2, 2 };
          int a = countMajorityEls(arr2, 0, arr2.length-1);
          System.out.println(a);
    }
    private static int countInRange(int arr[], int si, int ei, int num) {
        int count = 0;
        for(int i=si; i<=ei; i++) {
            if(arr[i] == num) {
                count++;
            }
        }
        return count;
    }
    
    private static int countMajorityEls(int arr[], int si, int ei) {
        if(si == ei) return arr[si];
        int mid = si + (ei - si)/2;
        int left = countMajorityEls(arr, si, mid);
        int right = countMajorityEls(arr, mid+1, ei);
        if(left == right) {
            return left;
        }
        int leftCounts = countInRange(arr, si, ei, left);
        int rightCounts = countInRange(arr, si, ei, right);
        
        return leftCounts > rightCounts ? leftCounts : rightCounts;
        
    }
    private static int countKDist(String str, int k) {
        int res = 0;
        int n = str.length();
        int cnt[] = new int[26];
        for (int i = 0; i < n; i++) {
            int dist_char = 0;
            for (int j = i; j < n; j++) {
                char ch = str.charAt(j);
                if (cnt[ch - 'a'] == 0) {
                    dist_char++;
                }
                cnt[ch - 'a']++;
                if (dist_char == k) {
                    res++;
                }
            }
        }
        return res;
    }
    private static boolean isLexicographicaly(String str1, String str2) {
        return str1.compareTo(str2) < 0;
    }

    private static void mergeSort(String str[], int si, int ei) {
        if (si >= ei) {
            return;
        }
        int mid = si + (ei - si) /2;
        mergeSort(str, si, mid);
        mergeSort(str, mid + 1, ei);
        merge(str, si, mid, ei);
    }

    private static void merge(String str[], int si, int mid, int ei) {
        String arr[] = new String[ei-si+1];
        int idx1 = si;
        int idx2 = mid+1;
        int count = 0;
        while(idx1 <= mid && idx2 <= ei) {
            if(isLexicographicaly(str[idx1], str[idx2])) {
                arr[count++] = str[idx1++];
            } else {
                arr[count++] = str[idx2++];
            }
        }
        while(idx1 <= mid) {
            arr[count++] = str[idx1++];
        }
        while(idx2 <= ei) {
            arr[count++] = str[idx2++];
        }
        for(int i=0, j=si; i<arr.length; i++, j++) {
            str[j] = arr[i];
        }
    }

    private static int countsubstring(String str, int i, int j, int n) {
        if (n == 1 || n <= 0) {
            return n;
        }
        int res = countsubstring(str, i + 1, j, n - 1) + countsubstring(str, i, j - 1, n - 1) - countsubstring(str, i + 1, j - 1, n - 2);
        if (str.charAt(i) == str.charAt((j))) {
            res++;
        }
        return res;
    }

    private static String isEvenOrOdd(int n) {
        if ((n & 1) == 0) {
            return "even";
        } else {
            return "odd";
        }
    }

    private static String getIthBit(int n, int i) {
        if (((1 << i) & n) == 0) {
            return "0";
        } else {
            return "1";
        }
    }

    private static void printStringNumber(String str) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch >= '0' && ch <= '9') {
                Integer val = ch - '0';
                list.add(val);
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        for (Integer i : list) {
            System.out.print(i);
        }
    }

    private static void towerOfHanoi(int n, String source, String helper, String destination) {
        if (n == 0) {
            return;
        }
        towerOfHanoi(n - 1, source, destination, helper);
        System.out.println(n + " file trasfer to " + source + " " + destination);
        towerOfHanoi(n - 1, helper, source, destination);

    }

    private static int countBits(int n) {
        int count = 0;
        while (n != 0) {
            if ((1 & n) != 0) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }

    private static void printPermutation(String str, String ans) {
        if (str.length() == 0) {
            System.out.print(ans + " ");
        }
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            String ros = str.substring(0, i) + str.substring(i + 1);
            printPermutation(ros, ans + ch);
        }
    }

    private static int returnSubstringCount(String str) {
        HashSet<Character> set = new HashSet<>();
        int maxCount = 0;
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!set.contains(ch)) {
                set.add(ch);
                count++;
            } else {
                if (maxCount < count) {
                    maxCount = count;
                }
                set.clear();
                set.add(ch);
                count = 1;
            }
        }
        return count > maxCount ? count : maxCount;
    }

    private static int countVowels(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++;
            }
        }
        return count;
    }

    private static String stringCompresson(String str) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < str.length(); i++) {
            int count = 1;
            while (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                count++;
                i++;
            }
            if (count > 1) {
                sb.append(str.charAt(i));
                sb.append(Integer.toString(count));
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }

    private static void spiralMatrix(int matrix[][]) {
        int startRow = 0;
        int startCol = 0;
        int endRow = matrix.length - 1;
        int endCol = matrix[0].length - 1;
        while (startRow <= endRow && startCol <= endCol) {
            for (int j = startCol; j <= endCol; j++) {
                System.out.print(matrix[startRow][j]);
            }
            startRow++;
            for (int i = startRow; i <= endRow; i++) {
                System.out.print(matrix[i][endCol]);
            }
            endCol--;
            for (int j = endCol; j >= startCol && startRow <= endRow; j--) {
                System.out.print(matrix[endRow][j]);
            }
            endRow--;
            for (int i = endRow; i >= startRow && startCol <= endCol; i--) {
                System.out.print(matrix[i][startCol]);
            }
            startCol++;
        }

    }

    private static int isIndex(int arr[], int key) {
        int si = 0;
        int ei = arr.length - 1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            if (key == arr[mid]) {
                return mid;
            } else if (arr[si] <= arr[mid]) {
                if (key <= arr[mid] && key >= arr[si]) {
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            } else if (arr[mid] <= arr[ei]) {
                if (key <= arr[ei] && key >= arr[mid]) {
                    si = mid + 1;
                } else {
                    ei = mid - 1;
                }
            }
        }
        return -1;
    }

    private static void insertionSort(int arr[]) {
        for (int i = 0; i < arr.length - 1; i++) {
            int smallestIdx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[smallestIdx] > arr[j]) {
                    smallestIdx = j;
                }
            }
            // swaping 
            int temp = arr[i];
            arr[i] = arr[smallestIdx];
            arr[smallestIdx] = temp;
        }
    }

    private static void printPattern(int n) {
        for (int i = 1; i <= n; i++) {
            // space
            for (int j = 1; j <= n - i; j++) {
                System.out.print(" ");
            }
            // number
            for (int j = i; j >= 1; j--) {
                System.out.print(j);
            }
            for (int j = 2; j <= i; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    private static int trapwater(int height[]) {
        int n = height.length;
        // left max
        int leftMax[] = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        // right max
        int rightMax[] = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        // trap water
        int trapWater = 0;
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(rightMax[i], leftMax[i]);
            trapWater += (waterLevel - height[i]);
        }
        return trapWater;
    }

}
