package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PointTest {	
	@Test
	public void wthenSetPointThenResultDistanceBetweenPoint() {
			Point a = new Point(0, 0);
			Point b = new Point(0, 4);
			double result = a.distanceTo(b); 						
			assertThat(result, is(4D));
	}
}



