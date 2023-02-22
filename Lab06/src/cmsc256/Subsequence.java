package cmsc256;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Determine if one sequence of words is a subsequence of a second.
 */


public class Subsequence {
    public static void main(String args[]) {
        List<String> s1, s2;

        System.out.println("We need two sequences of words.");

        s1 = getSequence("Enter the first sequence (s1) all on a single line.");
        s2 = getSequence("Enter the second sequence (s2) all on a single line.");

        System.out.println("s1 is " + s1);
        System.out.println("s2 is " + s2);

        System.out.println("Is s1 a subsequence of s2: " + subSequence(s1, s2));
        System.out.println("Is s2 a subsequence of s1: " + subSequence(s2, s1));

        testSubSequence();

    }

    /**
     * subSequence - determine if one sequence is a subsequence of the other
     *
     * @param sequence1 the first sequence to test
     * @param sequence2 the second sequence to test
     * @return a true if the first list is a subsequence of the other
     */
    public static <T> boolean subSequence(List<T> sequence1, List<T> sequence2) {
        if (sequence1.size() > sequence2.size()) return false;
        if (sequence1.isEmpty()) return true;

        Iterator<T> iter1 = sequence1.iterator();
        Iterator<T> iter2 = sequence2.iterator();

        while (iter1.hasNext()) {
            T item1 = iter1.next();
            boolean found = false;

            while (iter2.hasNext()) {
                T item2 = iter2.next();

                if (item1.equals(item2)) {
                    found = true;
                    break;
                }
            }

            if (!found) return false;
        }

        return true;
    }


    /**
     * getSequence - Get a sequence of words
     *
     * @param prompt A string that prompts the user for the sequence of words.
     * @return A list of words.
     */
    private static List<String> getSequence(String prompt) {
        Scanner input;
        String inString = null;
        List<String> sequence = new LinkedList<String>();

        try {
            input = new Scanner(System.in);

            System.out.println(prompt);
            inString = input.nextLine().trim();

            Scanner theWords = new Scanner(inString);
            while (theWords.hasNext()) {
                sequence.add(theWords.next());
            }
        } catch (Exception e) {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use a sequence of size zero");
        }

        return sequence;
    }

    public static void testSubSequence() {
        List<String> s1, s2;

        System.out.println();
        System.out.println("TEST CASES FOR THE SUBSEQUENCE METHOD");

        s1 = new LinkedList<String>();
        s2 = new LinkedList<String>();

        s1.add("a");
        s1.add("b");
        s1.add("c");

        System.out.println("s1 is " + s1);
        System.out.println("s2 is " + s2);

        testIsNotSubSequence(s1, s2, "s1", "s2");
        testIsSubSequence(s2, s1, "s2", "s1");

        s2.add("a");
        s2.add("c");
        s2.add("b");

        System.out.println();
        System.out.println("s1 is " + s1);
        System.out.println("s2 is " + s2);

        testIsNotSubSequence(s1, s2, "s1", "s2");
        testIsNotSubSequence(s2, s1, "s2", "s1");

        s2.add("c");

        System.out.println();
        System.out.println("s1 is " + s1);
        System.out.println("s2 is " + s2);

        testIsSubSequence(s1, s2, "s1", "s2");
        testIsNotSubSequence(s2, s1, "s2", "s1");

        System.out.println();
        s1.add("a");
        s1.add("b");
        s1.add("c");

        System.out.println("s1 is " + s1);
        System.out.println("s2 is " + s2);

        testIsNotSubSequence(s1, s2, "s1", "s2");
        testIsSubSequence(s2, s1, "s2", "s1");

        System.out.println();
        s1.clear();
        s1.add("a");
        s1.add("b");
        s1.add("a");
        s1.add("c");

        s2.clear();
        s2.add("a");
        s2.add("b");
        s2.add("a");
        s2.add("c");
        System.out.println("s1 is " + s1);
        System.out.println("s2 is " + s2);

        testIsSubSequence(s1, s2, "s1", "s2");
        testIsSubSequence(s2, s1, "s2", "s1");
    }

    /**
     * A private method that will test the isSubSequence method with sequence1
     * as the first sequence and sequence2 as the second sequence.  It expects that
     * the correct answer should be true
     *
     * @param sequence1 the first sequence
     * @param sequence2 the second sequence
     * @param name1     the name of the variable referencing sequence1
     * @param name2     the name of the variable referencing sequence2
     * @return a list of words
     */
    private static <T> void testIsSubSequence(List<T> sequence1,
                                              List<T> sequence2,
                                              String name1, String name2) {
        System.out.println("Testing if " + name1 + " is a subsequence of " + name2);
        if (subSequence(sequence1, sequence2)) {
            System.out.println("     Passes: returned true and "
                    + name1 + " is a subsequence of " + name2);
        } else {
            System.out.println("**** Fails - returned false but "
                    + name1 + " is a subsequence of " + name2);
        }
    }


    /**
     * A private method that will test the isSubSequence method with sequence1
     * as the first sequence and sequence2 as the second sequence.  It expects that
     * the correct answer should be false
     *
     * @param sequence1 the first sequence
     * @param sequence2 the second sequence
     * @param name1     the name of the variable referencing sequence1
     * @param name2     the name of the variable referencing sequence2
     * @return a list of words
     */
    private static <T> void testIsNotSubSequence(List<T> sequence1,
                                                 List<T> sequence2,
                                                 String name1, String name2) {
        System.out.println("Testing if " + name1 + " is a subsequence of " + name2);
        if (!subSequence(sequence1, sequence2)) {
            System.out.println("     Passes: returned false and "
                    + name1 + " is not a subsequence of " + name2);
        } else {
            System.out.println("**** Fails - returned true but "
                    + name1 + " is not a subsequence of " + name2);
        }
    }
}
