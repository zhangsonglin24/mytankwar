package com.forest.tank;


import java.awt.*;
import java.util.Random;

/**
 * @Author: Forest
 * @Date: 2019/5/31
 */
public class Tank extends GameObject {

	protected Dir dir = Dir.RIGHT;
	private int SPEED = 5;
	private boolean moving = true;
	public static int WIDTH = ResourceManagers.goodTankU.getWidth();
	public static int HEIGHT = ResourceManagers.goodTankU.getHeight();
	private boolean living = true;
	public Group group = Group.BAD;
	private Random random = new Random();
	//初始化一个Rectangle，跟着tank
	public Rectangle rectangle = new Rectangle();
	int oldX, oldY;

	FireStrategy fs;

	public Tank(int x, int y, Dir dir, Group group){
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;

		rectangle.x = this.x;
		rectangle.y = this.y;
		rectangle.width = WIDTH;
		rectangle.height = HEIGHT;

		if (this.group == Group.BAD){
			fs = new DefaultFireStrategy();
		}else {
			fs = new FourDirFireStrategy();
		}
	}

	public void paint(Graphics g) {
		if (!living) GameModel.getInstance().remove(this);
		switch (dir){
			case LEFT:
				g.drawImage(this.group == Group.GOOD ? ResourceManagers.goodTankL : ResourceManagers.badTankL,x,y,null);
				break;
			case RIGHT:
				g.drawImage(this.group == Group.GOOD ? ResourceManagers.goodTankR : ResourceManagers.badTankR,x,y,null);
				break;
			case UP:
				g.drawImage(this.group == Group.GOOD ? ResourceManagers.goodTankU : ResourceManagers.badTankU,x,y,null);
				break;
			case DOWN:
				g.drawImage(this.group == Group.GOOD ? ResourceManagers.goodTankD : ResourceManagers.badTankD,x,y,null);
				break;
		}
		move(this.group);
	}

	public void back() {
		x = oldX;
		y = oldY;
	}

	private void move(Group group) {
		oldX = x;
		oldY = y;
		if (!moving) return;
		if (group == Group.GOOD){
			//增加主战tank速度
			SPEED = 10;
			switch (dir){
				case LEFT:
					x -= SPEED;
					break;
				case UP:
					y -= SPEED;
					break;
				case RIGHT:
					x += SPEED;
					break;
				case DOWN:
					y += SPEED;
					break;
			}
		}else {
			switch (dir){
				case LEFT:
					x -= SPEED;
					break;
				case UP:
					y -= SPEED;
					break;
				case RIGHT:
					x += SPEED;
					break;
				case DOWN:
					y += SPEED;
					break;
			}
		}

		//随机开火
		if (group == Group.BAD && random.nextInt(100) > 95) this.fire();

		if (group == Group.BAD && random.nextInt(10) > 8) randomDir();

		//边界检测
		boundsCheck();
		//更新rectangle
		rectangle.x = this.x;
		rectangle.y = this.y;
	}

	private void boundsCheck() {
		if (this.x < 0) x =0;
		else if (this.y < 30) y = 30;
		else if (this.x > TankFrame.GAME_WIDTH - Tank.HEIGHT) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
		else if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
	}

	//随机改变方向
	private void randomDir() {
		dir = Dir.values()[random.nextInt(4)];
	}

	public void fire() {
		fs.fire(this);
	}




	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public int getSPEED() {
		return SPEED;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void die() {
		this.living = false;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
}
