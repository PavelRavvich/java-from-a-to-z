package ru.pravvich.isp.menu.paragraphs;

/**
 * Determines paragraph number 1
 */
public class Paragraph implements ParagraphInterface {
    /**
     * Key for synchronize with action.
     */
    private final String key;
    /**
     * Name for print in console.
     */
    private final String name;

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
}
