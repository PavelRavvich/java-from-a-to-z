package ru.pravvich.isp.menu.paragraphs;

import java.util.ArrayList;

/**
 * Determines get and set for paragraphs(sub paragraphs) field.
 */
interface ParagraphSub {
    ArrayList getParagraphs();
    void setParagraph(ArrayList<Paragraph> paragraphs);
}
