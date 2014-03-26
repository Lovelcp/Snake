package snake.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Random;

import snake.tool.Global;



public class Ground{
	private int[][] rocks = new int[Global.WIDTH][Global.HEIGHT];
	
	public boolean isEatGround(Snake snake){
		//System.out.println("is eat ground");
		for(int x = 0;x<Global.WIDTH;x++){
			for(int y = 0;y<Global.HEIGHT;y++){
				if(rocks[x][y] == 1 && snake.getHead().x == x && snake.getHead().y == y)
					return true;
			}
		}
		return false;
	}
	
	public Ground(){
		for(int i = 0;i<Global.WIDTH;i++){
//			rocks[i][0] = 1;
//			rocks[i][Global.HEIGHT-1] = 1;
			rocks[i][i] = 1;
		}
	}
	
	public Point getPoint(Snake snake){
		Random random = new Random();
		int x,y;
		LinkedList<Point> body = new LinkedList<Point>();
		body = snake.getBody();
		//新的食物不能和石头和蛇身重合
		do{
			x = random.nextInt(Global.WIDTH);
			y = random.nextInt(Global.HEIGHT);
			
		}while(rocks[x][y] == 1 || isInBody(x,y,body));
		System.out.println(""+x+" "+y);
		return new Point(x,y);
	}
	
	private boolean isInBody(int x,int y,LinkedList<Point> l){
		for(Point p:l){
			if(x == p.x && y == p.y)
				return true;
		}
		return false;
	}
	
	public void drawMe(Graphics g){
		g.setColor(Color.red);
		//System.out.println("draw me");
		//为1就表示有石头
		for(int x = 0;x<Global.WIDTH;x++){
			for(int y = 0;y<Global.HEIGHT;y++){
				if(rocks[x][y] == 1)
					g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
			}
		}
		
		
	}
}
