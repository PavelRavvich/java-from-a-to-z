package ru.pravvich.jdbs.requestGeneration.requestBuilder;

/**
 * todo
 */
public interface RequestBuilder {
    /**
     *
     * @param typeRequest
     * @return
     */
    boolean requestTypeEqual(final String typeRequest);

    /**
     *
     * @param conditions
     * @return
     */
    String build(String ... conditions);
}
