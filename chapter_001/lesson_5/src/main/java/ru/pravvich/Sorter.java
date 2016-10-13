package ru.pravvich;

public class Sorter {
    private int result;
    
    //Calculate factorial for numbers from zero to nine.
    public int calculateFactorialsBetweenZeroToNine(int numberForFactorial) {
        int ret = 1;
        for (int i = 1; i <= numberForFactorial; i++) ret *= i;
        return ret;
    }
}