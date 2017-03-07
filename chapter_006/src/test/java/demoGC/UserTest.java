package demoGC;

import org.junit.Test;

public class UserTest {
    @Test
    public void whenThen() {
        GCStart gcStart = new GCStart();
        gcStart.checkGC();
    }
}