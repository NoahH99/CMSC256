package cmsc256;
/*
 * This program is used to show how to find errors in your program
 * Programmer 1: Noah Hendrickson
 * Programmer 2:
 * CMSC 256 Section: 901
 * Lines with syntax errors: 24, 25, 27, 28
 * Runtime errors: 26, 42
 */

public class DebuggingPractice {

    private static int evenPerfectSquareNumbers = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //declare variables
        double number;
        double average, value;
        String[] daysOfTheWeek = {"Sunday", "Monday", "Tuesday", "Wednesday",
                "Thursday", "Friday", "Saturday"};
        int[] nums = {23, 3, 14, -5, 44, 78, 6, 98, 25};
        String myName = "Noah Hendrickson"; // Changed to my name
        value = 3.75; // Added: ;
        number = value; // Changed number to a double
        average = (3 + 5 + 8) / 3.0; // Can't divide by zero changed to divide by 3.0
        System.out.println(" *** I am " + myName + " *** "); // Added: );
        System.out.println("The first day of the week is: " + daysOfTheWeek[0]); // Error was fixed by fixing the error on line 27
        System.out.println("The last day of the week is: " + daysOfTheWeek[daysOfTheWeek.length - 1]);
//        average = 3 + 5 + 8 / 3.0; Deleted this line
        System.out.printf("expected average is 5.33, actual average is: %.2f\n", average); // Changed to printf
        System.out.println("expected max is 98, actual max is: " + max(nums));
        System.out.println("expected type of number is double: " + number);
        System.out.println("calling countPerfectSquares(100)");
        countPerfectSquares(100); // Changed 1000 to 100
    }

    // Returns the maximum value in the array parameter
    public static int max(int[] a) {
        int result = a[0];
        for (int j : a) { // Changed to enhanced for loop
            if (j > result) { // Checking if a[i] is greater than result
                result = j;
            }
        }
        return result;
    }

    // Returns true if all values in the parameter array fall with the
    // range define by parameters low and high
    public static boolean inRange(int[] array, int low, int high) {
        if (array == null) return false;

        for (int value : array) {
            if (value < low || value > high) {
                return false;
            }
        }

        return true;
    }

    // Displays the counts the total perfect squares and
    // //even perfect squares for a given number
    public static void countPerfectSquares(int num) {
//        int num = 100; Commented out variable
        System.out.println("Total Perfect Squares: " + calculateCount(num));
        System.out.println("Even Perfect Squares : " + evenPerfectSquareNumbers);
    }

    public static int calculateCount(int num) {
        int perfectSquaresCount = 0;
        boolean perfectSquare;

        for (int i = 1; i <= num; i++) {
            perfectSquare = isPerfectSquare(i);

            if (perfectSquare) {
                perfectSquaresCount++;

                if (i % 2 == 0) evenPerfectSquareNumbers++;
            }
        }

        return perfectSquaresCount;
    }

    private static boolean isPerfectSquare(int number) {
        double sqrt = Math.sqrt(number);
        return sqrt - Math.floor(sqrt) == 0;
    }

    public static int getEvenPerfectSquareNumbers() {
        return evenPerfectSquareNumbers;
    }
}
