package requestsFactory.converter;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class SelectAllUtilTest {
    @Test
    public void whenParamInConvertEqualsAllThenReturnSelectAll() {
        Converter pc = new SelectAllUtil();
        String result = pc.convertToPostgres("table");
        Assert.assertThat(result, is("SELECT * FROM table;"));
    }
}