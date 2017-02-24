package edu.buffalo.cse116;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class MultibrotTest {

	@Test
	public void test() {
		Multibrot MultiSet = new Multibrot();
		MultiSet.escapetime(0.5859375 , 0.24375000000000108);
		System.out.println(MultiSet.escapetime(0.5859375 , 0.24375000000000108));
		assertEquals(5, MultiSet.escapetime(0.5859375, 0.24375000000000108));
		
	}

}
