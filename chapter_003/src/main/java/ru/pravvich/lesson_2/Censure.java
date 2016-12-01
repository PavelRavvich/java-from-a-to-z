package ru.pravvich.lesson_2;

import java.io.*;

public class Censure {

    void dropAbuses(InputStream src, OutputStream dst, String[] words) {
        try (Close close = new Close()) {
            int[] forCheck = new int[words.length];
            for (int i = 0; i != words.length; i++) {
                forCheck[i] = new ByteArrayInputStream(words[i].getBytes("UTF8")).read();
            }

            while (true) {
                boolean equal = true;
                int element = src.read();
                if (element != -1) {
                    int i;
                    for (i = 0; i != words.length; i++) {
                        if (forCheck[i] == element) {
                            equal = false;
                            break;
                        }
                    }
                    if (equal) dst.write(element);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class Close implements Closeable {
    @Override
    public void close() throws IOException {

    }
}
