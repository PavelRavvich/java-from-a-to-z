package ru.pravvich.isp.menu.paragraphs;

import java.util.ArrayList;

/**
 * Work with sub paragraphs.
 */
interface SubMenu {
    void add(Paragraph paragraph);
    ArrayList<ParagraphInterface> getParagraphs();
}
