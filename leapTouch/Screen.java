package leapTouch;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;

public class Screen implements surfaceListener 
{
	static Robot rob = null;
	
	public void passRatio(float percentWidth, float percentHeight)
	{
		try{
			rob = new Robot();} 
		catch (AWTException e){
			e.printStackTrace();}
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double maxWidth = screenSize.getWidth();
		double maxHeight = screenSize.getHeight();
		System.out.println(maxWidth +", "+maxHeight);
		//rob.mouseMove((int)(maxWidth*percentWidth), (int)(maxHeight*percentHeight));
	}
	
	public static void main(String[] args) throws AWTException
	{
		Screen s = new Screen();
		s.passRatio((float) .5, (float) .5);             //it works
	}

}
