package problemsMultithreding;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProblemsTest {
    @Test
    public void whenVariableNotSynchronizedThenDataIsLost() {
        Problems problems = new Problems();
        int counter = problems.demo();

        boolean result = counter < 2_000_000;
        assertThat(result, is(true));
    }
}