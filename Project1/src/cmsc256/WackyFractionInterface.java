package cmsc256;

/**
 This interface describes the operations of a  wacky fraction.
 Forget all those boring rules of mathematics, we're going wacky
 WackyFractions have a numerator, denominator, and a sign.
 */
public interface WackyFractionInterface extends Comparable<WackyFractionInterface>{
    /** Sets this fraction to a given value.
     @param newNumerator	 The integer numerator.
     @param newDenominator  The integer denominator. */
    public void setFraction(int newNumerator, int newDenominator);

    /** Sets this fraction to a whole number.
     @param wholeInteger  The integer numerator. */
    public void setFraction(int wholeInteger);

    /** Gets this fraction's numerator.
     @return  The fraction's numerator. */
    public int getNumerator();

    /** Gets this fraction's denominator.
     @return  The fraction's denominator. */
    public int getDenominator();
    
    /** Sets this fraction's numerator.*/
    public void setNumerator(int numerator);
    
    /** Sets this fraction's denominator.*/
    public void setDenominator(int denominator);
    
    /** Performs a wackysum on this fraction and the given fraction
      without changing either one.
     @param operand  A fraction that is the wacky sum of the first and second operands
     @return  The wackysum of the two fractions.  */
    public WackyFractionInterface add(final WackyFractionInterface operand);

    /** Performs a wackydifference on this fraction and the given fraction
      without changing either one.
     @param operand  A fraction that is the wackydifference of the arguments.
     @return  The wackydifference of the two fractions. */
    public WackyFractionInterface subtract(final WackyFractionInterface operand);

    /** Performs a wackyproduct on this fraction and the parameter fraction
      without changing either one.
     @param operand  A fraction that is the wackyproduct of the arguments
     multiplication.
     @return  The wackyproduct of the two fractions. */
    public WackyFractionInterface multiply(final WackyFractionInterface operand);

    /** Performs a wackyquotient of this fraction and the parameter fraction
    without changing either one.
     @param operand  A fraction that is the wackyquotient of the arguments.
     @return  The wackyquotient of the two fractions. */
    public WackyFractionInterface divide(final WackyFractionInterface operand);

    /** Gets this fraction's reciprocal.
     @return  The reciprocal of the fraction. */
    public WackyFractionInterface getReciprocal();

    /** Compares this fraction to another fraction to determine which one is
     larger, or if they are equal, without changing either one.
     @param other  The other fraction we are comparing to this fraction.
     @return  An integer representing how the fractions compare. */
    public int compareTo(final WackyFractionInterface other);

    // The methods equals and toString are defined in and inherited from Object.
    // They should be overridden in any class that implements this interface.
}
