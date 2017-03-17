package Model;

public class Multibrot {

	public int escapeTime(double xCalc, double yCalc) {
		double distance = Math.sqrt(Math.pow(xCalc, 2) + Math.pow(yCalc, 2));
		int passes = 0;
		double xCord = xCalc; // Current point's x-coordinate
		double yCord = yCalc; // Current point's y-coordinate
		while (distance <= 2 && passes < 255) {
			/** x' = x³ - (3 * x * y²) + current point's x-coordinate
				y' = (3 * x² * y) - y³ + current point's y-coordinate
			  		Where x and y are the values of xCalc and yCalc prior to this update and x' and y' are their values after the update. */
			double tempX = xCalc; // x
			double tempY = yCalc; // y
			xCalc = (Math.pow(tempX, 3) - (3 * tempX * Math.pow(yCalc, 2))) + xCord;
			yCalc = (3 * Math.pow(tempY, 2) * tempY) - Math.pow(xCalc, 3) + yCord;
			passes = passes + 1;
			distance = Math.sqrt(Math.pow(xCalc, 2) + Math.pow(yCalc, 2));
		}
		return passes;
	}

	public int[][] createMulti() {
		Multibrot mu = new Multibrot();
		/** X-coordinate range from -1 to 1
			Y-coordinate range from -1.3 to 1.3 */
		double x_s = -1, x_e = 1;
		double y_s = -1.3, y_e = 1.3;
		double interval_x = (Math.abs(x_s) + Math.abs(x_e)) / 512;
		double interval_y = (Math.abs(y_s) + Math.abs(y_e)) / 512;
		double x_c = 0, y_c = 0;
		int[][] grid = new int[512][512];

		for (int x = 0; x < 512; x++) {
			x_c = x_c + interval_x;
			for (int y = 0; y < 512; y++) {
				y_c = y_c + interval_y;
				grid[x][y] = mu.escapeTime(x_c, y_c);
			}
			y_c = 0;
		}
		return grid;
	}
}