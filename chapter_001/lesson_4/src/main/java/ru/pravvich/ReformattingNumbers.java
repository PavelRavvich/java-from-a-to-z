package ru.pravvich;

public class ReformattingNumbers {
    /*
    Eng.
    The method for determining the number of characters in big numbers
    He shares the number-valued on individual numbers.
    Returns each character parameter "x" in a separate cell of the array.
    Distributes the number of cells in the array in order.
    Example: x = 945; Parameter return array[] = {9, 4, 5};
    ======================================================================
    Метод для определения количества символов в многозначном числе
    Он разделяет многозначное число на отдельные цифры.
    Возвращает каждый символ параметра "х" в отдельной ячейке массива.
    Распределяет числа в ячейках массива по порядку.
    Пример: параметр x = 945; Метод вернет: return array[] = {9, 4, 5};
    */
    public int[] determiningTheNumberOfDigitsAndSplit(int x) {
        if (x > 1 && x < 10) {
            int[] array = new int[1];
            array[0] = x;
            return array;
        } else if (x > 9 && x < 100) {
            //first numeral
            int[] array = new int[2];
            int h = x % 10;
            int c = x - h;
            int resultFirstNumeral = c / 10;
            array[0] = resultFirstNumeral;
            //second numeral
            int resultSecondNumeral = x % 10;
            array[1] = resultSecondNumeral;
            return array;
        } else if (x > 100 && x < 1000) {
            //first numeral
            int[] array = new int[3];
            int t = x % 10;
            int s = x - t;
            int q = s % 100;
            int j = x - q;
            int resultFirstNumeral = j / 100;
            array[0] = resultFirstNumeral;
            //second numeral
            int a = x % 100;
            int v = a % 10;
            int m = a - v;
            int resultSecondNumeral = m / 10;
            array[1] = resultSecondNumeral;
            //third numeral
            int resultThird = x % 10;
            array[2] = resultThird;
            return array;
        } else if (x == 0 || x == 1) {
            int[] array = new int[1];
            array[0] = 1;
            return array;
        } else if (x < 0) {
            System.out.println("Факториал определен только для натуральных чисел и нуля.");
            return new int[0];
        } else {
            System.out.println("Данная программа вычисляет факториал не более чем 3х значных чисел.");
            return new int[0];
        }
    }
}