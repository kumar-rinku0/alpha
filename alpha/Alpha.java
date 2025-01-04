
package alpha;

/**
 *
 * @author rinku
 */
public class Alpha {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("class ArrayC!");
        ArrayC ac = new ArrayC();
        int height[] = {4, 2, 0, 6, 3, 2, 5 };
        int water = ac.trapedWaterProb(height);
        System.out.println("traped water is : "+ water);
    }
    
}
