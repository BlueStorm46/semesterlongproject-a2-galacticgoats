package edu.buffalo.cse116;

public class Multibrot {
	public static void main (String[] args){
		Multibrot mu = new Multibrot();
		mu.createMulti();
	}

	  public int escapetime(double xCalc, double yCalc) {
		  double distance =  Math.sqrt(Math.pow(xCalc,2) + Math.pow(yCalc, 2));
		  int passes = 0;
		  while (distance <= 2 && passes < 255) {
			  xCalc = (Math.pow(xCalc,3) - (3 * xCalc * Math.pow(yCalc, 2))) + xCalc; 
			  yCalc = (3 * Math.pow(xCalc,2) * yCalc)- Math.pow(xCalc,3) + yCalc; 
			  passes = passes + 1; 
			  distance =  Math.sqrt(Math.pow(xCalc,2) + Math.pow(yCalc, 2));
		  }
		  return passes; 
		  }

	  public double[][] createMulti() {
		  Multibrot mu = new Multibrot();
		  double x_s = -1, x_e = 1;
		  double y_s = -1.3, y_e = 1.3;
		  double interval_x = (Math.abs(x_s) + Math.abs(x_e))/512;
		  double interval_y = (Math.abs(y_s) + Math.abs(y_e))/512;
		  double x_c = 0, y_c = 0; 
		  double[][] grid = new double[512][512];
		  
		  for (int x = 0; x < 512; x++) {
			  x_c = x_c + interval_x;
			  for (int y = 0; y < 512; y++) {
				  	y_c = y_c + interval_y;
				  	grid[x][y] = mu.escapetime(x_c, y_c);
				  }
			  	y_c = 0;
			  }
		  System.out.println(grid[512][0]);
			  return grid; 
		  }
	}