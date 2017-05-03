package requestsFactory.converter;

/**
 * Example:
 * SELECT t.name_task, c.body_comment FROM tasks AS t FULL OUTER JOIN comments AS c ON c.user_id = t.user_id AND t.user_id = '1';
 */
public class TaskWithCommentUtil implements Converter {

    /**
     * Implementation without decorators.
     * @param userId user id.
     * @return join name_task from tasks and body_comment from comments.
     */
    @Override
    public String convertToPostgres(final String ... userId) {

        final StringBuffer sb = new StringBuffer()

                .append("SELECT t.name_task, c.body_comment FROM " )
                .append("tasks AS t FULL OUTER JOIN comments AS c ")
                .append("ON c.user_id = t.user_id AND t.user_id = ");

        return String.format("%s'%s';", new String(sb), userId[0]);

    }

    @Override
    public boolean typeEquals(final String typeFlag) {

        return "get_task_with_comments_body".equals(typeFlag);

    }

}
