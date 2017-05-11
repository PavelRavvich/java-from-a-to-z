package ru.pravvich.jdbs.requestGeneration.requestBuilder;

/**
 * Build request for build check exists database 'auto_services'.
 */
public class DBExists implements RequestBuilder {

    @Override
    public boolean requestTypeEqual(final String typeRequest) {
        return typeRequest.equals("DBExist");
    }

    @Override
    public String build(final String ... c) {

        if (c.length > 0) return String.format(
                "select 1 as result from pg_database where datname = '%s'", c[0]);

        return "select 1 as result from pg_database where datname = 'auto_services'";
    }
}
