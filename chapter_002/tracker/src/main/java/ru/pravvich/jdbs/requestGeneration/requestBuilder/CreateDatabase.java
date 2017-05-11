package ru.pravvich.jdbs.requestGeneration.requestBuilder;

public class CreateDatabase implements RequestBuilder {

    @Override
    public boolean requestTypeEqual(final String typeRequest) {
        return typeRequest.equals("create_database");
    }

    @Override
    public String build(final String ... c) {

        if (c.length > 0) return String.format("create database %s", c[0]);

        return "create database auto_services";
    }
}
