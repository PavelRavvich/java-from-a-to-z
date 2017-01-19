package ru.pravvich.moneyExchange;

import java.util.ArrayList;
import java.util.List;

public class Residue {

}

class ResidueByFive {
    private final int value;

    public ResidueByFive(final int value) {
        this.value = value;
    }

    int[] getAllCases() {
        int[][] casesByFive = getCaseByFive();
        return null;
    }

    private List<List<Integer>> addCasesFive() {
        List<List<Integer>> result = new ArrayList<>();
        int amountFives = value - (value % 5);

        int[][] caseByFive = getCaseByFive();
        for (int i = 0; i < amountFives; i++) {
            for (int j = 0; j < caseByFive.length; j++) {
                for (int m = 0; m < caseByFive[j].length; m++) {
                    result.get(i).add(caseByFive[j][m]);
                }
            }
        }
        return result;
    }

    private int[][] getCaseByFive() {
        int[][] result = new int[3][];
        result[0] = new int[5];
        result[1] = new int[3];
        result[2] = new int[1];

        for (int i = 0; i < 5; i++) {
            result[0][i] = 1;
        }

        result[1][0] = 1;
        result[1][1] = 1;
        result[1][2] = 3;

        result[2][0] = 5;
        return result;
    }

    private void print() {
        List<List<Integer>> result = addCasesFive();
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                System.out.printf(result.get(i).get(j) + "|");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ResidueByFive r = new ResidueByFive(5);
        r.print();
    }
}
