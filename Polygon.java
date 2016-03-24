import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;


public class Polygon extends Shape{
	
	//Method inherited from superclass Shape
	//Draws the polygon start
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		Graphics2D g2d = (Graphics2D) g;

		//Sets the positions on where to draw the star x & y coordinates
		int xPoints[] = { 55, 67, 109, 73, 83, 55, 27, 37, 1, 43};
		int yPoints[] = { 0, 36, 36, 54, 96, 72, 96, 54, 36, 36};
		
		//Sets the size of the star dependent on panel size.
		GeneralPath star = new GeneralPath();
		star.moveTo( (xPoints[0]/109d) * getWidth(), (yPoints[0]/96d) * getHeight());
	    
		for (int count = 1; count < xPoints.length; count++)
			star.lineTo((xPoints[count]/109d) * getWidth(), (yPoints[count]/96d) * getHeight());
		    
		star.closePath();
		g2d.translate(getX(),getY()); //move the star
		        
	    g2d.fill(star); //color in the star
		
	}//end method draw

}//end class Polygon
