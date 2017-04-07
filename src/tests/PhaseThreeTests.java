package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import code.fractals.BurningShip;
import code.fractals.Julia;
import code.fractals.Mandelbrot;
import code.fractals.Multibrot;

public class PhaseThreeTests {

	/** o When the maximum escape time is set to 135 and the escape distance is set to 2, calculates the escape time for a coordinate whose distance from the origin never exceeds the escape distance [4 * 6 points = 24 points]
			- Mandelbrot Set: (0.3207031250000001, -0.07109374999999386)
    		- Julia Set: (1.0492187499999897, -0.234375)
    		- Burning Ship Set: (-1.7443359374999874, -0.017451171875000338)
    		- Multibrot Set: (0.5859375, 0.24375000000000108) */

	@Test
	public final void Mandelbrot135Loops() {
		Mandelbrot ma = new Mandelbrot();
		assertEquals(135, ma.escapeTime(0.3207031250000001, -0.07109374999999386, 2, 135), 0.001);
	}

	@Test
	public final void Julia135Loops() {
		Julia j = new Julia();
		assertEquals(135, j.escapeTime(1.0492187499999897, -0.234375, 2, 135), 0.001);
	}

	@Test
	public final void BurningShip135Loops() {
		BurningShip bs = new BurningShip();
		assertEquals(135, bs.escapeTime(-1.7443359374999874, -0.017451171875000338, 2, 135), 0.001);
	}

	@Test
	public final void Multibrot135Loops() {
		Multibrot mu = new Multibrot();
		assertEquals(135, mu.escapeTime(0.5859375, 0.24375000000000108, 2, 135), 0.001);
	}
}
