package edu.buffalo.cse116;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class JuliaTest {

	@Test
	public void neverExceedDistance() {
		Julia juliaSet = new Julia();
		juliaSet.escapetime(1.0492187499999897, -0.234375);
		System.out.println(juliaSet.escapetime(1.0492187499999897, -0.234375));
		assertEquals(255, juliaSet.escapetime(1.0492187499999897, -0.234375), 0);
	}

	@Test
	public void singleloop() {
		Julia juliaSet = new Julia();
		juliaSet.escapetime(1.6933593749999853, 0.9765625);
		System.out.println(juliaSet.escapetime(1.6933593749999853, 0.9765625));
		assertEquals(1, juliaSet.escapetime(1.6933593749999853, 0.9765625), 0);
	}

	@Test
	public void testXaxis() {
		Julia juliaSet = new Julia();
		assertEquals(0, juliaSet.createJulia()[372][0], 0);
	}

	@Test
	public void testyaxis() {
		Julia juliaSet = new Julia();
		assertEquals(1, juliaSet.createJulia()[0][393], 0);
	}

	@Test
	public void fractalSizeTest() {
		Julia juliaSet = new Julia();
		assertEquals(512, juliaSet.createJulia()[0].length, 0);
		assertEquals(512, juliaSet.createJulia().length, 0);
	}

}
