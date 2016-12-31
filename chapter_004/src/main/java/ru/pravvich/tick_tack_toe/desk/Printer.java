package ru.pravvich.tick_tack_toe.desk;

/**
 * Print current statement desc.
 */
public class Printer {
    public static void printDesc(char[][] desc) {
        System.out.print("y\\x");

        for (int i = 0; i < desc.length; i++) {
            System.out.print("  " + i + ":");
        }
        System.out.println();
        System.out.print("   ");

        for (char[] aDesc : desc) {
            System.out.print(" ---");
        }
        System.out.println();

        for (int i = 0; i < desc.length; i++) {
            System.out.print(i + ": | ");
            for (char[] aDesc : desc) {
                System.out.print(aDesc[i]);
                System.out.print(" | ");
            }
            System.out.println();
            System.out.print("   ");
            for (char[] aDesc : desc) {

                System.out.print(" ---");
            }
            System.out.println();
        }
    }
}
