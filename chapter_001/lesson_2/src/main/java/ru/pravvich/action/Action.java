package ru.pravvich.action;

import java.util.Scanner;

interface Action {
    String name();
    void invoke(Scanner scanner);
}
