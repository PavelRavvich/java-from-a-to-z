package multithredCounter;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class Counter03Test {
    @Test
    public void whenThreadsSynchronizedThenDataNotLost() {
        Counter03 c = new Counter03();
        int result = c.start();
        assertThat(result, is(2_000_000));
    }
}