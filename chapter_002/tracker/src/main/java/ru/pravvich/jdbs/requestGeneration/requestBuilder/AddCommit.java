package ru.pravvich.jdbs.requestGeneration.requestBuilder;

public class AddCommit implements RequestBuilder {
    @Override
    public boolean requestTypeEqual(String typeRequest) {
        return typeRequest.equals("add_commit");
    }

    /**
     * @param conditions first arg = content comment, second arg = task_id.
     * @return request pattern.
     */
    @Override
    public String build(final String... conditions) {
        return "insert into comments (comment_id, content, task_id) values (default, (?), (?))";
    }
}
