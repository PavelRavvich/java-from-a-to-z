package multithredCounter;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class Counter02Test {
    @Test
    public void whenThreadsSynchronizedThenDataNotLost() {
        Counter02 c = new Counter02(new Object(), 0);
        int result = c.start();
        assertThat(result, is(2_000_000));
    }
}