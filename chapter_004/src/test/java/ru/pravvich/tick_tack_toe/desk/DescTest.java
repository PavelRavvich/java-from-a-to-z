package ru.pravvich.tick_tack_toe.desk;

import org.junit.Test;
import ru.pravvich.tick_tack_toe.StubInput;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Tests for init desc size.
 */
public class DescTest {

    /**
     * Test choice standard desc size.
     * @see Desc#initDescSize()
     */
    @Test
    public void whenUserChoiceStandardDescSizeThenDeskSizeEqualTreeToTree() {
        StubInput input = new StubInput();
        Board desc = new Desc(input);
        input.setAnswersStr(new String[]{"y"});
        desc.setInput(input);

        desc.initDescSize();

        int result = desc.getDesc().length;
        assertThat(result, is(3));
    }

    /**
     * Test case when user choice nonstandard desc size.
     */
    @Test
    public void whenUserChoiceNonstandardThenSizeThenDeskSizeEqualUserInput() {
        StubInput input = new StubInput();
        Board desc = new Desc(input);
        input.setAnswersStr(new String[]{"n"});
        input.setAnswersNum(new int[]{5});
        desc.setInput(input);

        desc.initDescSize();

        int result = desc.getDesc().length;
        assertThat(result, is(5));
    }
}