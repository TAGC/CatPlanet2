package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AttackSprite extends Image {
	
	private double duration, posX, posY, angle, speed, scale;
	private int width, height;
	
	public AttackSprite(String filepath, double posX, double posY,
			double angle, double speed, double duration, double scale
			)throws SlickException {
		super(filepath);
		this.posX = posX;
		this.posY = posY;
		this.angle = angle;
		this.speed = speed;
		this.duration = duration;
		this.scale = scale;
		width = getWidth();
		height = getHeight();
	}
	
	public double[] getPosition() {
		double[] position = new double[] { posX, posY };
		return position;
	}
	
	public void setXPosition(double xPosition) {
		posX = xPosition;
	}
	
	public void setYPosition(double yPosition) {
		posY = yPosition;
	}
	
	public boolean hasDurationExpired() {
		return duration <= 0;
	}
	
	public void decrementDuration() {
		if(!hasDurationExpired()) {
			duration--;
		}
	}
	
	public void move() {
		double angleRads = Math.toRadians(angle);
		posX += speed*Math.sin(angleRads);
		posY -= speed*Math.cos(angleRads);
	}
	
	public void display() {
		Image copy = this.getScaledCopy((int)(width*scale), (int)(height*scale));
		copy.setRotation((float)angle);
		copy.draw((float)(posX-0.5*width*scale), (float)(posY-0.5*height*scale));
	}
}
