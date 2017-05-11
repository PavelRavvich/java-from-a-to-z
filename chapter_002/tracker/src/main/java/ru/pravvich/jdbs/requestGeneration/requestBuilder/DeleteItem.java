package ru.pravvich.jdbs.requestGeneration.requestBuilder;

/**
 * delete from tasks where task_id = (?)
 */
public class DeleteItem implements RequestBuilder {

    @Override
    public boolean requestTypeEqual(final String typeRequest) {
        return typeRequest.equals("delete_item");
    }

    @Override
    public String build(final String ... c) {
        return "delete from tasks where task_id = (?)";
    }
}
