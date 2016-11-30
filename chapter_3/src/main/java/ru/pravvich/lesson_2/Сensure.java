package ru.pravvich.lesson_2;

import java.io.*;

public class Сensure {






    class Output extends OutputStream {

        @Override
        public void write(int b) throws IOException {

        }
    }


    class Input extends InputStream {
        @Override
        public int read() throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            return bufferedReader.read();
        }
    }
}
