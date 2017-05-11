package ru.pravvich.jdbs.requestGeneration.requestBuilder;

public class AddDescription implements RequestBuilder {

    @Override
    public boolean requestTypeEqual(final String typeRequest) {

        return typeRequest.equals("add_description");
    }

    @Override
    public String build(final String... conditions) {

        return "update tasks set description = (?) where task_id = (?)";
    }
}
