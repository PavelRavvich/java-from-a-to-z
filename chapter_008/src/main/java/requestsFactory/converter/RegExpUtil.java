package requestsFactory.converter;

import static java.lang.String.format;
import static requestsFactory.converter.PostgresKeyWords.*;

/**
 * Decorator for SelectAllUtil with add regexp by attribute.
 * example: SELECT * FROM table WHERE attribute LIKE '%regexp%';
 */
public class RegExpUtil implements Converter {
    /**
     * Use for generate first part request.
     */
    private final Converter selectAll;

    /**
     * Default constructor.
     */
    public RegExpUtil() {

        this.selectAll = new SelectAllUtil();

    }

    /**
     * Generate request for get all attributes for table which satisfy the condition regExp value.
     *
     * @param values for this method use tree param.
     *               first param = name table,
     *               second param = attribute table,
     *               treed param = condition for regExp.
     * @return request all attributes for table which satisfy the condition regExp value.
     */
    @Override
    public String convertToPostgres(final String... values) {

        final String tableName = values[0];
        final String tableAttribute = values[1];
        final String conditionValue = values[2];

        final String selectAll = this.selectAll.convertToPostgres(tableName);

        return format("%s %s %s %s %s%s%s%s",

                selectAll.replaceAll(";", ""), WHERE, tableAttribute, LIKE,

                REG_EXP_OPEN.strValue(), conditionValue, REG_EXP_CLOSE.strValue(),

                END.strValue());

    }

    @Override
    public boolean typeEquals(final String typeFlag) {

        return "reg_exp".equals(typeFlag);

    }
}
