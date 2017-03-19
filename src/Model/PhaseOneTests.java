package Model;

import static org.junit.Assert.*;
import org.junit.Test;

/**	o Translate a pixel's row to the associated x-coordinate in the fractal (1 test per fractal) [4 * 5 points = 20 points]
	o Translate a pixel's column to the associated y-coordinate in the fractal (1 test per fractal) [4 * 5 points = 20 points]
	o Calculates the escape time for a coordinate whose distance from the origin never exceeds the escape distance [4 * 6 points = 24 points]
		- Mandelbrot Set: (0.3207031250000001, -0.07109374999999386)
		- Julia Set: (1.0492187499999897, -0.234375)
		- Burning Ship Set: (-1.7443359374999874, -0.017451171875000338)
		- Multibrot Set: (0.5859375, 0.24375000000000108)
	o Calculates the escape time for a coordinate whose distance from the origin exceeds the escape distance after a single loop pass [3 * 6 points = 18 points]
		- Mandelbrot Set: (0.5946289062500001, 1.2949218750000122)
		- Julia Set: (1.6933593749999853, 0.9765625)
		- Multibrot Set: (0.9921875, 1.05625)
	o Calculates that none of the pixels in the Burning Ship set have an escape time of 0 or 1 [8 points]
	o The method called to calculate the fractal returns a 2-d array with 512 rows and 512 columns (1 test per fractal) [4 * 2 points = 8 points]*/

public class PhaseOneTests {
	/** Translate a pixel's row to the associated x-coordinate in the fractal (1 test per fractal) */
	@Test
	public void MandelbrotXAxis() {
		Mandelbrot ma = new Mandelbrot();
		assertEquals(0, ma.createMandel(2)[372][0], 0);
	}
	@Test
	public void JuliaXAxis() {
		Julia j = new Julia();
		assertEquals(0, j.createJulia(2)[372][0], 0);
	}
	@Test
	public void BurningShipXAxis() {
		BurningShip bs = new BurningShip();
		assertEquals(255, bs.createBS(2)[372][0], 0);
	}
	@Test
	public void MultibrotXAxis() {
		Multibrot mu = new Multibrot();
		assertEquals(1, mu.createMulti(2)[372][0], 0);
	}
	
	/** Translate a pixel's column to the associated y-coordinate in the fractal (1 test per fractal) */
	@Test
	public void MandelbrotYAxis() {
		Mandelbrot ma = new Mandelbrot();
		assertEquals(0, ma.createMandel(2)[0][393], 0);	
	}
	@Test
	public void JuliaYAxis() {
		Julia j = new Julia();
		assertEquals(1, j.createJulia(2)[0][393], 0);
	}
	@Test
	public void BurningShipYAxis() {
		BurningShip bs = new BurningShip();
		assertEquals(255, bs.createBS(2)[0][393], 0);
	}
	@Test
	public void MultibrotYAxis() {
		Multibrot mu = new Multibrot();
		assertEquals(0, mu.createMulti(2)[0][393], 0);
	}
	
	/** Calculates the escape time for a coordinate whose distance from the origin never exceeds the escape distance */
	@Test
	  public final void MandelbrotNeverExceedsEscapeDistance() {
		Mandelbrot ma = new Mandelbrot();
	    assertEquals(255, ma.escapeTime(0.3207031250000001, -0.07109374999999386, 2), 0.001);
	  }
	@Test
	  public final void JuliaNeverExceedsEscapeDistance() {
	    Julia j = new Julia();
	    assertEquals(255, j.escapeTime(1.0492187499999897, -0.234375, 2), 0.001);
	  }
	@Test
	  public final void BurningShipNeverExceedsEscapeDistance() {
	    BurningShip bs = new BurningShip();
	    assertEquals(255, bs.escapeTime(-1.7443359374999874, -0.017451171875000338, 2), 0.001);
	  }
	@Test
	  public final void MultibrotNeverExceedsEscapeDistance() {
		Multibrot mu = new Multibrot();
	    assertEquals(255, mu.escapeTime(0.5859375, 0.24375000000000108, 2), 0.001);
	  }

	/** Calculates the escape time for a coordinate whose distance from the origin exceeds the escape distance after a single loop pass */
	@Test
	  public final void MandelbrotExceedsExcapeDistanceAfterOneLoop() {
		Mandelbrot ma = new Mandelbrot();
	    assertEquals(1, ma.escapeTime(0.5946289062500001, 1.2949218750000122, 2), 0.001);
	  }
	@Test
	  public final void JuliaExceedsExcapeDistanceAfterOneLoop() {
	    Julia j = new Julia();
	    assertEquals(1, j.escapeTime(1.6933593749999853, 0.9765625, 2), 0.001);
	  }
	@Test
	  public final void MultibrotExceedsExcapeDistanceAfterOneLoop() {
		Multibrot mu = new Multibrot();
	    assertEquals(1, mu.escapeTime(0.9921875, 1.05625, 2), 0.001);
	}
	
	/** Calculates that none of the pixels in the Burning Ship set have an escape time of 0 or 1 */
	@Test
	public void BurningShipEscapeDistance0() {
		BurningShip burningSet = new BurningShip();
		assertEquals(0, burningSet.escapeTime(-2, 2, 2), 0);
	}
	@Test
	public void BurningShipEscapeDistance1() {
		BurningShip burningSet = new BurningShip();
		assertEquals(1, burningSet.escapeTime(-2, 2, 2), 1);
	}
	
	/** The method called to calculate the fractal returns a 2-d array with 512 rows and 512 columns (1 test per fractal) */
	@Test
	public void MandelbrotFractalSize() { 
		Mandelbrot ma = new Mandelbrot();
		assertEquals(512, ma.createMandel(2)[0].length, 0);
		assertEquals(512, ma.createMandel(2).length, 0);
	}
	@Test
	public void JuliaFractalSize() {
		Julia j = new Julia();
		assertEquals(512, j.createJulia(2)[0].length, 0);
		assertEquals(512, j.createJulia(2).length, 0);
	}
	@Test
	public void BurningShipFractalSize() {
		BurningShip bs = new BurningShip();
		assertEquals(512, bs.createBS(2)[0].length, 0);
		assertEquals(512, bs.createBS(2).length, 0);
	}
	@Test
	public void MultibrotFractalSize() { 
		Multibrot mu = new Multibrot();
		assertEquals(512, mu.createMulti(2)[0].length, 0);
		assertEquals(512, mu.createMulti(2).length, 0);
	}
}