package Utils;

import java.util.Random;

/**
 * Utility functions belonging nowhere else
 * 
 * @author adveres
 */
public class Utils {

    /**
     * Generates a random number between 1 and nextInt and returns it
     * 
     * @param nextInt the top int to generate against
     * @return temp is the random number
     */
    public static int generateRandomNumber(int nextInt) {
        Random gen = new Random();
        int temp = gen.nextInt(nextInt);

        return temp;
    }
}
