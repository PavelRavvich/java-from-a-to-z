package ru.pravvich;

/**
 * Поворачивает матицу на 90 градусов,
 * по часовой стреке и против.
 * @author Pavel Ravvich
 * @version 1.0
 */
public class ArrTurn {

    /**
     * Поворачиват массив по часовой стрелке
     * Объединяет два метода:
     * {@link #transpositionMatrix транспонирование} и
     * {@link #rowsInversion инверсия}
     * @param transitionReady результат транспонирования
     * @param result результат инверсии
     * Метод инверсия не передается в транспонирование на прямую
     * так как return оставлен для реализации
     * {@link ArrTurnTest#whenArrInThenOutArrayTranspositionMatrix теста},
     * чтобы каждый метод мог иметь свой тест.
     * С начала используется транспонирование потом инверсия.
     */
    public int[][] arrTurningClockwise(int[][] arrForTurn) {
        int[][] transitionReady = transpositionMatrix(arrForTurn);
        int[][] result = rowsInversion(transitionReady);
        return result;
    }

    /**
     * Поворот против часовой стрелки
     * Почти поторяет {@link #arrTurningClockwise(int[][])}
     * @param inversionReady результат инверсии
     * @param result результат транспонирования
     * Разница: сначала инверсия потом транспонирование,
     * за счет этого поворот получается против часовой стрелки.
     */
    public int[][] arrTurningAnticlockwise(int[][] arrForTurn) {
        int[][] inversionReady = rowsInversion(arrForTurn);
        int[][] result = transpositionMatrix(inversionReady);
        return result;
    }

    /**
     * Транспонирует матрицу путем перезаписи ее в новый массив
     * меняет местами аргументы в столбцах и строках
     * @param result - новый массив
     */
    public int[][] transpositionMatrix(int[][] arrTransport) {
        int t = arrTransport.length;
        int f = arrTransport[(t - 1)].length;
        int[][] result = new int[f][t];
        for (int i = 0; i < arrTransport.length; i++) {          //обход по горизонтали
            for (int j = 0; j < arrTransport[i].length; j++) {   //обход по вертикали
                result[i][j] = arrTransport[j][i];       //обмен строк и столбцов местами
            }
        }
        return result;
    }

    /**
     * Инвертирует последовательность элементов по горизонтали,
     * путем перезаписи ее в новый массив.
     * меняет местами аргументы в столбцах и строках
     * @param result - новый массив
     */
    public int[][] rowsInversion(int[][] arrRowsInvert) {
        int[][] result = new int[arrRowsInvert.length][arrRowsInvert.length];
        for (int l = 0; l < arrRowsInvert.length ; l++) {
            for (int x = 0, y = (arrRowsInvert.length - 1); x < arrRowsInvert.length; x++, y--) {
                result[l][x] = arrRowsInvert[l][y];
            }
        }
        return result;
    }
}