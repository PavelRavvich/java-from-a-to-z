package ru.pravvich.additionalQuestion;

class CorrectBracket {

    public static void main(String[] args) {
        String s = "()()";
        CorrectBracket correctBracket = new CorrectBracket();
        System.out.println(correctBracket.checkSecond(s));
    }

    boolean checkSecond(String bracket) {
        char[] array = bracket.toCharArray();

        if (array.length % 2 != 0)
            return false;

        for (int i = 0; i < array.length; i++) {
            if (array[i] == '(') {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] == ')') {
                        array[i] = '+';
                        array[j] = '+';
                        break;
                    }
                }
            }
        }

        for (char c : array) {
            if (c != '+')
                return false;
        }
        return true;
    }
}
