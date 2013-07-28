package leapTouch;


public class MatrixLib {
	public static float[][] add(float[][] A, float[][] B) {
		float[][] result = new float[A.length][];
		for(int i = 0; i < A.length; i++) {
			result[i] = new float[A[i].length];
			for(int j = 0; j < A[i].length; j++) {
				result[i][j] = A[i][j]+B[i][j];
			}
		}
		return result;
	}
	
	public static float[][] multiply(float[][] A, float[][] B) {
		int size = B.length;
		float[][] result = new float[A.length][B[0].length];
		for(int i = 0; i < result.length; i++) {
			for(int j = 0; j < result[i].length; j++) {
				for(int k = 0; k < size; k++)
				result[i][j] += A[i][k]*B[k][j];
			}
		}
		return result;
	}
	
	public static float[][] getYTranslationMatrix(float x,float y) {
		
		float theta = (float) (Math.PI/2-Math.atan2(y,x));
		float cos = (float)Math.cos(theta);
		float sin = (float)Math.sin(theta);
		float[][] translationMatrix = {
				{cos,-sin,0},	
				{sin,cos,0},
				{0,0,1}
		};
		return translationMatrix;
	}
	
	public static float[][] getXTranslationMatrix(float y,float z) {
		
		float theta = (float) -(Math.PI/2-Math.atan2(y,z));
		float cos = (float)Math.cos(theta);
		float sin = (float)Math.sin(theta);
		float[][] translationMatrix = {
				{1,0,0},	
				{0,cos,-sin},
				{0,sin,cos}
		};
		return translationMatrix;
	}
	
	public static float dist(float x, float y) {
		return (float) Math.sqrt(x*x+y*y);
	}
	
	public static float dist(float x, float y, float z) {
		return (float) Math.sqrt(x*x+y*y+z*z);
	}
}
