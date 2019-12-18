package LibTest;
import static LibTest.utils.Matrix.*;
import static LibTest.utils.Multiplication.*;

public class MatricesMultiplication {

    public static void main(String[] args) {
        int max = 9;
        int[][] mtr1 = createMatrix(2, 3, max);
        int[][] mtr2 = createMatrix(3, 4, max);

        System.out.printf("\nFirst %d x %d matrix\n", mtr1.length, mtr1[0].length);
        printMatrix(mtr1);

        System.out.printf("\nSecond %d x %d matrix\n", mtr2.length, mtr2[0].length);
        printMatrix(mtr2);

        System.out.println("\nMultiplication of the matrices in Java");
        printMatrix(multiplyMatrices(mtr1, mtr2));

        /********************************************
         * Petri net multiplication of the matrices *
         *******************************************/

        System.out.println("\n\nPetri net multiplication of the matrices");

        short iterations = 100;

        System.out.println("\nConsistent multiplication");
        double consTime = execute(() -> multiplyConsistently(mtr1, mtr2), iterations, mtr2[0].length);

        System.out.println("\nParallel multiplication");
        execute(() -> multiplyParallel(mtr1, mtr2), iterations, mtr2[0].length);

        System.out.println("\nModel-based parallel multiplication");
        double modelsTime = execute(() -> multiplyParallelByModel(
                mtr1, mtr2, mtr2[0].length), iterations, mtr2[0].length);

        System.out.printf("\nAcceleration is %f\n", consTime / modelsTime);
    }

}
