
// Implement the MathLib class
public class MathLib implements Library {
    public int add(int a, int b) {
        return a + b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public double divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("Cannot divide by zero");
        return (double) a / b;
    }

    public double power(int base, int exponent) {
        return Math.pow(base, exponent);
    }

    public double squareRoot(int number) {
        return Math.sqrt(number);
    }
}
