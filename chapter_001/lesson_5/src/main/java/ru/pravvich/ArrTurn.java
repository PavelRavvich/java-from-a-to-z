package ru.pravvich;

public class ArrTurn {

    /*
    *     //* Поворот по часовой стрелке *\\
    * Объединяет два метода: транспонирование и потом инверсию
    * (transpositionMatrix, rowsInversion)
    * Метод инверсия не передается в транспонирование на прямую
    * так как return оставлен для реализации теста,
    * чтобы каждый метод имел свой тест.
    */
    public int[][] arrTurningClockwise(int[][] arrForTurn) {
        int[][] transitionReady = transpositionMatrix(arrForTurn);
        int[][] result = rowsInversion(transitionReady);
        return result;
    }

    /*
    *     //* Поворот против часовой стрелки *\\
    * Объединяет два метода: инверсию и потом транспонирование
    * (rowsInversion, transpositionMatrix)
    */
    public int[][] arrTurningAnticlockwise(int[][] arrForTurn) {
        int[][] inversionReady = rowsInversion(arrForTurn);
        int[][] result = transpositionMatrix(inversionReady);
        return result;
    }

    /*
    * Транспонирует матрицу путем перезаписи ее в новый массив
    * меняет местами аргументы в столбцах и строках
    */
    public int[][] transpositionMatrix(int[][] arrTransport) {
        int t = arrTransport.length;
        int f = arrTransport[(t - 1)].length;
        int[][] result = new int[f][t];
        for (int i = 0; i < arrTransport.length; i++) {          //обход по горизонтали
            for (int j = 0; j < arrTransport[i].length; j++) {   //обход по вертикали
                result[i][j] = arrTransport[j][i];      //обмен строк и столбцов местами
            }
        }
        return result;
    }

    /* Инвертирует последовательность элементов по горизонтали */
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