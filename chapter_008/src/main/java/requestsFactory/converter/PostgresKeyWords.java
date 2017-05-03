package requestsFactory.converter;

public enum PostgresKeyWords {
    SELECT_ALL_FROM("SELECT * FROM"),
    INNER_JOIN("INNER JOIN"),
    RETURNING("RETURNING"),
    REG_EXP_CLOSE("%'"),
    BETWEEN("BETWEEN"),
    DEFAULT("DEFAULT"),
    REG_EXP_OPEN("'%"),
    SELECT("SELECT"),
    UPDATE("UPDATE"),
    INSERT("INSERT"),
    VALUES("VALUES"),
    NOT_EQUAL("!="),
    WHERE("WHERE"),
    FROM("FROM"),
    LIKE("LIKE"),
    NULL("NULL"),
    INTO("INTO"),
    SET("SET"),
    NOT("NOT"),
    MORE(">"),
    LESS("<"),
    STR("'"),
    IF("IF"),
    IS("IS"),
    AS("AS"),
    ON("ON"),
    END(";"),
    ALL("*");

    private String v;

    PostgresKeyWords(String v) {
        this.v = v;
    }

    public String strValue() {
        return v;
    }
}
