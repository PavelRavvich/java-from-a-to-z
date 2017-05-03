package requestsFactory;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class SelectTest {
    @Test
    public void whenCallWithRegExpThenReturnRequestWithRexExpInPostgresStyle() {

        Request r = new Select();

        String flag = "reg_exp";
        String table = "users";
        String attribute = "name";
        String regExp = "jo";

        String result = r.generate(flag, table, attribute, regExp);
        Assert.assertThat(result, is("SELECT * FROM users WHERE name LIKE '%jo%';"));
    }

    @Test
    public void whenCallEntireTableThenReturnRequestForGetEntryTableInPostgresStyle() {

        Request r = new Select();

        String flag = "select_all";
        String table = "users";

        String result = r.generate(flag, table);
        Assert.assertThat(result, is("SELECT * FROM users;"));
    }

    @Test
    public void whenThen() {
        Request r = new Select();

        String flag = "get_task_with_comments_body";
        String userId = "2";

        String result = r.generate(flag, userId);
        Assert.assertThat(result, is("SELECT t.name_task," +
                " c.body_comment FROM tasks AS t FULL OUTER" +
                " JOIN comments AS c ON c.user_id = t.user_id " +
                "AND t.user_id = '2';"));
    }
}