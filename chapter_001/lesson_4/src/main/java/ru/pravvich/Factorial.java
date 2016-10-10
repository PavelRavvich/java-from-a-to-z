package ru.pravvich;

public class Factorial {

    private int result;

    //Calculate factorial for numbers from zero to nine.
    public int calculateFactorialsBetweenZeroToNine(int numberForFactorial) {
        int ret = 1;
        for (int i = 1; i <= numberForFactorial; i++) ret *= i;
        return ret;
    }

    /* Eng.
    The parameters of the method takes determiningTheNumberOfDigitsAndSplit classroom ReformattingNumbers
    These parameters - results transcode multiple numbers in the single digits.
    Then calculateFactorialsBetweenZeroToNine refers to a method of the same class.
    To calculate the factorial of each number.
    Adds derived from calculateFactorialsBetweenZeroToNine results.
    It returns the result of the addition. (End of the assembly method to obtain the factorial of the number).
    ============================================================================================================
    Параметры берет из метода determiningTheNumberOfDigitsAndSplit в классе ReformattingNumbers
    Эти параметры - результаты переконвертации многозначных чисел в однозначные числа.
    Затем обращается к методу calculateFactorialsBetweenZeroToNine из этого же класса.
    Для вычисления факториала каждого числа.
    Складывает полученные из calculateFactorialsBetweenZeroToNine результаты.
    Возвращает результат сложения. (Конец узла методов для полученя факториала числа).
     */
    public int converting(int[] array) {
        if (array.length == 0) {
            return  -1;
        } else if (array.length == 1) {
            int result = calculateFactorialsBetweenZeroToNine(array[0]);
            return result;
        } else if (array.length == 2) {
            int first = calculateFactorialsBetweenZeroToNine(array[0]);
            int second = calculateFactorialsBetweenZeroToNine(array[1]);
            int result = first + second;
            return result;
        } else if (array.length == 3) {
            int first = calculateFactorialsBetweenZeroToNine(array[0]);
            int second = calculateFactorialsBetweenZeroToNine(array[1]);
            int third = calculateFactorialsBetweenZeroToNine(array[2]);
            int result = first + second + third;
            return result;
        } else return -1;
    }
}