package ru.pravvich.jdbs.requestGeneration.requestBuilder;

public class FindByHeader implements RequestBuilder {
    @Override
    public boolean requestTypeEqual(final String typeRequest) {
        return typeRequest.equals("find_by_header");
    }

    @Override
    public String build(final String... conditions) {

        final StringBuffer sb = new StringBuffer();

        sb.append("select t.task_id, t.author, t.header, t.description, t.create_time, ");
        sb.append("c.comment_id, c.content from tasks as t left join comments as c on t");
        sb.append(".task_id = c.task_id where t.header = (?)");

        return new String(sb);
    }
}
