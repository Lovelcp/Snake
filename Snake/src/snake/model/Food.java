package snake.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import snake.tool.Global;

public class Food extends Point{
	protected int numberOfFood;
	protected int valueOfFood;
	
	public Food(){
		numberOfFood = 0;
		valueOfFood = 1;
	}
	
	public int getNumberOfFood() {
		return numberOfFood;
	}

	public void setNumberOfFood(int numberOfFood) {
		this.numberOfFood = numberOfFood;
	}

	public int getValueOfFood() {
		return valueOfFood;
	}

	public void setValueOfFood(int valueOfFood) {
		this.valueOfFood = valueOfFood;
	}
	
	public void addNumberOfFood(){
		numberOfFood++;
	}
	
	//判断蛇是否吃到了食物还是应该靠食物自己来进行判断
	public boolean isEatFood(Snake snake){
		//System.out.println("is eat food");
		return this.equals(snake.getHead());
	}

	public void drawMe(Graphics g){
		g.setColor(Color.MAGENTA);
		//System.out.println("draw food");
		g.fill3DRect(x*Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
		
	}

	public void newFood(Point p){		
		this.setLocation(p);
	}
}
