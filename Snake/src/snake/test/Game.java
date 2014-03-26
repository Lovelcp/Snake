package snake.test;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import snake.control.Controller;
import snake.model.Food;
import snake.model.Ground;
import snake.model.Snake;
import snake.tool.Global;
import snake.view.GamePanel;

public class Game implements Global{
	private static String title = "Snake V1.0";
	private static int score = 0;
	private static JFrame frame;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Snake snake = new Snake();
		Food food = new Food();
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();
		Controller controller = new Controller(snake, food, ground, gamePanel);
		
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.setSize(Global.WIDTH*Global.CELL_SIZE,Global.HEIGHT*Global.CELL_SIZE);
		frame.setSize(Global.WIDTH*Global.CELL_SIZE+10,Global.HEIGHT*Global.CELL_SIZE+35);
		frame.add(gamePanel,BorderLayout.CENTER);
		
		gamePanel.addKeyListener(controller);
		snake.addListener(controller);
		frame.addKeyListener(controller);
		
		frame.setTitle(title+"   score:"+score);
		
		//声音部分
		AudioClip sound = loadSound("1973.wav");
		//sound.play();
		sound.loop();
		frame.setVisible(true);
		controller.newGame();
		
	}
	
	
	private static AudioClip loadSound(String fileName) {
		URL url = null;
		try {
			url = new URL("file:" + System.getProperty("user.dir") + "/"
					+ fileName);
		} catch (MalformedURLException e) {
		}
		return Applet.newAudioClip(url);
	}
	
	public static void changeTitle(int score){
		Game.score = score;
		frame.setTitle(title+"   score:"+score);		
	}
	
	public static int getScore(){
		return score;
	}
	
}
