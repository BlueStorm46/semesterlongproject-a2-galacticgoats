package model;

public class BurningShip {

	public double escapeTime(double xCalc, double yCalc, double escapeDistance) {
		double distance = Math.sqrt(Math.pow(xCalc, 2) + Math.pow(yCalc, 2));
		int passes = 0;
		double xCord = xCalc; // Current point's x-coordinate
		double yCord = yCalc; // Current point's y-coordinate
		while (distance <= escapeDistance && passes < 255) {
			/** x' = x² - y² + current point's x-coordinate
				y' = Math.abs(2 * x * y) + current point's y-coordinate
			   		Where x and y are the values of xCalc and yCalc prior to this update and x' and y' are their values after the update. */
			double tempX = xCalc; // x
			double tempY = yCalc; // y
			xCalc = ((Math.pow(tempX, 2) - Math.pow(tempY, 2)) + xCord);
			yCalc = Math.abs(2 * tempX * tempY) + yCord;
			passes = passes + 1;
			distance = Math.sqrt(Math.pow(xCalc, 2) + Math.pow(yCord, 2));
		}
		return passes;
	}

	public int[][] createBS(double escapeDistance) {
		/** X-coordinate range from -1.8 to -1.7
			Y-coordinate range from -0.08 to 0.025 */
		double x_min = -1.8, x_max = -1.7;
		double y_min = -0.08, y_max = 0.025;

		/** Each pixel represents a real coordinate */
		double x_range = (x_max - x_min)/512;
		double y_range = (y_max - y_min)/512;
		
		/** Current coordinates the for loop is on */
		double x_current_cord = 0;
		double y_current_cord = 0;
		
		/** Create an empty grid */
		int[][] grid = new int[512][512];

		/** Double for loop which calculates the escape time for each pixel
		 * 
		 *  For every x-coordinate pixel, each y-coordinate pixel in that column will be calculated. */
		for (int x = 0; x < 512; x++) {
			x_current_cord = x_current_cord + x_range;
			for (int y = 0; y < 512; y++) {
				y_current_cord = y_current_cord + y_range;
				grid[x][y] = (int) escapeTime(x_current_cord, y_current_cord, escapeDistance);
			}
			/** Reset y-coordinate pixel */
			y_current_cord = 0;
		} 
		return grid; 
	}
}