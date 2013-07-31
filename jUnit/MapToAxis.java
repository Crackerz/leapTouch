package jUnit;

import static org.junit.Assert.*;
import leapTouch.MatrixLib;

import org.junit.Test;

public class MapToAxis {
	
	@Test public void yAxis() {
		for(int i=0; i < 100; i++) {
			float z = (float)Math.random();
			float[][] p = {{(float)Math.random()},{(float)Math.random()},{z}};
			float dist = MatrixLib.dist(p[0][0], p[1][0]);
			float[][] translationMatrix = MatrixLib.getYTranslationMatrix(p[0][0], p[1][0]);
			float[][] result = MatrixLib.multiply(translationMatrix,p);
			float delta = (float)0.0001;
			assertEquals(result[0][0],0.0,delta);
			assertEquals(result[1][0],dist,delta);
			assertEquals(result[2][0],z,delta);
		}
	}
	
	@Test public void xAxis() {
		for(int i=0; i < 100; i++) {
			float[][] p = {{0},{(float)Math.random()*100},{(float)Math.random()*100}};
			//float[][] p = {{0},{1},{1}};
			float dist = MatrixLib.dist(p[0][0], p[1][0],p[2][0]);
			float[][] translationMatrix = MatrixLib.getXTranslationMatrix(p[1][0], p[2][0]);
			float[][] result = MatrixLib.multiply(translationMatrix,p);
			float delta = (float)0.0001;
			assertEquals(0,result[0][0],delta);
			assertEquals(dist,result[1][0],delta);
			assertEquals(0,result[2][0],delta);
		}
	}
	
	@Test public void combo() {
		for(int i=0; i < 100; i++) {
			float[][] p = {{(float)Math.random()*100},{(float)Math.random()*100},{(float)Math.random()*100}};
			float[][] yTranslationMatrix = MatrixLib.getYTranslationMatrix(p[0][0], p[1][0]);
			float[][] result = MatrixLib.multiply(yTranslationMatrix, p);
			float[][] xTranslationMatrix = MatrixLib.getXTranslationMatrix(result[1][0], result[2][0]);
			result = MatrixLib.multiply(xTranslationMatrix,result);
			float[][] compound = MatrixLib.multiply(xTranslationMatrix, yTranslationMatrix);
			float[][] test = MatrixLib.multiply(compound, p);
			float delta = (float)0.0001;
			assertEquals(result[0][0],test[0][0],delta);
			assertEquals(result[1][0],test[1][0],delta);
			assertEquals(result[2][0],test[2][0],delta);
		}
	}
	
	@Test public void moveToOrigin() {
		for(int i = 0; i < 100; i++) {
			float[][] point = {
					{(float)Math.random()*100,(float)Math.random()*100},
					{(float)Math.random()*100,(float)Math.random()*100},
					{(float)Math.random()*100,(float)Math.random()*100},
					{1,1}
			};
			
			float originalDistance = MatrixLib.dist(
				new MatrixLib.Point(point[0][0],point[1][0],point[2][0]),
				new MatrixLib.Point(point[0][1],point[1][1],point[2][1])
			);
			
			float[][] matrix = MatrixLib.translateToOrigin(
					new MatrixLib.Point(point[0][0], point[1][0], point[2][0]));
			
			float[][] result = MatrixLib.multiply(matrix, point);
			float delta = (float)0.00001;
			float newDistance = MatrixLib.dist(
					new MatrixLib.Point(result[0][0],result[1][0],result[2][0]),
					new MatrixLib.Point(result[0][1],result[1][1],result[2][1])
			);
			assertEquals(0,result[0][0],delta);
			assertEquals(0,result[1][0],delta);
			assertEquals(0,result[2][0],delta);
			assertEquals(1,result[3][0],delta);
			assertEquals(originalDistance,newDistance,delta);
		}
	}
	
	@Test public void zeroZaroundY() {
			float[] point1 = {(float)Math.random()*100, (float)Math.random()*100, (float)Math.random()*100, 1};
			float[] point2 = {(float)Math.random()*100, (float)Math.random()*100, (float)Math.random()*100, 1};
			float[] origin = {0,0,0,1};
			float[][] matrix = new float[4][3];
			matrix[0][0] = point1[0];
			matrix[1][0] = point1[1];
			matrix[2][0] = point1[2];
			matrix[0][1] = point2[0];
			matrix[1][1] = point2[1];
			matrix[2][1] = point2[2];
			matrix[0][2] = 0; matrix[1][2] = 0; matrix[2][2] = 0;
			matrix[3][0] = 1; matrix[3][1] = 1; matrix[3][2] = 1;
			
			float dist1 = MatrixLib.dist(new MatrixLib.Point(point1[0], point1[1], point1[2]),
					new MatrixLib.Point(origin[0], origin[1], origin[2])); // distance between point1 and origin
			float dist2 = MatrixLib.dist(new MatrixLib.Point(point2[0], point2[1], point2[2]),
					new MatrixLib.Point(origin[0], origin[1], origin[2])); // distance between point2 and origin
			float dist3 = MatrixLib.dist(new MatrixLib.Point(point1[0], point1[1], point1[2]), 
					new MatrixLib.Point(point2[0], point2[1], point2[2])); // distance between point1 and point2
			
			//insert your magic here
			
			float[][] result = matrix;
			float newdist1 = MatrixLib.dist(new MatrixLib.Point(result[0][0], result[0][1], result[0][2]), 
					new MatrixLib.Point(result[2][0], result[2][1], result[2][2]));
			float newdist2 = MatrixLib.dist(new MatrixLib.Point(result[1][0], result[1][1], result[1][2]), 
					new MatrixLib.Point(result[2][0], result[2][1], result[2][2]));
			float newdist3 = MatrixLib.dist(new MatrixLib.Point(result[0][0], result[0][1], result[0][2]), 
					new MatrixLib.Point(result[1][0], result[1][1], result[1][2]));
			float delta = (float)0.00001;
			
			assertEquals(dist1, newdist1, delta);
			assertEquals(dist2, newdist2, delta);
			assertEquals(dist3, newdist3, delta);
			
	}

}