package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculateTest {	
	@Test
	public void wthenSetAdditionThenArgumentAddition() {
			Calculate calc = new Calculate();
			calc.add(2D, 3D);
			double result = calc.getResult(); 						
			assertThat(result, is(5D));
	
	}
	
	@Test
	public void wthenSetSubstractThenArgumentSubstract() {
			Calculate calc = new Calculate();
			calc.substract(3D, 2D);
			double result = calc.getResult(); 						
			assertThat(result, is(1D));
	
	}
	
	@Test
	public void wthenSetMultiplicationThenArgumentMultiplication() {
			Calculate calc = new Calculate();
			calc.mult(3D, 2D);
			double result = calc.getResult(); 						
			assertThat(result, is(6D));
	
	}	
	
	@Test
	public void wthenSetDivisionThenArgumentDivision() {
			Calculate calc = new Calculate();
			calc.div(6D, 2D);
			double result = calc.getResult(); 						
			assertThat(result, is(3D));
	
	}		

}



