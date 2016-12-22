package ru.pravvich.isp.menu.paragraphs.paragraph_1;

import ru.pravvich.isp.menu.paragraphs.Paragraph;

import java.util.ArrayList;

/**
 * Determines paragraph number 1.2
 */
class Paragraph_1_2 implements Paragraph {

    /**
     * List contain sub paragraphs.
     */
    private ArrayList<Paragraph> paragraphs = new ArrayList<>();

    @Override
    public String getKey() {
        return "1.2";
    }

    @Override
    public String getName() {
        return "\t1.2 Paragraph_1_2";
    }

    @Override
    public ArrayList getParagraphs() {
        return this.paragraphs;
    }

    @Override
    public void setParagraph(ArrayList<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
