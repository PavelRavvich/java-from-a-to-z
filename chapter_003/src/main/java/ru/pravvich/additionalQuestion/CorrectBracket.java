package ru.pravvich.additionalQuestion;

class CorrectBracket {

    // сложный если учитывать свякие вложенные в вложенные и тд
    boolean checkSecond(String bracket) {
        String[] arr = bracket.split("");
        if (arr.length % 2 != 0)
            return false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null && arr[i].equals("(")) {
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] != null && arr[j].equals(")")) {
                        arr[i] = null;
                        arr[j] = null;
                        break;
                    }
                }
            }
        }

        for (String item : arr) {
            if (item != null)
                return false;
        }
        return true;
    }
}
