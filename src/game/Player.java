package game;

import org.newdawn.slick.Image;

import utility.Angles;

public class Player {
	private double x;
	private double y;
	private double angle;
	private int width;
	private int height;
	private double speedX;
	private double speedY;
	private double acceleration;
	private final double ROTATE_SPEED = 0.3;
	private Image appearance;
	
	public Player(Image appearance) {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
		this.appearance = appearance;
		generalInitialisation();
	}
	
	public Player(double acceleration, Image appearance) {
		this.x = 0;
		this.y = 0;
		this.angle = 0;
		this.acceleration = acceleration;
		this.appearance = appearance;
		generalInitialisation();
	}
	
	public Player(int x, int y, int angle, Image appearance) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.appearance = appearance;
		generalInitialisation();
	}
	
	public Player(int x, int y, int angle, double acceleration, Image appearance) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.acceleration = acceleration;
		this.appearance = appearance;
		generalInitialisation();
	}
	
	private void generalInitialisation() {
		width = getAppearance().getWidth();
		height = getAppearance().getHeight();
		speedX = 0;
		speedY = 0;
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
	
	private double getSpeedX() {
		return speedX;
	}
	
	private double getSpeedY() {
		return speedY;
	}
	
	public double getSpeed() {
		return Math.sqrt(getSpeedX()*getSpeedX() + getSpeedY()*getSpeedY());
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
	
	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(double speedY) {
		this.speedY = speedY;
	}
	
	private void speedChangeFactor(double speedChangeFactor) {
		setSpeedX(getSpeedX() * speedChangeFactor);
		setSpeedY(getSpeedY() * speedChangeFactor);
	}
	
	public void accelerate(boolean forwards, double delta) {
		double angleRads = Math.toRadians(getAngle());
		double direction = forwards ? 1 : -1;
		
		setSpeedX(getSpeedX() + acceleration*Math.sin(angleRads)*direction
				*delta);
		setSpeedY(getSpeedY() - acceleration*Math.cos(angleRads)*direction
				*delta);
		
		//System.out.printf(String.format("Speed X: %s, Speed y: %s\n", getSpeedX(), getSpeedY()));
	}
	
	public void move() {
		setX(getX() + getSpeedX());
		setY(getY() + getSpeedY());
		speedChangeFactor(0.999);
		
		//System.out.printf(String.format("Player moved to (%s, %s)\n\n", getX(), getY()));		
	}
	
	public void rotate(boolean clockwise) {
		if(clockwise) {
			setAngle((getAngle()+ROTATE_SPEED) % Angles.DEGREES_IN_CIRCLE);
		} else {
			setAngle((getAngle()-ROTATE_SPEED) % Angles.DEGREES_IN_CIRCLE);
		}
		//System.out.printf(String.format("Angle: %s\n", getAngle()));
	}
	
	public void display() {
		getAppearance().setRotation((float)getAngle());
		getAppearance().draw((int)getX(), (int)getY());
	}
}
