package ru.pravvich;

/**
 * Implements Calculate.
 * Supports repeated using previous calculation.
 */
public class Calculate {
    double result;

    void setResult(double result) {
        this.result = result;
    }


    double getResult() {
        return this.result;
    }

    /**
     * The add operations.
     *
     * @param first - first argument, second - second argument
     */
    public void add(double first, double second) {
        this.result = first + second;
    }


    /**
     * Subtraction operation.
     *
     * @param first - first argument, second - second argument
     */
    public void subtraction(double first, double second) {
        this.result = first - second;
    }


    /**
     * Multiplication operation.
     *
     * @param first - first argument, second - second argument
     */
    public void multiplication(double first, double second) {
        this.result = first * second;
    }


    /**
     * Division operation.
     *
     * @param first - first argument, second - second argument
     */
    public void div(double first, double second) {
        if (second != 0) {
            this.result = first / second;
        } else {
            System.out.println("Делить на ноль нельз¤!");
        }
    }
}



