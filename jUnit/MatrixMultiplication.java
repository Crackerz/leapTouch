package jUnit;

import static org.junit.Assert.*;
import leapTouch.MatrixLib;

import org.junit.Test;

public class MatrixMultiplication {
	
	@Test public void identity() {
		float[][] A = {
				{1,2,3,10},
				{4,5,6,11},
				{7,8,9,12}
		};
		
		float[][] B = {
				{1,0,0,0},
				{0,1,0,0},
				{0,0,1,0},
				{0,0,0,1}
		};
		
		float[][] result = MatrixLib.multiply(A, B);
		assertNotNull(result);
		for(int i=0;i<result.length;i++) {
			assertArrayEquals(A[i], result[i],(float)0.01);
		}
	}

}
