package requestsFactory.converter;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class TaskWithCommentUtilTest {
    @Test
    public void whenThen() {
        Converter c = new TaskWithCommentUtil();
        String result = c.convertToPostgres("1");
        Assert.assertThat(result, is("SELECT t.name_task, " +
                "c.body_comment FROM tasks AS t FULL OUTER" +
                " JOIN comments AS c ON c.user_id = t.user" +
                "_id AND t.user_id = '1';"));
    }
}