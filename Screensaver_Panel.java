import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Screensaver_Panel extends JPanel {

	//Declaring Constants
	private static final int ANIMATION_DELAY = 40;
	private static final int MOVE_INCREMENT = 20;
	
	//Declaring variables
	private Timer animationTimer = new Timer(ANIMATION_DELAY,new TimerHandler());
	private Boolean counterMove = false; //in order to determine if it is the first time moving.
	
	private ArrayList<Shape> myShapes = new ArrayList<>(); //an array to hold all of the shapes created.
	
	private Color colorArray[] = {Color.RED,Color.BLUE,Color.GREEN,Color.YELLOW,Color.BLACK,Color.MAGENTA};

	private Random randomNumber = new Random();
	
	//Constructor method
	public Screensaver_Panel() {

		myShapes.add((Shape) new Oval()); //or can just simply write myShapes.add(new Oval());
		myShapes.add((Shape) new Rectangle()); //or can just simply write myShapes.add(new Rectangle());
		myShapes.add((Shape) new Arc());//or can just simply write myShapes.add(new Arc());
		myShapes.add((Shape) new Polygon());//or can just simply write myShapes.add(new Polygon));

		animationTimer.start(); //Starting the time
		
		//This action listener will be executed every time 
		//that the mouse is clicked.
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {	}

			@Override
			public void mouseEntered(MouseEvent e) {}
			
			//To be executed upon mouse click
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int random = randomNumber.nextInt(3);
				Shape clickedShape;
				
				if (random == 0) {
					 clickedShape = (Shape) new Oval();//or can just simply write clickedShape = new Oval()
				}
				else if (random == 1) {
					clickedShape = (Shape) new Rectangle();//or can just simply write clickedShape = new Rectangle()
				}
				else {
					clickedShape = (Shape) new Arc();//or can just simply write clickedShape = new Arc()
				}
				initializeShape(clickedShape);
				
				myShapes.add(0, clickedShape);
			}
		}); //end mouseClicked action listener
		
	}//end constructor

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.WHITE);

		for (Shape eachShape: myShapes) {
			eachShape.draw(g);
		}//end for each loop
	}//end paintComponent

	//This method is called every time the timer has "ticked".
	private class TimerHandler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			
			for (Shape eachShape: myShapes) {
				resizeShape(eachShape); //Setting the initial values of the shape
				if (counterMove == false ) {
					initializeShape(eachShape); //Initializes the shape depending on which shape
				}//end if
				else {
					updateShapePosition(eachShape); //updates the position/color/size of the shape, depending on the shape
				}//end else
			}//end for loop
			
				repaint();//calls paintComponent
				counterMove = true; //this counter is set to know if it was at the start of the program.
		}//end actionPerformed	
	}//end inner class TimerHandler
	
	//This method sets the width and height to 1/10 of the panel size.
	public void resizeShape(Shape eachShape) {
		eachShape.setWidth(getWidth()/10);
		eachShape.setHeight(getHeight()/10);
	}//end method resizeShape
	
	//This method initializes the shape x, y, and velocity coordinates for the shape
	//Does not return a value.
	public void initializeShape(Shape eachShape) {
		
		//sets position
		eachShape.setX(1+randomNumber.nextInt(getWidth()-eachShape.getWidth()));
		eachShape.setY(1+randomNumber.nextInt(getHeight()-eachShape.getHeight()));
		
		//sets velocity
		eachShape.setVelocityX(1+randomNumber.nextInt(MOVE_INCREMENT));
		eachShape.setVelocityY(1+randomNumber.nextInt(MOVE_INCREMENT));
	}//end method initializeShape
	
	//This method updates the shape after each Timer "tick".
	//Does not return a value
	public void updateShapePosition(Shape eachShape) {
		
		//sets position
		eachShape.setX(eachShape.getX() + eachShape.getVelocityX());
		eachShape.setY(eachShape.getY() + eachShape.getVelocityY());
		
		
		//creating an array to store the objects that have collision
		ArrayList<Shape> collisionArray = new ArrayList<>();
		
		
		//This loop checks for collision detection
		for (Shape checkShape: myShapes) {
		
			//Check if there is collision detection on shapes other than itself
			if (!eachShape.equals(checkShape)&&(eachShape.isIntersecting(checkShape))) {	
			
				collisionArray.add(checkShape);
				
			}//end if
			
		}//end for
		
		//if there are shapes in the array of collisions, then find the average (like a resultant vector) for the collisions.
		if (!collisionArray.isEmpty()) {
				double totalX =0; //total x positions
				double totalY = 0; //total y positions
				for (Shape checkShape: collisionArray)
				{
					totalX += checkShape.getX() + (checkShape.getWidth()/2d);
					totalY += (checkShape.getY() + (checkShape.getHeight()/2d));
					
				}
				//finding the averages
			 	double avgX = totalX/collisionArray.size();
			 	double avgY = totalY/collisionArray.size();
			 	
				double speed = Math.sqrt((eachShape.getVelocityX() * eachShape.getVelocityX()) + (eachShape.getVelocityY()*eachShape.getVelocityY()));
				
				double deltaX= avgX -(eachShape.getX() + (eachShape.getWidth()/2d)) ;
				double deltaY= avgY -(eachShape.getY() + (eachShape.getHeight()/2d));
				
				//angle between the x and y
				double angle = Math.atan2(deltaX, deltaY);
				
				double newMagnitudeY = Math.sin(angle) * speed;
				double newMagnitudeX = Math.cos(angle) * speed;
				
				eachShape.setVelocityX((int) Math.round(newMagnitudeX));
				eachShape.setVelocityY((int) Math.round(newMagnitudeY));
		}//end if
			
			
			
		//Checks Right bounds on x axis.
		if (eachShape.getX() > getWidth()-eachShape.getWidth()){
			eachShape.setX(getWidth()-eachShape.getWidth());
			eachShape.setVelocityX(-Math.abs(eachShape.getVelocityX()));
			updateColor(eachShape);
		}
		//Checks Left bounds on y axis.
		else if (eachShape.getX()<0){
			eachShape.setX(0);
			eachShape.setVelocityX(Math.abs(eachShape.getVelocityX()));
			updateColor(eachShape);
		}
		//Checks Bottom bounds on y axis.
		if (eachShape.getY() > getHeight()-eachShape.getHeight()){
			eachShape.setY(getHeight()-eachShape.getHeight());
			eachShape.setVelocityY(-Math.abs(eachShape.getVelocityY()));
			updateColor(eachShape);
		}
		//Checks Top bounds on y axis.
		else if (eachShape.getY() <0){
			eachShape.setY(0);
			eachShape.setVelocityY(Math.abs(eachShape.getVelocityY()));
			updateColor(eachShape);
		}
	}//end updateShapePosition
	
	//This method updates the shape color for each shape that is created.
	public void updateColor(Shape eachShape) {
		
		int pickColor = randomNumber.nextInt(colorArray.length +2);
		
		//setting the colors to uniform colors
		if (pickColor < colorArray.length) {
			eachShape.setMycolor(colorArray[pickColor]);
		}
		//setting some of the random numbers to Texture
		else if (pickColor == colorArray.length){
			
			BufferedImage buffImage = new BufferedImage(10,10,BufferedImage.TYPE_INT_RGB);
			Graphics2D gg = buffImage.createGraphics();		
			gg.setColor(Color.CYAN);
			gg.fillRect(0,0,10,10);
			gg.setColor(Color.BLACK);
			gg.drawRect(1, 1, 6, 6);
			gg.setColor(Color.BLUE);
			gg.fillRect(1, 1, 6, 6);
			gg.setColor(Color.RED);
			gg.fillRect(4, 4, 3, 3);
			
			eachShape.setColorPaint(new TexturePaint(buffImage,new java.awt.Rectangle(10,10)));
		}
		//setting some of the random numbers to gradient
		else {
			eachShape.setColorPaint(new GradientPaint(5,30,Color.BLUE,35,100,Color.YELLOW,true));
		}
	}//end method updateColor
	
}//end class Screensaver_Panel
