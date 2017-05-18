package ru.pravvich.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load properties from file.
 */
public class PropertiesLoader {
    /**
     * Represents a persistent set of properties.
     */
    private final Properties properties;

    /**
     * Default constructor.
     *
     * @param filename of .properties format from resources folder.
     */
    public PropertiesLoader(final String filename) {

        this.properties = new Properties();

        this.load(filename);
    }

    /**
     * Work with inner .properties file.
     *
     * @param key which associated with target value.
     * @return value by key.
     */
    public String getValue(final String key) {

        return this.properties.getProperty(key);

    }

    /**
     * Load whole .properties file in instance of field Properties.
     *
     * @param filename of .properties format from resources folder.
     */
    private void load(final String filename) {

        try (final InputStream input = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(filename)
        ) {


            this.properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
