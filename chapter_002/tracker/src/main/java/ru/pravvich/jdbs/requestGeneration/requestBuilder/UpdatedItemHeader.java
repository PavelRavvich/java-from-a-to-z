package ru.pravvich.jdbs.requestGeneration.requestBuilder;

/**
 * Generate request for update item header.
 */
public class UpdatedItemHeader implements RequestBuilder {
    @Override
    public boolean requestTypeEqual(String typeRequest) {
        return typeRequest.equals("update_item");
    }

    @Override
    public String build(String... conditions) {
        return "update tasks set header = (?) where task_id = (?)";
    }
}
