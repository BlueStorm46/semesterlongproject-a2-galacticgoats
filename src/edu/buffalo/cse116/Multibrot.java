package edu.buffalo.cse116;

public class Multibrot {

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
		  
		  double[][] grid = new double[512][512];
		  
		  for (int x = 0; x < grid.length; x++) {
			  for (int y = 0; y < grid[x].length; y++) {
				  	grid[x][y] = escapetime((-1 + 1)/512 , (-1.3 + 1.3)/512);
				  }	
			  }
			  return grid; 
		  }
	}