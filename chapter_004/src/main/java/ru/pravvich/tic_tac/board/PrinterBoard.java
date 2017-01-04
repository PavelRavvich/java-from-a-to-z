package ru.pravvich.tic_tac.board;

import ru.pravvich.tic_tac.Cell;

/**
 * Print current statement desc.
 */
public class PrinterBoard {
    public static void printDesc(Cell[][] desc) {
        System.out.print("y\\x");
        for (int i = 0; i < desc.length; i++) {
            System.out.print("  " + i + ":");
        }
        System.out.println();
        System.out.print("   ");
        for (Cell[] aDesc : desc) {
            System.out.print(" ---");
        }
        System.out.println();
        for (int i = 0; i < desc.length; i++) {
            System.out.print(i + ": | ");
            for (Cell[] aDesc : desc) {
                System.out.print(aDesc[i]);
                System.out.print(" | ");
            }
            System.out.println();
            System.out.print("   ");
            for (Cell[] aDesc : desc) {

                System.out.print(" ---");
            }
            System.out.println();
        }
    }
}
