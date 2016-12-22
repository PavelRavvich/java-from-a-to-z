package ru.pravvich.isp.menu;

import ru.pravvich.isp.menu.paragraphs.Paragraph;

import java.util.ArrayList;

/**
 * Determines base algorithm show menu.
 */
interface Menu {
    void showMenu(ArrayList<Paragraph> paragraphs);
}
