package ru.pravvich.isp.menu;

import ru.pravvich.isp.menu.paragraphs.ParagraphInterface;

import java.util.ArrayList;

/**
 * Determines base algorithm show menu.
 */
interface Menu {
    void showMenu(ArrayList<ParagraphInterface> paragraphs);
}
