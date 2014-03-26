package snake.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Set;

import javax.swing.JOptionPane;

import snake.test.Game;
import snake.tool.Global;
import snake.tool.SnakeListener;

public class Snake extends Observable implements Global {
	private Point oldTail;
	private boolean life = true;
	private static final int SPEED = 100;
	private LinkedList<Point> body = new LinkedList<Point>();
	private Set<SnakeListener> listeners = new HashSet<SnakeListener>();
	
	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	private int oldDirection;
	private int newDirection;
	/**
	 * 构造方法
	 */
	public Snake(){
		init();
	}
	
	/**
	 * 蛇身体的初始化
	 */
	private void init(){
		int x = Global.WIDTH/2;
		int y = Global.HEIGHT/2;
		for(int i = 0; i < 3;i++){
			body.add(new Point(x--,y));
		}
		this.oldDirection = this.newDirection = RIGHT;
	}
	
	public void newSnake(){
		body.clear();
		init();
		life = true;
	}
	
	public LinkedList<Point> getBody(){
		return body;
	}
	
	public void move() {
		//System.out.println("snake move");
		//去尾
		oldTail = body.removeLast();
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		
		//判断方向是否为有效方向
		if((oldDirection+newDirection)!=0){
			oldDirection = newDirection;
			//System.out.println("方向有效");
		}
		
		//加头
		switch(oldDirection){
		case UP:
			//System.out.println("当前方向为up");
			y--;
			if(y<0){
				y = Global.HEIGHT-1;
			}
			break;
		case DOWN:
			//System.out.println("当前方向为down");
			y++;
			if(y>=Global.HEIGHT){
				y = 0;
			}
			break;
		case LEFT:
			//System.out.println("当前方向为left");
			x--;
			if(x<0){
				x = Global.WIDTH-1;
			}
			break;
		case RIGHT:
			//System.out.println("当前方向为right");
			x++;
			if(x>=Global.WIDTH){
				x = 0;
			}
			break;
		}
		
		body.addFirst(new Point(x,y));
		
	}

	public void changeDirection(int direction) {
		//System.out.println("snake change");
		newDirection = direction;
	}

	public void eatFood() {
		//System.out.println("snake eat food");
		//添加上去掉的尾巴就行
		body.addLast(oldTail);
	}

	public boolean isEatSelf() {
		//System.out.println("snake eat itself");
		for(int i = 1;i<body.size();i++){
			if(body.get(i).equals(this.getHead()))
				return true;
		}
		return false;
	}
	
	public Point getHead(){
		return body.getFirst();
	}
	
	public void die(){
		life = false;
		
		
	}
	
	public void drawMe(Graphics g) {
		//System.out.println("snake draw itself");
		g.setColor(Color.CYAN);
		for(Point p : body){
			g.fill3DRect(p.x*Global.CELL_SIZE, p.y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
		}
	
	}

	/**
	 * 使用线程不停调用move方法来实现蛇的不停运动
	 */

	private class SnakeMove implements Runnable {

		@Override
		public void run() {
			while (life) {
				move();
				for (SnakeListener l : listeners) {
					l.snakeMoved(Snake.this);// 外部类名.this的用法
				}
				try {
					Thread.sleep(SPEED);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			 
			int status = JOptionPane.showConfirmDialog(null, "YOUR SCORE : "+Game.getScore()+"\r\n"+"Do you want to restart a new game?", null, JOptionPane.YES_NO_OPTION); 
			//System.out.println(status);
			Snake.this.setChanged();
			Snake.this.notifyObservers(status);
		}

	}

	/**
	 * 启动线程
	 */
	public void threadStart() {
		new Thread(new SnakeMove()).start();
	}

	/**
	 * 添加监听器的方法
	 */
	public void addListener(SnakeListener l) {
		if (l != null) {
			this.listeners.add(l);
		}
	}

}
