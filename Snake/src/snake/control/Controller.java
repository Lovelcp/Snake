package snake.control;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import snake.model.Food;
import snake.model.Ground;
import snake.model.Snake;
import snake.test.Game;
import snake.tool.SnakeListener;
import snake.view.GamePanel;

public class Controller extends KeyAdapter implements SnakeListener,Observer{
	private final int EAT = 0;
	private final int DEAD = 1;
	public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
		super();
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
		snake.addObserver(this);
	}

	private Snake snake;
	private Food food;
	private Ground ground;
	private GamePanel gamePanel;
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			snake.changeDirection(snake.UP);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(snake.LEFT);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(snake.DOWN);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(snake.RIGHT);
			break;
		}
	}

	@Override
	public void snakeMoved(Snake snake) {
		//如果蛇吃到了食物
		if(food.isEatFood(snake)){
			snake.eatFood();
			food.newFood(ground.getPoint(snake));
			food.addNumberOfFood();
			playSound(EAT);		
			Game.changeTitle(food.getNumberOfFood()*food.getValueOfFood());
		}
		if(ground.isEatGround(snake)){
			snake.die();			
			playSound(DEAD);
		}
		if(snake.isEatSelf()){
			snake.die();
			playSound(DEAD);
		}
		gamePanel.display(snake, food, ground);		
	}

	
	
	public void newGame(){
		snake.threadStart();
		food.newFood(ground.getPoint(snake));
	}

	public void continueNewGame(){
		snake.newSnake();
		snake.threadStart();
		food.setNumberOfFood(0);
		Game.changeTitle(0);
		food.newFood(ground.getPoint(snake));	
	}
	
	private AudioClip loadSound(String fileName) {
		URL url = null;
		try {
			url = new URL("file:" + System.getProperty("user.dir") + "/"
					+ fileName);
		} catch (MalformedURLException e) {
		}
		return Applet.newAudioClip(url);
	}
	
	private void playSound(int status){
		AudioClip sound;
		switch(status){
			case EAT:sound = loadSound("eat.wav");sound.play();break;
			case DEAD:sound = loadSound("killed.wav");sound.play();break;
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg.toString().equals("0"))
			continueNewGame();
	}
	
}
