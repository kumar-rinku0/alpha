
package alpha;

/**
 *
 * @author rinku
 */
public class ArrayC {
    public int trapedWaterProb(int height[]) {
        // given bar heights to calculate traped water in vally of bars
        // 1. calculate leftMax array Or Axiulary array for left max boundry
        int n = height.length;
        int leftMax[] = new int[n];
        leftMax[0] = height[0];
        for(int i=1; i<n; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }
        // 2. calculate rightMax array Or Axiulary array for right max boundry
        int rightMax[] = new int[n];
        rightMax[n-1] = height[n-1];
        for(int i=n-2; i>=0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }
        // 3. loop
        int trapedwater = 0;
        for(int i=0; i<n; i++) {
            // 4. trapedwater = waterlevel - height[i] where waterlevel = min(leftMaxBoundry, rightMaxBoundry);
            int waterlevel = Math.min(leftMax[i], rightMax[i]);
            trapedwater += waterlevel - height[i];
        }
        return trapedwater;
    }
}
