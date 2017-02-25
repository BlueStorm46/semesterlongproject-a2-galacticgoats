package edu.buffalo.cse116;


public class Mandelbrot {

	public double escapetime(double xCalc, double yCalc) {
		double distance =  Math.sqrt(Math.pow(xCalc,2) + Math.pow(yCalc, 2));
		int passes = 0;
		while (distance <= 2 && passes < 255) {
			//System.out.println("hello");
			xCalc = ((Math.pow(xCalc,2) - Math.pow(yCalc, 2)) + xCalc); 
			yCalc = (2  * xCalc * yCalc) + yCalc; 
			passes = passes + 1; 
			distance =  Math.sqrt(Math.pow(xCalc,2) + Math.pow(yCalc, 2));
		}
		//System.out.println(passes);
//		if (distance != 0 ){
//			System.out.println("distance :" + distance);
//		}
		return passes;
	}

	public double[][] createMandel() { 
		Mandelbrot m = new Mandelbrot();
		double x_s = -2.15,x_e = 0.6;
		double y_s = -1.3,y_e = 1.3; 
		double interval_x = (Math.abs(x_s)+Math.abs(x_e))/512;
		double interval_y = (Math.abs(y_s)+Math.abs(y_e))/512;
		double x_c = 0, y_c=0;
		double[][] grid = new double[512][512];

		for (int x = 0; x < 512; x++) {
			x_c = x_c + interval_x;
			for (int y = 0; y < 512; y++) {
				y_c = y_c+interval_y;
				grid[x][y] = m.escapetime(x_c, y_c);
			}
			y_c = 0;
		} 
		return grid; 
	}
} 

