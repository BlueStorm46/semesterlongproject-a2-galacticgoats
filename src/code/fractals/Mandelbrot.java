package code.fractals;

public class Mandelbrot {

	public double escapeTime(double xCalc, double yCalc, double escapeDistance, int escapeTime) {
		double distance = Math.sqrt(Math.pow(xCalc, 2) + Math.pow(yCalc, 2));
		int passes = 0;
		double xCord = xCalc; // Current point's x-coordinate
		double yCord = yCalc; // Current point's y-coordinate
		while (distance <= escapeDistance && passes < escapeTime) {
			/** x' = x^2 - y^2 + current point's x-coordinate
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

	public int[][] createMandel(double x_min, double x_max, double y_min, double y_max, double escapeDistance, int escapeTime, int threads) { 
		/** Each pixel represents a real coordinate */
		double x_range = (x_max - x_min)/2048;
		double y_range = (y_max - y_min)/2048;
		
		/** Current coordinates the for loop is on */
		double x_current_cord = x_min;
		double y_current_cord = y_min;
		
		/** Create an empty grid */
		int[][] grid = new int[2048][2048];

		/** Double for loop which calculates the escape time for each pixel
		 * 
		 *  For every x-coordinate pixel, each y-coordinate pixel in that column will be calculated. */
		for (int x = 0; x < 2048; x++) {
			x_current_cord = x_current_cord + x_range;
			for (int y = 0; y < 2048; y++) {
				y_current_cord = y_current_cord + y_range;
				grid[x][y] = (int) escapeTime(x_current_cord, y_current_cord, escapeDistance, escapeTime);
			}
			/** Reset y-coordinate pixel */
			y_current_cord = y_min;
		} 
		return grid; 
	}	
} 