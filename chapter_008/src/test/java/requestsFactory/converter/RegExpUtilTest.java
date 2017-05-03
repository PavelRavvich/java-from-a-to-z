package requestsFactory.converter;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class RegExpUtilTest {
    @Test
    public void whenRexExpInThenPostgresFormatReturn() {
        Converter pc = new RegExpUtil();
        String result = pc.convertToPostgres("table", "attribute", "ab");
        Assert.assertThat(result, is("SELECT * FROM table WHERE attribute LIKE '%ab%';"));
    }
}