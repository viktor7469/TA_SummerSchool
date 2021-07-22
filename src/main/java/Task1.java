
import java.util.*;

public class Task1 {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Input first number: ");
        int lowerBound = in.nextInt();
        System.out.print("Input second number: ");
        int upperBound = in.nextInt();
//        System.out.print("Input third number: ");
//        int nFib = in.nextInt();


        int sumOdd = 0;    // For accumulating odd numbers, init to 0
        int sumEven = 0;    // For accumulating even numbers, init to 0

        // Use a while loop to accumulate the sums from LOWERBOUND to UPPERBOUND
        int number = lowerBound;   // loop init
        while (number <= upperBound) {  // loop test

            if (number % 2 == 0) {  // Even number
                sumEven += number;   // Same as sumEven = sumEven + number
            } else {                // Odd number
                sumOdd += number;    // Same as sumOdd = sumOdd + number
            }
            ++number;  // loop update for next number
        }
        System.out.println(sumEven);
        System.out.println(sumOdd);
    }
}






