package ru.pravvich.jdbs.requestGeneration.requestBuilder;

public class EditCommit implements RequestBuilder {

    @Override
    public boolean requestTypeEqual(final String typeRequest) {
        return typeRequest.equals("edit_commit");
    }

    @Override
    public String build(final String... conditions) {
        return "update comments set content = (?) where content = (?)";
    }
}
