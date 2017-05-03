package requestsFactory.converter;

/**
 * Determines all converters for generate Postgres requests.
 */
public interface Converter {
    /**
     * Generate postgresSQL script.
     * @param val see in  implementation class.
     * @return see in  implementation class.
     */
    String convertToPostgres(final String ... val);

    /**
     * For work Command Design Pattern.
     * Determines what kind of implementation use.
     * @param typeFlag request for choice implementation.
     * @return true if flag equals, else false.
     */
    boolean typeEquals(final String typeFlag);
}
