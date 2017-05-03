package requestsFactory.converter;

import static requestsFactory.converter.PostgresKeyWords.END;

public class SelectAllUtil implements Converter {

    /**
     * Request for get all table's values.
     * @param table use one value - name table.
     * @return request in postgres type.
     * Example: SELECT * FROM table;
     */
    @Override
    public String convertToPostgres(final String ... table) {

        return String.format("SELECT * FROM %s%s", table[0], END.strValue());

    }

    @Override
    public boolean typeEquals(final String typeFlag) {

        return "select_all".equals(typeFlag);

    }
}
