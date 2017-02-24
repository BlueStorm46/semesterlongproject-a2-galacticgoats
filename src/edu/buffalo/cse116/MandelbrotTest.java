package edu.buffalo.cse116;

import static org.junit.Assert.*; 

import org.junit.AfterClass;
import org.junit.Test;

public class MandelbrotTest {

	@Test
	public void test() {
		Mandelbrot mandelSet = new Mandelbrot();
		mandelSet.escapetime(0.3207031250000001, -0.07109374999999386);
		System.out.print(mandelSet.escapetime(0.3207031250000001, -0.0710937499999938));
		assertEquals(4, mandelSet.escapetime(0.3207031250000001, -0.0710937499999938), 0);
		
	}

}
