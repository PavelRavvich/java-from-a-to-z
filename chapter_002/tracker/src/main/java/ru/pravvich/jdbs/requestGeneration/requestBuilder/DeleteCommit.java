package ru.pravvich.jdbs.requestGeneration.requestBuilder;

/**
 * Created by pavel on 09.05.17.
 */
public class DeleteCommit implements RequestBuilder {
    @Override
    public boolean requestTypeEqual(final String typeRequest) {
        return typeRequest.equals("delete_commit");
    }

    @Override
    public String build(final String... conditions) {
        return "delete from comments where content = (?)";
    }
}
