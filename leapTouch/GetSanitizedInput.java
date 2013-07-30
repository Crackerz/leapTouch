package leapTouch;
import com.leapmotion.leap.Vector;


public class GetSanitizedInput {

	long timeDelta;
	long jitterDelta;
	Vector restPoint;
	long start_time;
	
	public GetSanitizedInput(long timeDelta, long jitterDelta) {
		this.timeDelta = timeDelta;
		this.jitterDelta = jitterDelta;
	}
	
	public boolean checkInput(Vector v, long timestamp) {
		if(restPoint == null) {
			restPoint = v;
			start_time = timestamp;
			return false;
		}
		
		if(getDistance(v) > jitterDelta) {
			System.out.println("Hand Moved");
			restPoint = v;
			start_time = timestamp;
			return false;
		}
		
		if((timestamp-start_time) > timeDelta) return true;
		
		return false;
	}

	private float getDistance(Vector v) {
		return Math.abs(restPoint.distanceTo(v));
	}
}
