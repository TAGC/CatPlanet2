package game;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AttackPlayer extends Player {
	
	private String attackSpritePath;
	private double startX, startY, attackAngle, attackSpeed, spriteDuration;
	private boolean attackSpriteDetailsExist = false;
	private int attackRate = 0;
	private int cooldown = 0;
	
	private ArrayList<AttackSprite> attackSprites = new ArrayList<AttackSprite>();
	
	public AttackPlayer(Image appearance) {
		super(appearance);
	}
	
	public AttackPlayer(double acceleration, Image appearance) {
		super(acceleration, appearance);
	}
	
	public AttackPlayer(int x, int y, int angle, Image appearance) {
		super(x, y, angle, appearance);
	}
	
	public AttackPlayer(int x, int y, int angle, double acceleration,
			Image appearance) {
		super(x, y, angle, acceleration, appearance);
	}
	
	public AttackSprite[] getAttackSprites() {
		AttackSprite[] attackSpriteArray = new AttackSprite[]{};
		return attackSprites.toArray(attackSpriteArray);
	}
	
	public boolean attackSpriteDetailsExist() {
		return attackSpriteDetailsExist;
	}
	
	public void setAttackCharacteristics(String attackSpritePath,
			double startX, double startY, double attackAngle,
			double attackSpeed, double spriteDuration, 
			int attackRate) {
		
		this.attackSpritePath = attackSpritePath;
		this.startX = startX;
		this.startY = startY;
		this.attackAngle = attackAngle;
		this.attackSpeed = attackSpeed;
		this.spriteDuration = spriteDuration;
		this.attackRate = attackRate;
		attackSpriteDetailsExist = true;
	}
	
	public void createAttackSprite() {
		try {
			double playerAngle = Math.toRadians(getAppearance().getRotation());
			double midX = getWidth()/2;
			double midY = getHeight()/2;
			double difX = midX - startX;
			double difY = midY - startY;
			double xPos, yPos;
			System.out.println(String.format("midx: %s, midY: %s, difX: %s, difY: %s\n", midX, midY, difX, difY));
			
			xPos = midX + difY * Math.sin(playerAngle) - difX * Math.cos(playerAngle);
			yPos = midY - difY * Math.cos(playerAngle) - difX * Math.sin(playerAngle);
			
			System.out.println(String.format("xPos: %s, yPos: %s\n", xPos, yPos));
			
			xPos += getPosition()[0];
			yPos += 	getPosition()[1];
			
			AttackSprite attackSprite = new AttackSprite(attackSpritePath,
					xPos, yPos, getAppearance().getRotation() + attackAngle,
					attackSpeed, spriteDuration, 0.5);
			
			attackSprites.add(attackSprite);
			
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void moveAttackSprites(int delta) {
		for(AttackSprite attackSprite : getAttackSprites()) {
			attackSprite.move(delta);
		}
	}
	
	public void wrapAttackSpriteLocation(int xMax, int yMax) {
		double xPos, yPos;
		int width, height;
		
		for(AttackSprite attackSprite : getAttackSprites()) {
			xPos = attackSprite.getPosition()[0];
			yPos = attackSprite.getPosition()[1];
			width = attackSprite.getWidth();
			height = attackSprite.getHeight();
			
			if(xPos < -width) {
				attackSprite.setXPosition(xMax);
			} else if (xPos > xMax) {
				attackSprite.setXPosition(-width);
			}
			
			if(yPos < -height) {
				attackSprite.setYPosition(yMax);
			} else if (yPos > yMax) {
				attackSprite.setYPosition(-height);
			}
		}
	}
	
	public void displayAttackSprites() {
		for(AttackSprite attackSprite : getAttackSprites()) {
			attackSprite.display();
		}
	}
	
	public void decrementAttackSpriteDurations(int delta) {
		for(AttackSprite attackSprite : getAttackSprites()) {
			attackSprite.decrementDuration(delta);
			if(attackSprite.hasDurationExpired()) {
				attackSprites.remove(attackSprite);
			}
		}
	}
	
	public void renewCooldown() {
		if(cooldown > 0) cooldown--;
	}
	
	public void attack() {
		if(attackSpriteDetailsExist() && cooldown == 0) {
			cooldown = attackRate;
			createAttackSprite();
		}
	}
}
