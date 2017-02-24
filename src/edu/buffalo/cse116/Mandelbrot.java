package edu.buffalo.cse116;


public class Mandelbrot {

  public double escapetime(double xCalc, double yCalc) {
	  double distance =  Math.sqrt(Math.pow(xCalc,2) + Math.pow(yCalc, 2));
	  int passes = 0;
	  while (distance <= 2 && passes < 255) {
		  xCalc = ((Math.pow(xCalc,2) - Math.pow(yCalc, 2)) + xCalc); 
		  yCalc = (2 * xCalc * yCalc) + yCalc; 
		  passes = passes + 1; 
		  distance =  Math.sqrt(Math.pow(xCalc,2) + Math.pow(yCalc, 2));
	  }
	  return passes;
}

  public double[][] createMandel() { 
	  Mandelbrot m = new Mandelbrot();
	  double[][] grid = new double[512][512];
	  
	  for (int x = 0; x < grid.length; x++) {
		  for (int y = 0; y < grid[x].length; y++) {
			  	grid[x][y] =m.escapetime(((Math.pow(x,2) - Math.pow(y, 2)) + x),(2 * x * y) + y);
			  }
		  } 
		  return grid; 
	  }
}
  