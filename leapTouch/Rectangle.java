package leapTouch;
import leapTouch.MatrixLib.Point;

import com.leapmotion.leap.Vector;


public class Rectangle {
	/*	Before Constructor
	 *  p0 = upperLeft
	 *	p1 = lowerRight
	 *	p3 = upperRight
	 *	p4 = in front of tv
	 *
	 *	After Constructor
	 *	p0 = upperLeft
	 *	p1 = upperRight
	 *	p2 = lowerLeft
	 *	p3 = lowerRight
	 *	nontouch = point in front of TV
	 */
	Vector[] p = new Vector[4];
	Vector nontouch;
	float[][] translationMatrix;
	public Rectangle(Vector upperLeft, Vector lowerRight, Vector upperRight, Vector inFront) {
		p[0] = upperLeft;
		p[1] = lowerRight;
		p[2] = upperRight;
		p[3] = inFront;
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
	
	public float[][] flatten() {
		float[][] points = this.getMarix();
		float[][] toOrigin = MatrixLib.translateToOrigin(new MatrixLib.Point(points[0][0],points[1][0],points[2][0]));		
		points = MatrixLib.multiply(toOrigin, points);
		MatrixLib.Point upperRight = MatrixLib.getTopRightCorner(
				new MatrixLib.Point(points[0][0],points[1][0],points[2][0]),
				new MatrixLib.Point(points[0][1],points[1][1],points[2][1]));
		float[][] flattenDiag = MatrixLib.zeroZaroundY(upperRight);
		points = MatrixLib.multiply(flattenDiag, points);
		float[][] flattenUpperRight = MatrixLib.zeroZaroundPoint(
				new MatrixLib.Point(points[0][2],points[1][2],points[2][2]),
				new MatrixLib.Point(points[0][1],points[1][1],points[2][1]));
		points = MatrixLib.multiply(flattenUpperRight, points);
		float[][] newPoints = {
				{points[0][0],points[0][1],points[0][0],points[0][1]},
				{points[1][0],points[1][0],points[1][1],points[1][1]},
				{points[2][0],0,0,points[2][1]}
		};
		float[][] newOrigin = MatrixLib.translateToOrigin(
				new MatrixLib.Point(newPoints[0][2],newPoints[1][2],newPoints[2][2]));
		newPoints = MatrixLib.multiply(newPoints, newPoints);
		nontouch = new Vector(points[0][3],points[1][3],points[2][3]);
		float[][] result = MatrixLib.multiply(newOrigin, flattenUpperRight);
		result = MatrixLib.multiply(flattenDiag, result);
		result = MatrixLib.multiply(toOrigin, result);
		this.setToMatrix(newPoints);
		return points;
	}
}
