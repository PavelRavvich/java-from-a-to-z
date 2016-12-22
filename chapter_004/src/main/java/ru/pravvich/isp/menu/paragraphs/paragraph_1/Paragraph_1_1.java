package ru.pravvich.isp.menu.paragraphs.paragraph_1;

import ru.pravvich.isp.menu.paragraphs.Paragraph;

import java.util.ArrayList;

/**
 * Determines paragraph number 1.1
 */
class Paragraph_1_1 implements Paragraph {

    /**
     * List contain sub paragraphs.
     */
    private ArrayList<Paragraph> paragraphs = new ArrayList<>();

    /**
     * Base constructor - init sub paragraphs.
     */
    Paragraph_1_1() {
        this.paragraphs.add(new Paragraph_1_1_1());
    }

    @Override
    public String getKey() {
        return "1.1";
    }

    @Override
    public String getName() {
        return "\t1.1 Paragraph_1_1";
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
