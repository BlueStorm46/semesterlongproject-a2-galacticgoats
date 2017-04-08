package code.fractals;

public class Multibrot {

	public int escapeTime(double xCalc, double yCalc, double escapeDistance, int escapeTime) {
		double distance = Math.sqrt(Math.pow(xCalc, 2) + Math.pow(yCalc, 2));
		int passes = 0;
		double xCord = xCalc; // Current point's x-coordinate
		double yCord = yCalc; // Current point's y-coordinate
		while (distance <= escapeDistance && passes < escapeTime) {
			/** x' = x^3 - (3 * x * y^2) + current point's x-coordinate
				y' = (3 * x^2 * y) - y^3 + current point's y-coordinate
			  		Where x and y are the values of xCalc and yCalc prior to this update and x' and y' are their values after the update. */
			double tempX = xCalc; // x
			double tempY = yCalc; // y
			xCalc = (Math.pow(tempX, 3) - (3 * tempX * Math.pow(tempY, 2))) + xCord;
			yCalc = (3 * Math.pow(tempX, 2) * tempY) - Math.pow(tempY, 3) + yCord;
			passes = passes + 1;
			distance = Math.sqrt(Math.pow(xCalc, 2) + Math.pow(yCalc, 2));
		}
		return passes;
	}

	public int[][] createMulti(double x_min, double x_max, double y_min, double y_max, double escapeDistance, int escapeTime) {
		/** Each pixel represents a real coordinate */
		double x_range = (x_max - x_min)/512;
		double y_range = (y_max - y_min)/512;
		
		/** Current coordinates the for loop is on */
		double x_current_cord = x_min;
		double y_current_cord = y_min;
		
		/** Create an empty grid */
		int[][] grid = new int[512][512];

		/** Double for loop which calculates the escape time for each pixel
		 * 
		 *  For every x-coordinate pixel, each y-coordinate pixel in that column will be calculated. */
		for (int x = 0; x < 512; x++) {
			x_current_cord = x_current_cord + x_range;
			for (int y = 0; y < 512; y++) {
				y_current_cord = y_current_cord + y_range;
				grid[x][y] = (int) escapeTime(x_current_cord, y_current_cord, escapeDistance, escapeTime);
			}
			/** Reset y-coordinate pixel */
			y_current_cord = y_min;
		} 
		return grid; 
	}
}