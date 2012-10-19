package game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utility.Angles;

public class TestGame extends BasicGame {
	
	private final static int WINDOW_HEIGHT = 480;
	private final static int WINDOW_WIDTH = 640;
	private AttackPlayer player;
	private Image land;
	
	public TestGame() {
		super("TestGame");
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		
		land.draw(0, 0);
		player.display();
		
		if(player instanceof AttackPlayer) {
			player.renewCooldown();
			if(player.attackSpriteDetailsExist()) {
				player.displayAttackSprites();
			}
		}
		
		g.drawString("Hello,  Slick world!", 0, 100);
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		player = new AttackPlayer(50, 50, Angles.UP.value(), 0.001,
				new Image("src/player.png"));
		player.setAttackCharacteristics("src/cannon_ball.png",
				player.getWidth()/2, 0, 0, 1, 1000, 200);
		land = new Image("src/worldpic.jpg" );
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		
		int playerX, playerY, playerWidth, playerHeight;
		handleInput(container.getInput(), delta);
		
		playerX = player.getPosition()[0];
		playerY = player.getPosition()[1];
		playerWidth = player.getWidth();
		playerHeight = player.getHeight();
		
		if(playerX < -playerWidth) {
			player.setX(WINDOW_WIDTH);
		} else if (playerX > WINDOW_WIDTH) {
			player.setX(-playerWidth);
		}
		
		if(playerY < -playerHeight) {
			player.setY(WINDOW_HEIGHT);
		} else if (playerY > WINDOW_HEIGHT) {
			player.setY(-playerHeight);
		}
		
		player.move();
		
		if(player instanceof AttackPlayer) {
			player.renewCooldown();
			if(player.attackSpriteDetailsExist()) {
				player.decrementAttackSpriteDurations();
				player.moveAttackSprites();
				player.wrapAttackSpriteLocation(WINDOW_WIDTH, WINDOW_HEIGHT);
				player.displayAttackSprites();
			}
		}
	}
	
	public void handleInput(Input input, double delta) {
		
		if(input.isKeyDown(Input.KEY_D)) {
			player.rotate(true);
		} else if (input.isKeyDown(Input.KEY_A)) {
			player.rotate(false);
		}
		
		if(input.isKeyDown(Input.KEY_W)) {
			player.accelerate(true, delta);
		} else if (input.isKeyDown(Input.KEY_S)) {
			player.accelerate(false, delta);
		}
		
		if(input.isKeyDown(Input.KEY_SPACE)) {
			if(player instanceof AttackPlayer) {
				player.attack();
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new TestGame(),
					WINDOW_WIDTH, WINDOW_HEIGHT, false);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}
