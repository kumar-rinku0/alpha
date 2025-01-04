
package alpha;


/**
 *
 * @author rinku
 */
public class DP {
    public boolean targetSumSubArray(int arr[], int targetSum) {
        int n = arr.length;
        boolean dp[][] = new boolean[n+1][targetSum+1];
        for(int i=0; i<=n; i++) {
            dp[i][0] = true;
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=targetSum; j++) {
                int v = arr[i-1];
                if(v <= j && dp[i-1][j-v]) {
                    dp[i][j] = true;
                } else if(dp[i-1][j]) {
                    dp[i][j] = true;
                }
            }
        }
        for(boolean i[] : dp) {
            for(boolean j : i) {
                System.out.print(j+ " ");
            }
            System.out.println();
        }
        return dp[n][targetSum];
    }
    public int unboundedKnapsack(int val[], int wt[], int W) {
        int n = val.length;
        int dp[][] = new int[n+1][W+1];
        for(int i=0; i<n+1; i++) {
            dp[i][0] = 0;
        }
        for(int i=0; i<W+1; i++) {
            dp[0][i] = 0;
        }
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<W+1; j++) {
                if(wt[i-1] <= j) {
                    dp[i][j] = Math.max(val[i-1] + dp[i][j-wt[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        for(int i[] : dp) {
            for(int j : i) {
                System.out.print(j+ " ");
            }
            System.out.println();
        }
        return dp[n][W];
    }
    public int rodCutting(int length[], int price[], int totalRod) {
        int n = price.length;
        int dp[][] = new int[n+1][totalRod+1];
        for(int i=0; i<n+1; i++) {
            for(int j=0; j<totalRod+1; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = 0;
                }
            }
        }
        for(int i=1; i<n+1; i++) {
            for(int j=1; j<totalRod+1; j++) {
                if(length[i-1] <= j) {
                    // dp[i][j] = Math.max(price[i-1] + dp[i][j-i], dp[i-1][j]);
                    dp[i][j] = Math.max(price[i-1] + dp[i][j-length[i-1]], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        for(int i[] : dp) {
            for(int j : i) {
                System.out.print(j+ " ");
            }
            System.out.println();
        }
        return dp[n][totalRod];
    }
    public int zeroOneKnapsakTabulation(int val[], int wt[], int W) { // O(n * W) n->number of itams, W->total weight
        int n = val.length;
        int dp[][] = new int[n+1][W+1];
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = 0;
        }
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = 0;
        }
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=W; j++) {
                int v = val[i-1];
                int w = wt[i-1];
                if(w <= j) {
                    dp[i][j] = Math.max(v+dp[i-1][j-w], dp[i-1][j]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        for(int i[] : dp) {
            for(int j : i) {
                System.out.print(j+ " ");
            }
            System.out.println();
        }
        return dp[n][W];
    }
    
}
