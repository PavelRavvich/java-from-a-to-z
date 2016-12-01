package ru.pravvich.lesson_1;

import java.io.*;

class CheckNumber {

    ByteArrayInputStream getInput(String src) {
        try (ByteArrayInputStream result = new ByteArrayInputStream(src.getBytes("UTF-8"))) {
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    boolean isNumber(InputStream in) {
        try (InputStream input = in) {
            int value = input.read();
            if ((value % 2) == 0) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
