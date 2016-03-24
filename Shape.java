import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;


public abstract class Shape {
	
	//Declaring variables
	private int velocityX;
	private int velocityY;
	private int width = 0;
	private int height = 0;
	private int x = 0; //x position
	private int y = 0; // y position
	private Color mycolor = Color.BLUE; //default color for Shape is set to Blue.
	private Paint colorPaint;
	
	//Setters and Getters for Velocity x and y
	public int getVelocityX() {
		return velocityX;
	}
	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}
	public int getVelocityY() {
		return velocityY;
	}
	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}
	
	//Setters and Getters for Width and Height
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}

	//Setters and Getters for positions x and y
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	//Setters and Getters for the shape color
	public Color getMycolor() {
		return mycolor;
	}
	public void setMycolor(Color mycolor) {
		this.mycolor = mycolor;
		this.colorPaint = null; //set to null so that the texture/gradient could be changed.
	}
	
	//Setters and Getters for the Paint colors
	public Paint getColorPaint() {
		return colorPaint;
	}
	public void setColorPaint(Paint colorPaint) {
		this.colorPaint = colorPaint;
	}
	
	//This method determines if two shapes intersected based on a rectangular boundary
	//set for both shapes. This method returns true if there is a collision detected.
	public boolean isIntersecting(Shape myShape) {
		return getBounds().intersects(myShape.getBounds());
	}//end method isIntersecting()
	
	//Gets the rectangular bounds for the shape to compare
	private java.awt.Rectangle getBounds() {
		return new java.awt.Rectangle(x,y,width,height);
	}//end method getBounds

	//Method to draw a shape. Sets the color of the shape and a paint color.
	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(mycolor);
		g2d.setPaint(colorPaint);
	}//end method draw

}//end class Shape
