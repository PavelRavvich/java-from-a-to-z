package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ArrTurnTest {

    /* Поворот матрицы по часовой стрелке */
    @Test
    public void whenArrInThenArrTurningOnNinetyDegreesClockwise() {
        int[][] arr = {{1, 1, 1, 1},
                       {2 ,2, 2, 2},
                       {3 ,3, 3, 3},
                       {4 ,4, 4, 4}};
        int[][] result = {{4, 3, 2, 1},
                          {4, 3, 2, 1},
                          {4, 3, 2, 1},
                          {4, 3, 2, 1}};
        ArrTurn array = new ArrTurn();
        int[][] arr1 = array.arrTurningClockwise(arr);
        assertThat(result, is(arr1));
    }

    /* Поворот матрицы против часовой стрелки */
    @Test
    public void whenArrInThenArrTurningOnNinetyDegreesAnticlockwise() {
        int[][] arr = {{1, 1, 1, 1},
                       {2 ,2, 2, 2},
                       {3 ,3, 3, 3},
                       {4 ,4, 4, 4}};
        int[][] result = {{1, 2, 3, 4},
                          {1, 2, 3, 4},
                          {1, 2, 3, 4},
                          {1, 2, 3, 4}};
        ArrTurn array = new ArrTurn();
        int[][] arr1 = array.arrTurningAnticlockwise(arr);
        assertThat(result, is(arr1));
    }

    /* Транспонироание матрицы */
    @Test
    public void whenArrInThenOutArrayTranspositionMatrix() {
        ArrTurn array = new ArrTurn();
        int[][] arr = {{1, 2, 3, 4},
                       {1 ,2, 3, 4},
                       {1, 2, 3, 4},
                       {1, 2, 3, 4}};
        int[][] arr1 = array.transpositionMatrix(arr);
        int[][] result = {{1, 1, 1, 1},
                          {2, 2, 2, 2},
                          {3, 3, 3, 3},
                          {4, 4, 4, 4}};
        assertThat(result, is(arr1));
    }

    /* Реверс строк */
    @Test
    public void whenArrInThenRowsRevers() {
        ArrTurn array = new ArrTurn();
        int[][] arr = {{4, 3, 2, 1},
                       {4, 3, 2, 1},
                       {4, 3, 2, 1},
                       {4, 3, 2, 1}};
        int[][] arr2 = array.rowsInversion(arr);
        int[][] result = {{1, 2, 3, 4},
                          {1, 2, 3, 4},
                          {1, 2, 3, 4},
                          {1, 2, 3, 4}};
        assertThat(result, is(arr2));
    }
}