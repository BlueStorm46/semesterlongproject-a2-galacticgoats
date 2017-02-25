package edu.buffalo.cse116;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class MultibrotTest {

	@Test
	public void test() {
		Multibrot multiSet = new Multibrot();
		multiSet.escapetime(0.5859375 , 0.24375000000000108);
		System.out.println(multiSet.escapetime(0.5859375 , 0.24375000000000108));
		assertEquals(5, multiSet.escapetime(0.5859375, 0.24375000000000108));
		
	}
	
	@Test
	public void test2() {
		Multibrot multiSet = new Multibrot();
		multiSet.escapetime(0.9921875, 1.05625);
		System.out.println(multiSet.escapetime(0.9921875, 1.05625));
		assertEquals(1, multiSet.escapetime(0.9921875, 1.05625));
	}
	@Test
	public void testXaxis() {
		Multibrot multiSet = new Multibrot();
		assertEquals(1, multiSet.createMulti()[372][0], 0);
	}
	@Test
	public void testYaxis() {
		Mandelbrot	multiSet = new Mandelbrot();
		assertEquals(0, multiSet.createMandel()[0][393], 0);
	}
	@Test
	public void fractalSizeTest() { 
		Mandelbrot multiSet = new Mandelbrot();
		assertEquals(512, multiSet.createMandel()[0].length, 0);
		assertEquals(512, multiSet.createMandel().length, 0);
	}
	
	

}
