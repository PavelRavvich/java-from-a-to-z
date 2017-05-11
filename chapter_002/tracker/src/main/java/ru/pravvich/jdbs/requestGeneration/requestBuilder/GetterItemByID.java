package ru.pravvich.jdbs.requestGeneration.requestBuilder;

/**
 * Build pattern for get item by id.
 */
public class GetterItemByID implements RequestBuilder {

    /**
     * Determines equals identifications of request.
     *
     * @param typeRequest searching type RequestBuilder.
     * @return true if identifications equals else false.
     */
    @Override
    public boolean requestTypeEqual(final String typeRequest) {
        return typeRequest.equals("find_by_id");
    }

    /**
     * Build request pattern for get item from table task in DB.
     * <p>
     * Example:
     * <p>
     * select
     * t.task_id, t.author, t.header, t.description, t.create_time, c.comment_id, c.content
     * from tasks as
     * left join comments as c on t.task_id = c.task_id where t.task_id = (?)
     *
     * @return request.
     */
    @Override
    public String build(final String ... c) {

        final StringBuffer sb = new StringBuffer();

        sb.append("select t.task_id, t.author, t.header, ");
        sb.append("t.description, t.create_time, c.commen");
        sb.append("t_id, c.content from tasks as t left ");
        sb.append("join comments as c on t.task_id = c.ta");
        sb.append("sk_id where t.task_id = (?)");

        return new String(sb);
    }
}
