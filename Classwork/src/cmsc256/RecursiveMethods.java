package cmsc256;

import java.util.Arrays;

/**
 * Created by Noah Hendrickson on 1/31/23
 *
 * Created by: Noah Hendrickson, and Diamond Zetty
 */
public class RecursiveMethods {

    /*
     * @returns a String of character, ch. The length is determined
     *  by the second parameter, length.
     */
    public static String buildStringOfCharacters(char ch, int length) { // a, 3
        if (length == 0) return "";

        return ch + buildStringOfCharacters(ch, length - 1); // a:3, aa:2, aaa:1
    }

    /*
     * returns an int array that has the elements in reverse order
     *  of the parameter array, nums.
     * Process this recursively beginning with the last element.
     */
    public static int[] reverseNumArray(int[] nums, int backIndex) {
        int firstIndex = nums.length - backIndex - 1;

        if (firstIndex < backIndex) {
            int temp = nums[firstIndex];

            nums[firstIndex] = nums[backIndex];
            nums[backIndex] = temp;

            reverseNumArray(nums, backIndex - 1);
        }

        return nums;
    }

    /*
     * returns true if the int array parameter is sorted from smallest
     *  to largest, false otherwise.
     * Process this recursively beginning with the first element.
     *
     * [1,2,3,4,5] -> true
     *
     *  0 1 2 3
     * [1,2,4,5,9,3,15,16] -> false
     * // 0, 1, 2, ... values.length - 1
     */
    public static boolean isSmallestToLargest(int[] values, int i) {
        if (i == values.length - 1) {
            return true;
        }

        if (values[i] > values[i + 1]) {
            return false;
        }

        return isSmallestToLargest(values, i + 1);
    }

    /*
     * @returns true if the parameter String, str is a palindrome
     *  false otherwise
     */

    // dad
    // racecar

    // [1,2,3,4,5]
    // r=1 l=5
    // r=2 l=4

    // racecar
    // r != r
    // a != a
    // c != c
    // e != e

    // aaaa

    // isPalindrome("racecar", 0, 6);
    // isPalindrome("racecar", 1, 5);
    // isPalindrome("racecar", 2, 4);
    // isPalindrome("racecar", 3, 3);

    // isPalindrome("racecar", 0, 3);
    // isPalindrome("racecar", 1, 2);
    // isPalindrome("racecar", 2, 1);

    public static boolean isPalindrome(String str, int begin, int end) {
        if (begin >= end) return true;
        if (str.charAt(begin) != str.charAt(end)) return false;

        return isPalindrome(str, begin + 1, end - 1);
    }

    public static void main(String[] args) {
        System.out.println(buildStringOfCharacters('a', 5));

        int[] arr = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(reverseNumArray(arr, arr.length - 1)));

        int[] arr1 = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{1, 2, 3, 4, 5, 1, 9};
        System.out.println(isSmallestToLargest(arr1, 0));
        System.out.println(isSmallestToLargest(arr2, 0));

        System.out.println(isPalindrome("racecar", 0, 6));
        System.out.println(isPalindrome("aaaa", 0, 3));
        System.out.println(isPalindrome("abaa", 0, 3));
    }
}
