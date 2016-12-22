package ru.pravvich.isp.menu.paragraphs.paragraph_2;

import ru.pravvich.isp.menu.paragraphs.Paragraph;

import java.util.ArrayList;

/**
 * Determines paragraph number 2
 */
public class Paragraph_2 implements Paragraph {

    /**
     * List contain sub paragraphs.
     */
    private ArrayList<Paragraph> paragraphs = new ArrayList<>();

    @Override
    public String getKey() {
        return "1";
    }

    @Override
    public String getName() {
        return "2. Paragraph_2";
    }

    @Override
    public ArrayList<Paragraph> getParagraphs() {
        return this.paragraphs;
    }

    @Override
    public void setParagraph(ArrayList<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }
}
