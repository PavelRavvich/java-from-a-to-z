package demoGC;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    @Test
    public void whenThen() {
        GCStart gcStart = new GCStart();
        gcStart.checkGC();
    }
}