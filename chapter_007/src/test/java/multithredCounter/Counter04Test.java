package multithredCounter;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class Counter04Test {
    @Test
    public void whenThreadsSynchronizedThenDataNotLost() {
        Counter04 c = new Counter04();
        int result = c.start();
        assertThat(result, is(2_000_000));
    }
}