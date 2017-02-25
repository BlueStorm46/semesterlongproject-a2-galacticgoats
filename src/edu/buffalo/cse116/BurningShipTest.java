package edu.buffalo.cse116;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class BurningShipTest {

	@Test
	public void test() {
		BurningShip burningSet = new BurningShip();
		burningSet.escapetime(-1.7443359374999874, -0.017451171875000338);
		System.out.println(burningSet.escapetime(-1.7443359374999874, -0.017451171875000338));
		assertEquals(2, burningSet.escapetime(-1.7443359374999874, -0.017451171875000338), 0);
	}

	@Test
	public void testescapefor0() {
		BurningShip burningSet = new BurningShip();
		burningSet.escapetime(-2, 2);
		System.out.println(burningSet.escapetime(-2, 2));
		assertEquals(0, burningSet.escapetime(-2, 2), 0);

	}

	@Test
	public void testescapefor1() {
		BurningShip burningSet = new BurningShip();
		burningSet.escapetime(-2, 2);
		System.out.println(burningSet.escapetime(-2, 2));
		assertEquals(1, burningSet.escapetime(-2, 2), 1);
	}

	@Test
	public void testXaxis() {
		BurningShip burningSet = new BurningShip();
		assertEquals(0, burningSet.createBS()[372][0], 0);
	}

	@Test
	public void testyaxis() {
		BurningShip burningSet = new BurningShip();
		assertEquals(22, burningSet.createBS()[0][300], 0);
	}

	@Test
	public void fractalSizeTest() {
		BurningShip burningSet = new BurningShip();
		assertEquals(512, burningSet.createBS()[0].length, 0);
		assertEquals(512, burningSet.createBS().length, 0);
	}

}
