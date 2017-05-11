package ru.pravvich.jdbs.requestGeneration.requestBuilder;

/**
 * Request for addition item in DB.
 */
public class AddItem implements RequestBuilder {
    /**
     * Determines equals identifications of request.
     *
     * @param typeRequest searching type RequestBuilder.
     * @return true if identifications equals else false.
     */
    @Override
    public boolean requestTypeEqual(final String typeRequest) {
        return typeRequest.equals("add_item");
    }

    /**
     * Build request pattern for addition task in DB.
     * <p>
     * Example:
     * insert into tasks (task_id, author, header, description, create_time)
     * values (default, (?), (?), (?), now()::timestamp);
     *
     * @return request.
     */
    @Override
    public String build(String ... c) {

        final StringBuffer sb = new StringBuffer();

        sb.append("insert into tasks (task_id, author, header,");
        sb.append(" description, create_time) values (default,");
        sb.append(" (?), (?), (?), now()::timestamp) returning task_id");

        return new String(sb);
    }
}
