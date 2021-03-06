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

}
