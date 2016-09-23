package ru.pravvich;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CalculateTest {	
	@Test
	public void wthenSetAdditionThenArgumentAddition() {
			Calculate calc = new Calculate();
			
		    //А как мне указать что мне нужен getResoult записанный из метода add?
			calc.add(2D, 3D);
			double result = calc.getResult(); 						
			assertThat(result, is(5D));
	
	}

}



