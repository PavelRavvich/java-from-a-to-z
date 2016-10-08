package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TriangleTest {	
	@Test
	public void wthenSetPointsThenResultAreaTriangle() {
			Point a = new Point(0,0);
			Point b = new Point(0,3);
			Point c = new Point(4,0);
			
			Triangle triangle = new Triangle(a, b, c);
			
			double result = triangle.area(a, b, c); 						
			assertThat(result, is(6d));
	
	}
}



