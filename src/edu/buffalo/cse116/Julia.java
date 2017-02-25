package edu.buffalo.cse116;

public class Julia {

	public double escapetime(double xCalc, double yCalc) {
		double distance = Math.sqrt(Math.pow(xCalc, 2) + Math.pow(yCalc, 2));
		int passes = 0;
		while (distance <= 2 && passes < 255) {
			// System.out.println("hello");
			xCalc = ((Math.pow(xCalc, 2) - Math.pow(yCalc, 2)) - 0.73);
			yCalc = (2 * xCalc * yCalc) + 0.19;
			passes = passes + 1;
			distance = Math.sqrt(Math.pow(xCalc, 2) + Math.pow(yCalc, 2));
		}
		// System.out.println(passes);
		// if (distance != 0 ){
		// System.out.println("distance :" + distance);
		// }
		return passes;
	}

	public double[][] createJulia() {
		Julia j = new Julia();
		double x_s = -1.7, x_e = 1.7;
		double y_s = -1.0, y_e = 1.0;
		double interval_x = (Math.abs(x_s) + Math.abs(x_e)) / 512;
		double interval_y = (Math.abs(y_s) + Math.abs(y_e)) / 512;
		double x_c = 0, y_c = 0;
		double[][] grid = new double[512][512];

		for (int x = 0; x < 512; x++) {
			x_c = x_c + interval_x;
			for (int y = 0; y < 512; y++) {
				y_c = y_c + interval_y;
				grid[x][y] = j.escapetime(x_c, y_c);
			}
			y_c = 0;
		}
		return grid;
	}
}
