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
	private Player player;
	private Image land;
	
	public TestGame() {
		super("TestGame");
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		
		land.draw(0, 0);
		player.display();
		
		g.drawString("Hello,  Slick world!", 0, 100);
		
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		player = new Player(50, 50, Angles.UP.value(), 0.001,
				new Image("src/player.png"));
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
	}
	
	public void handleInput(Input input, double delta) {
		
		if(input.isKeyDown(Input.KEY_D)) {
			player.rotate(true);
		} else if (input.isKeyDown(Input.KEY_A)) {
			player.rotate(false);
		}
		
		if (input.isKeyDown(Input.KEY_W)) {
			player.accelerate(true, delta);
		} else if (input.isKeyDown(Input.KEY_S)) {
			player.accelerate(false, delta);
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
