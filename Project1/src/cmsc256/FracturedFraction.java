package cmsc256;

/**
 * Created by Noah Hendrickson on 1/24/2023
 */
public class FracturedFraction implements WackyFractionInterface {

    private int numerator;
    private int denominator;

    public FracturedFraction() {
        setFraction(1);
    }

    public FracturedFraction(int numerator, int denominator) {
        setFraction(numerator, denominator);
    }

    @Override
    public void setFraction(int newNumerator, int newDenominator) {
        setNumerator(newNumerator);
        setDenominator(newDenominator);
    }

    @Override
    public void setFraction(int wholeInteger) {
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
    public void setNumerator(int numerator) {
        if (numerator < 1) throw new IllegalArgumentException("Numerator cannot be less than 1.");
        this.numerator = numerator;
    }

    /**
     * @throws IllegalArgumentException if denominator is less than 1.
     */
    @Override
    public void setDenominator(int denominator) {
        if (denominator < 1) throw new IllegalArgumentException("Denominator cannot be less than 1.");
        this.denominator = denominator;
    }

    @Override
    public WackyFractionInterface add(WackyFractionInterface operand) {
        return new FracturedFraction(numerator + operand.getNumerator(), denominator + operand.getDenominator());
    }

    @Override
    public WackyFractionInterface subtract(WackyFractionInterface operand) {
        return new FracturedFraction(
                Math.abs(numerator - operand.getNumerator()),
                Math.min(denominator, operand.getDenominator())
        );
    }

    @Override
    public WackyFractionInterface multiply(WackyFractionInterface operand) {
        return new FracturedFraction(numerator * operand.getNumerator(), denominator * operand.getDenominator());
    }

    @Override
    public WackyFractionInterface divide(WackyFractionInterface operand) {
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

        return compareTo((WackyFractionInterface) o) == 0;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
