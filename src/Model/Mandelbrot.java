package Model;

public class Mandelbrot {

	public double escapeTime(double xCalc, double yCalc, int escapeDistance) {
		double distance = Math.sqrt(Math.pow(xCalc, 2) + Math.pow(yCalc, 2));
		int passes = 0;
		double xCord = xCalc; // Current point's x-coordinate
		double yCord = yCalc; // Current point's y-coordinate
		while (distance <= escapeDistance && passes < 255) {
			/** x' = x² - y² + current point's x-coordinate
				y' = 2 * x * y + current point's y-coordinate
   					Where x and y are the values of xCalc and yCalc prior to this update and x' and y' are their values after the update. */
			double tempX = xCalc; // x
			double tempY = yCalc; // y
			xCalc = ((Math.pow(tempX, 2) - Math.pow(tempY, 2)) + xCord); 
			yCalc = (2 * tempX * tempY) + yCord; 
			passes = passes + 1; 
			distance = Math.sqrt(Math.pow(xCalc, 2) + Math.pow(yCalc, 2));
		}
		return passes;
	}

	public int[][] createMandel() { 
		Mandelbrot ma = new Mandelbrot();
		/** X-coordinate range from -2.15 to 0.6
			Y-coordinate range from -1.3 to 1.3 */
		double x_s = -2.15, x_e = 0.6;
		double y_s = -1.3, y_e = 1.3; 
		double interval_x = (Math.abs(x_s) + Math.abs(x_e)) / 512;
		double interval_y = (Math.abs(y_s) + Math.abs(y_e)) / 512;
		double x_c = 0, y_c = 0;
		int[][] grid = new int[512][512];

		for (int x = 0; x < 512; x++) {
			x_c = x_c + interval_x;
			for (int y = 0; y < 512; y++) {
				y_c = y_c + interval_y;
				grid[x][y] = (int) ma.escapeTime(x_c, y_c, 3); // Not supposed to be 3. Not sure what to put here.
			}
			y_c = 0;
		} 
		return grid; 
	}
	
} 