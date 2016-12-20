package ru.pravvich.additionalQuestion;

class CorrectBracket {

    public static void main(String[] args) {
        String s = "()()";
        CorrectBracket correctBracket = new CorrectBracket();
        System.out.println(correctBracket.checkSecond(s));
    }

    boolean checkSecond(String bracket) {
        char[] array = bracket.toCharArray();
        if (array.length % 2 != 0) {
            return false;
        }
        int d = 0;
        for (char anArray : array) {
            d += anArray == '(' ? 1 : -1;
            if (d < 0) {
                return false;
            }
        }
        return true;
    }
}
