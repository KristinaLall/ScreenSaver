import java.awt.Graphics;

public class Arc extends Shape{
	
	//Method inherited from superclass Shape
	//Draws the arc
	@Override
	public void draw(Graphics g) {

		super.draw(g);
		g.fillArc(getX(), getY(), getWidth(), getHeight(), 0, 180); //colors in the arc
		
	}//end method draw

}//end class Arc