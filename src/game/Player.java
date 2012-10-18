package game;

import org.newdawn.slick.Image;

import utility.Angles;

public class Player {
	private double x;
	private double y;
	private double angle;
	private int width;
	private int height;
	private int speed;
	private final double SPEED_DECREASE_FACTOR = 100;
	private final double ROTATE_SPEED = 0.1;
	private Image appearance;
	
	public Player(Image appearance) {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
		this.appearance = appearance;
		initialiseDimensions();
	}
	
	public Player(int speed, Image appearance) {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
		this.speed = speed;
		this.appearance = appearance;
		initialiseDimensions();
	}
	
	public Player(int x, int y, int angle, Image appearance) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.appearance = appearance;
		initialiseDimensions();
	}
	
	public Player(int x, int y, int angle, int speed, Image appearance) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		this.appearance = appearance;
		initialiseDimensions();
	}
	
	private void initialiseDimensions() {
		width = getAppearance().getWidth();
		height = getAppearance().getHeight(); 
	}
	
	public int[] getPosition() {
		int[] position = new int[]{ (int)getX(), (int)getY() };
		return position;
	}
	
	private double getX() {
		return x;
	}
	
	private double getY() {
		return y;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public Image getAppearance() {
		return appearance;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void move(boolean forwards) {
		double angleRads = Math.toRadians(getAngle());
		double trueSpeed = speed/SPEED_DECREASE_FACTOR;
		double directionMod = forwards ? 1 : -1;
		
		setX(getX() + trueSpeed*Math.sin(angleRads)*directionMod);
		setY(getY() - trueSpeed*Math.cos(angleRads)*directionMod);
		
		//System.out.printf(String.format("Player moved to (%s, %s)\n\n", getX(), getY()));
		
	}
	
	public void rotate(boolean clockwise) {
		if(clockwise) {
			setAngle((getAngle()+ROTATE_SPEED) % Angles.DEGREES_IN_CIRCLE);
		} else {
			setAngle((getAngle()-ROTATE_SPEED) % Angles.DEGREES_IN_CIRCLE);
		}
		System.out.printf(String.format("Angle: %s\n", getAngle()));
	}
	
	public void display() {
		getAppearance().setRotation((float)getAngle());
		getAppearance().draw((int)getX(), (int)getY());
	}
}
