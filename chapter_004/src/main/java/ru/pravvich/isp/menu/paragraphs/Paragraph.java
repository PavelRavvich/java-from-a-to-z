package ru.pravvich.isp.menu.paragraphs;

import java.util.ArrayList;

/**
 * Determines paragraph number 1
 */
public class Paragraph implements ParagraphInterface {
    /**
     * Key for synchronize with action.
     */
    private String key;
    /**
     * Name for print in console.
     */
    private String name;

    /**
     * Contain sub paragraphs.
     */
    private ArrayList<ParagraphInterface> paragraphs = new ArrayList<>();

    /**
     * Default constructor.
     * @param key for synchronize with action.
     * @param name for print in console.
     */
    public Paragraph(String key, String name) {
        this.key = key;
        this.name = name;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void add(Paragraph paragraph) {
        this.paragraphs.add(paragraph);
    }

    @Override
    public ArrayList<ParagraphInterface> getParagraphs() {
        return this.paragraphs;
    }
}
