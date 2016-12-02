package ru.pravvich.lesson_2;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class CensureInThread {
    void dropAbuses(InputStream src, OutputStream dst, String[] words) {
        try (OutputStream dest = dst; InputStream sours = src; Scanner in = new Scanner(sours)) {
            while (true) {
                if (sours.read() != -1) {
                    boolean write = true;
                    int i;
                    String inner = in.nextLine();
                    //System.out.println(inner);
                    for (i = 0; i != words.length; i++) {
                        if (inner.equals(words[i])) {
                            write = false;
                            break;
                        }
                    }

                    if (write && (i == (words.length - 1) )) {
                        dest.write(inner.getBytes());
                    }
                } else {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
