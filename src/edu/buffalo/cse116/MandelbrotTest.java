package edu.buffalo.cse116;

import static org.junit.Assert.*; 

import org.junit.AfterClass;
import org.junit.Test;

public class MandelbrotTest {

	@Test
	public void neverExceedDistance() {
		Mandelbrot mandelSet = new Mandelbrot();
		mandelSet.escapetime(0.3207031250000001, -0.07109374999999386);
		System.out.println(mandelSet.escapetime(0.3207031250000001, -0.0710937499999938));
		assertEquals(4, mandelSet.escapetime(0.3207031250000001, -0.0710937499999938), 0);
	}
	@Test
	public void neverSingleloop() {
		Mandelbrot mandelSet = new Mandelbrot();
		mandelSet.escapetime(0.5946289062500001, 1.2949218750000122);
		System.out.println(mandelSet.escapetime(0.5946289062500001, 1.2949218750000122));
		assertEquals(255, mandelSet.escapetime(0.5946289062500001, 1.2949218750000122), 0);
	}
		
		@Test
		public void testXaxis() {
			Mandelbrot mandelSet = new Mandelbrot();
			assertEquals(0, mandelSet.createMandel()[372][0], 0);
	}
		@Test
		public void testyaxis() {
			Mandelbrot mandelSet = new Mandelbrot();
			assertEquals(0, mandelSet.createMandel()[0][393], 0);
	}
		@Test
		public void fractalSizeTest() { 
			Mandelbrot mandelSet = new Mandelbrot();
			assertEquals(512, mandelSet.createMandel()[0].length, 0);
			assertEquals(512, mandelSet.createMandel().length, 0);
		}

}
