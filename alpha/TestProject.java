package alpha;

import java.util.*;

public class TestProject {
    
   
    public static void main(String[] args) {
        // class StringC
//        StringC sc = new StringC();
//        String words[] = {"b", "a", "banana", "ap", "app", "apply", "appl", "ba", "apple"};
//        //String keys[] = {"thor", "an", "there", "all"};
//        String key = "apple";
//        StringC.Node root = null;
//        for (String word : words) {
//            root = sc.insert(word);
//        }
//        System.out.println(StringC.search(key));
        // class BST 
        BST bst = new BST();
        int values[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, 7, -1, -1};
        BinaryTreeC.Node newroot = BinaryTreeC.buildTree(values);
        bst.sumOfLargetBSTinBT(newroot);
        bst.preorder(newroot);
        System.out.println();
        System.out.println(BST.sumOfBST);
        // class HeapC
        System.out.println("HeapC Class");
        HeapC hc = new HeapC();
        int arr[] = {2, 4, 1, 6, 3, 9, 5, 8 };
        hc.heapSort(arr);
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        HeapC.Heap heap = new HeapC.Heap();
        heap.add(5);
        heap.add(1);
        heap.add(7);
        heap.add(15);
        heap.add(3);
        heap.add(9);
        
        while(!heap.isEmpty()) {
            System.out.print(heap.peek()+ " ");
            heap.remove();
        }
        System.out.println();
        int arr1[] = { 4, 3, 2, 6 };
        int val = hc.connectRopes(arr1);
        System.out.println("cost of ropes : " + val);
        int arr2d[][] = {
            {1, 1, 0, 0 },
            {1, 1, 1, 1 },
            {0, 0, 1, 0 },
            {1, 0, 0, 1 }
        };
        hc.weakestSoldier(arr2d);
        int arr2[] = {1, 3, -1, -3, 5, 3, 6, 7 };
        hc.slidingWindowMax(arr2, 3);
        int arr2dCost[][] = {
            {31, 100, 65, 12, 18 },
            {10, 13, 47, 157, 6 },
            {100, 113, 174, 11, 33 },
            {88, 124, 41, 20, 140 },
            {99, 32, 111, 41, 20 }
        };
        int cost = hc.pathWithMINEffort(arr2dCost);
        System.out.println(cost);
        System.out.println("nearby car");
        HeapC.Pair pairArr[] = new HeapC.Pair[5];
        pairArr[0] = new HeapC.Pair(7, 3);
        pairArr[1] = new HeapC.Pair(5, -1);
        pairArr[2] = new HeapC.Pair(-2, 4);
        pairArr[3] = new HeapC.Pair(7, 5);
        pairArr[4] = new HeapC.Pair(2, 9);
        hc.kNearbyCars(pairArr, 3);
        // QueueC class
        System.out.println("QueueC "); 
        
        QueueC qc = new QueueC();
        qc.maximumOfSubarrayOfSizeK(arr2, 3);
        int arr3[] = {2, 6};
        System.out.println(hc.minimumTimeRequiredToFillNslots(6, 2, arr3));
        System.out.println("HashMapC Class!");
        System.out.println(" -> ");
        HashMapC hmc = new HashMapC();
        HashMapC.HashMap<Integer, Integer> map = new HashMapC.HashMap<>();
        for(int i=1; i<100; i++) {
            map.put(i, i+7);
        }
        System.out.println(map.containsKey(91));
        System.out.println(map.remove(91));
//        System.out.println(map.containsKey(91));
        System.out.println(hmc.isAnagram("tirip", "iiprj"));
        val = hmc.intersection(new int[] {3, 7, 9 }, new int[] {1, 2, 3, 4, 5, 6, 6, 7, 7, 0 });
        System.out.println(val);
        System.out.println();
        String arrStr[][] = {
            {"chennai", "baglooru" },
            {"mumbai", "delhi" },
            {"goa", "chennai" },
            {"delhi", "goa" }
        };
        hmc.findItineraryFromTikets(arrStr);
        System.out.println();
        System.out.println(hmc.largestSubarraySumZero(new int[] {15, 2, -2, -8, 1, 7, 10, 23, -23, 5}));
        System.out.println(hmc.subarraySumEqualToK(new int[] {1, 2, 3 } , 3));
        System.out.println();
        System.out.println("class BT");
        int arrBT[] = {1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, 7, -1, -1};
        BinaryTreeC.idx = -1;
        BinaryTreeC btc = new BinaryTreeC();
        BinaryTreeC.Node root = btc.buildTree(arrBT);
        btc.topView(root);
        System.out.println();
        System.out.println("GraphC(base_class) & GraphDirected(child_class)");
        GraphDirected gd = new GraphDirected();
        int cities[][] = {
            {0, 1, 2, 3, 4},
            {1, 0, 5, 0, 7},
            {2, 5, 0, 6, 0},
            {3, 0, 6, 0, 0},
            {4, 7, 0, 0, 0}
        };
        System.out.println("connecting cities = "+gd.connectingCities(cities));
        System.out.println();
        GraphC gc = new GraphC();
        
        GraphC.count = 0;
        gc.largestMatrixInBooleanRegion(arr2d);
        System.out.println(GraphC.count);
        
        ArrayList<GraphC.Edge> graph[] = new ArrayList[6];
        gc.createGraph(graph);
//        System.out.println(gc.dijistraAlgo(graph, 0));
        System.out.println("Dp Class ->");
        DP dp = new DP();
        int value[] = {15, 14, 10, 45, 30 };
        int weight[] = {4, 5, 1, 3, 4 };
        int price[] = {1, 5, 8, 9, 10, 17, 17, 20 };
        int length[] = {1, 2, 3, 4, 5, 6, 7, 8 };
        val = dp.rodCutting(length, price, 8);
        System.out.println("Max Profit value is : " + val);
//        int value[] = {4, 2, 7};
        System.out.println(dp.targetSumSubArray(value, 15));
        
    }

}

