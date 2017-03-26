package multithredCounter;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class Counter01Test {
    @Test
    public void whenThreadsSynchronizedThenDataNotLost() {
        Counter01 c = new Counter01();
        int result = c.getCounter();
        assertThat(result, is(2_000_000));
    }
}