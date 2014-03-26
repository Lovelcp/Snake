package snake.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import snake.model.Food;
import snake.model.Ground;
import snake.model.Snake;
import snake.tool.Global;

public class GamePanel extends JPanel{

	private Snake snake;
	private Food food;
	private Ground ground;
	
	public void display(Snake snake,Food food,Ground ground){
		//System.out.println("game display");
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//重新显示
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0, Global.CELL_SIZE*Global.WIDTH, Global.CELL_SIZE*Global.HEIGHT);
		if(snake!=null&&food!=null&&ground!=null){
			this.snake.drawMe(g);
			this.food.drawMe(g);
			this.ground.drawMe(g);
		}
		
	}

	
	
}
