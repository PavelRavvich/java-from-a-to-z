package ru.pravvich.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Load property.
 * For loading jdbc requests.
 */
public class PropertiesLoader {
    /**
     * Represents a persistent set of properties.
     */
    private final Properties properties;

    /**
     * Default constructor.
     *
     * @param fileName which contain requests strings.
     */
    public PropertiesLoader(final String fileName) {
        this.properties = new Properties();
        load(fileName);
    }

    /**
     * Load all data.
     *
     * @param fileName of  data contains
     */
    private void load(final String fileName) {
        try (final InputStream input =
                     Thread
                             .currentThread()
                             .getContextClassLoader()
                             .getResourceAsStream(fileName)
        ) {

            properties.load(input);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get value.
     *
     * @param key of value.
     * @return value of Properties by key.
     */
    public String get(final String key) {
        return properties.getProperty(key);
    }
}
