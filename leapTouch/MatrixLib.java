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
		
		float theta = getAngle(x,y);
		float cos = (float)Math.cos(theta);
		float sin = (float)Math.sin(theta);
		float[][] translationMatrix = {
				{cos,-sin,0},	
				{sin,cos,0},
				{0,0,1}
		};
		return translationMatrix;
	}
	
	/**
	 * Get the angle between a 2D point and the vertical axis.
	 * For example if you need to get the angle between a point
	 * and the Y axis (with respect to X), axis1 should be x and
	 * axis2 should be y. If you need to get the angle between
	 * the point and the Z axis (with respect to X), axis1 should
	 * be Z and axis2 should X.
	 * @return
	 */
	public static float getAngle(float axis1, float axis2) {
		return (float) (Math.PI/2-Math.atan2(axis2,axis1));
	}
	
	public static float[][] translateToOrigin(Point p) {
		float[][] result = {
				{1,0,0,-p.x},
				{0,1,0,-p.y},
				{0,0,1,-p.z},
				{0,0,0,1}
		};
		return result;
	}
	
	public static Point getTopRightCorner(Point upperLeft, Point lowerRight) {
		return new Point(lowerRight.x,upperLeft.y,lowerRight.z);
	}

	public static float[][] zeroZaroundY(Point p) {
		float theta = getAngle(p.x,p.z);
		float cos = (float) Math.cos(theta);
		float sin = (float) Math.cos(theta);
		return null;
	}
	
	public static float[][] getXTranslationMatrix(float y,float z) {
		
		float theta = -getAngle(z,y);
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
	
	public static float dist(Point p1, Point p2) {
		return (float) Math.sqrt(
				(p1.x-p2.x)*(p1.x-p2.x)+
				(p1.y-p2.y)*(p1.y-p2.y)+
				(p1.z-p2.z)*(p1.z-p2.z)
		);
	}
	
	public static class Point {
		public float x;
		public float y;
		public float z;
		
		public Point(float x, float y, float z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
	}
}
