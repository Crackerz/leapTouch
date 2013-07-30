package leapTouch;
import com.leapmotion.leap.Vector;


public class VectorAverage {
	Vector vectorArray[];
	int currSize;
	int index;

	public VectorAverage(int size) {
		vectorArray = new Vector[size];
		currSize = 0;
		index = 0;
	}
	
	public void add(Vector v) {
		vectorArray[index++]=v;
		if(currSize < vectorArray.length-1)	currSize = index;
		index%=vectorArray.length;
	}
	
	public Vector getAverage() {
		float x = 0;
		float y = 0;
		float z = 0;
		for(int i = 0; i < currSize; i++) {
			x+=vectorArray[i].getX();
			y+=vectorArray[i].getY();
			z+=vectorArray[i].getZ();
		}
		return new Vector(x/currSize,y/currSize,z/currSize);
	}
}
