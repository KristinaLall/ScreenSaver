import java.awt.Graphics;


public class Oval extends Shape //subclass for Shape
{
	
	//method draw inherited from Shape class.
	@Override
	public void draw(Graphics g){	
		super.draw(g);
		
		g.fillOval(getX(), getY(), getWidth(), getHeight()); //colors in the Oval shape
	}//end method draw
	
}//end class Oval
