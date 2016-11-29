package ru.pravvich.lesson_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Check {

    private int number;

    public boolean checkInput(InputStream in) {
        try (Exit exit = new Exit("Exit")) {
            this.number = in.read();
            System.out.println(exit.getMassage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.number % 2 == 0) return true;
        return false;
    }

    private class Exit implements AutoCloseable {
        private String massage;

        private String getMassage() {
            return massage;
        }

        private Exit(String massage) {
            this.massage = massage;
        }

        @Override
        public void close() throws Exception {
        }
    }
}

class Input extends InputStream {
    @Override
    public int read() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return bufferedReader.read();
    }
}
