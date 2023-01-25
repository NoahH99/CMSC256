package cmsc256;

/**
 * Created by Noah Hendrickson on 1/24/2023
 */
public class FracturedFraction implements WackyFractionInterface {

    private int numerator;
    private int denominator;

    public FracturedFraction() throws IllegalArgumentException {
        setFraction(1);
    }

    public FracturedFraction(int numerator, int denominator) throws IllegalArgumentException {
        setFraction(numerator, denominator);
    }

    @Override
    public void setFraction(int newNumerator, int newDenominator) throws IllegalArgumentException {
        setNumerator(newNumerator);
        setDenominator(newDenominator);
    }

    @Override
    public void setFraction(int wholeInteger) throws IllegalArgumentException {
        setFraction(wholeInteger, 1);
    }

    @Override
    public int getNumerator() {
        return numerator;
    }

    @Override
    public int getDenominator() {
        return denominator;
    }

    /**
     * @throws IllegalArgumentException if numerator is less than 1.
     */
    @Override
    public void setNumerator(int numerator) throws IllegalArgumentException {
        if (numerator < 1) throw new IllegalArgumentException("Numerator cannot be less than 1.");
        this.numerator = numerator;
    }

    /**
     * @throws IllegalArgumentException if denominator is less than 1.
     */
    @Override
    public void setDenominator(int denominator) throws IllegalArgumentException {
        if (denominator < 1) throw new IllegalArgumentException("Denominator cannot be less than 1.");
        this.denominator = denominator;
    }

    @Override
    public WackyFractionInterface add(WackyFractionInterface operand) throws IllegalArgumentException {
        return new FracturedFraction(numerator + operand.getNumerator(), denominator + operand.getDenominator());
    }

    @Override
    public WackyFractionInterface subtract(WackyFractionInterface operand) throws IllegalArgumentException, UnsupportedOperationException {
        if (numerator - operand.getNumerator() == 0) throw new UnsupportedOperationException();

        return new FracturedFraction(
                Math.abs(numerator - operand.getNumerator()),
                Math.min(denominator, operand.getDenominator())
        );
    }

    @Override
    public WackyFractionInterface multiply(WackyFractionInterface operand) throws IllegalArgumentException {
        return new FracturedFraction(numerator * operand.getNumerator(), denominator * operand.getDenominator());
    }

    @Override
    public WackyFractionInterface divide(WackyFractionInterface operand) throws IllegalArgumentException {
        return new FracturedFraction(denominator * operand.getNumerator(), numerator * operand.getDenominator());
    }

    @Override
    public WackyFractionInterface getReciprocal() {
        return new FracturedFraction(denominator, numerator);
    }

    @Override
    public int compareTo(WackyFractionInterface o) {
        return Integer.compare(numerator * o.getDenominator(), o.getNumerator() * denominator);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof WackyFractionInterface)) return false;

        return this.compareTo((WackyFractionInterface) o) == 0;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
