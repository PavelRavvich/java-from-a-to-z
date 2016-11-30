package ru.pravvich.lesson_1;

import java.io.*;

class CheckNumber {

    ByteArrayInputStream getInput(String src) {
        try {
            return new ByteArrayInputStream(src.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    boolean isNumber(InputStream in) {
        try (Close close = new Close()) {
            int value = in.read();
            if ((value % 2) == 0) {
                close.getMessage();
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            System.out.println("Что-то пошло не так.");
        }
        return false;
    }
}

class Close implements Closeable {
    void getMessage() {
        System.out.println("Поток точно будет закрыт.");
    }

    @Override
    public void close() throws IOException {

    }
}
