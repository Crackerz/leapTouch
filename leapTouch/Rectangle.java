package leapTouch;
import com.leapmotion.leap.Vector;


public class Rectangle {
	/*  p0 --------- p1
	 *  |            |
	 *  |            |
	 *  p2 --------- p3
	 */
	Vector[] p = new Vector[4];
	float[][] translationMatrix;
	public Rectangle(Vector upperLeft, Vector upperRight, Vector lowerLeft, Vector lowerRight) {
		p[1] = upperLeft;
		p[2] = upperRight;
		p[3] = lowerLeft;
		p[4] = lowerRight;
	}
	
	public float[][] getMarix() {
		float[][] result = {
			{p[0].getX(),p[1].getX(),p[2].getX(),p[3].getX()},
			{p[0].getY(),p[1].getY(),p[2].getY(),p[3].getY()},
			{p[0].getZ(),p[1].getZ(),p[2].getZ(),p[3].getZ()},
		};
		return result;
	}
	
	public void setToMatrix(float[][] matrix) {
		p[0].setX(matrix[0][0]);
		p[0].setY(matrix[0][1]);
		p[0].setZ(matrix[0][2]);
		p[1].setX(matrix[1][0]);
		p[1].setY(matrix[1][1]);
		p[1].setZ(matrix[1][2]);
		p[2].setX(matrix[2][0]);
		p[2].setY(matrix[2][1]);
		p[2].setZ(matrix[2][2]);
		p[3].setX(matrix[3][0]);
		p[3].setY(matrix[3][1]);
		p[3].setZ(matrix[3][2]);
	}
	
	public float[][] mapToOrigin() {
		float[] tVector = {-p[2].getX(),-p[2].getY(),-p[2].getZ()};
		float[][] translationMatrix = {tVector,tVector,tVector,tVector};
		this.setToMatrix(MatrixLib.add(this.getMarix(),translationMatrix));
		return translationMatrix;
	}

	public float[][] snapToY() {
		float[][] translationMatrix = MatrixLib.getYTranslationMatrix(p[0].getX(), p[0].getY());
		this.setToMatrix(MatrixLib.multiply(translationMatrix,this.getMarix()));
		return translationMatrix;
	}
	
	public float[][] snapToX() {
		return null;
	}
	
	public float[][] flatten() {
		float[][] result = mapToOrigin();
		result = MatrixLib.multiply(snapToY(), result);
		return result;
	}
}
