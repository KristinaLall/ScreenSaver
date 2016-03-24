
import java.awt.Graphics;


public class Rectangle extends Shape{ //aggregation

	
	//Method inherited from superclass Shape
	//Draws the rectangle
	@Override
	public void draw(Graphics g) {
		super.draw(g);
		
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		
	}//end method draw

}//end class Rectangle
