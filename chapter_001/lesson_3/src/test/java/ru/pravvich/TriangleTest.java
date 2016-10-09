package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TriangleTest {	
	@Test
	public void wthenSetPointsThenResultAreaTriangle() {
		
		//Вычисление площади для нормального треугольника
		Point a = new Point(0,0);
		Point b = new Point(0,3);
		Point c = new Point(4,0);
		Triangle triangle = new Triangle(a, b, c);
		double result = triangle.area(a, b, c); 						
		assertThat(result, is(6d));
	}
	
	
	@Test
    public void whenTheSumOfAnyTwoSidesIsGreaterThanOrEqualToAThirdThenLeaveAMessageTriangleDoesNotExist() {

        //Если сумма двух любых сторон больше или равна третей.
        Point aUnreal = new Point(1,1);
        Point bUnreal = new Point(1,2);
        Point cUnreal = new Point(1,3);
        Triangle triangleExist = new Triangle(aUnreal, bUnreal, cUnreal);
        double resultUnreal = triangleExist.area(aUnreal, bUnreal, cUnreal);
        assertThat(resultUnreal, is(-1D));
    }
}



