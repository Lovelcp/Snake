package snake.tool;

import snake.model.Snake;
/**
 * 判断蛇移动时遇到的情况：
 * 1、吃到食物
 * 2、吃到自己
 * 3、撞到障碍物
 * @author Lenovo
 *
 */
public interface SnakeListener {
	void snakeMoved(Snake snake);
}
