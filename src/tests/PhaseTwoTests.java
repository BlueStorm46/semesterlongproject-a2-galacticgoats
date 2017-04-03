package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import code.fractals.*;

public class PhaseTwoTests {

	/** o When the escape distance is set to 3, calculates the escape time for a coordinate whose distance from the origin exceeds the escape distance after 10 passes of the loop [4 * 6 points = 24 points]
			- Mandelbrot Set: (0.46007827788650374, -0.3383561643835661)
			- Julia Set: 1.4538160469667272, -0.13502935420743645)
			- Burning Ship Set: (-1.6999999999999802, 0.0030136986301371603)
			- Multibrot Set: (0.7025440313111545, -0.5520547945205528) */
	
	@Test
	  public final void MandelbrotTenLoopPasses() {
		Mandelbrot ma = new Mandelbrot();
	    assertEquals(10, ma.escapeTime(0.46007827788650374, -0.33835616438356616, 3, 255), 0.001);
	  }
	@Test
	  public final void JuliaTenLoopPasses() {
	    Julia j = new Julia();
	    assertEquals(10, j.escapeTime(1.4538160469667272, -0.13502935420743645, 3, 255), 0.001);
	  }
	@Test
	  public final void BurningShipTenLoopPasses() {
	    BurningShip bs = new BurningShip();
	    assertEquals(10, bs.escapeTime(-1.6999999999999802, 0.0030136986301371603, 3, 255), 0.001);
	  }
	@Test
	  public final void MultibrotTenLoopPasses() {
		Multibrot mu = new Multibrot();
	    assertEquals(10, mu.escapeTime(0.7025440313111545, -0.5520547945205528, 3, 255), 0.001);
	  }
} 