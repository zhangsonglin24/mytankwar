package com.forest.tank;


import java.awt.*;

/**
 * @Author: Forest
 * @Date: 2019/5/31
 */
public class Bullet extends GameObject {
	private static final int SPEED = 10;
	public static int WIDTH = ResourceManagers.bulletD.getWidth();
	public static int HEIGHT = ResourceManagers.bulletD.getHeight();
	private Dir dir;
	private boolean living = true;
	//初始化一个Rectangle，跟着bullet
	public Rectangle rectangle = new Rectangle();
	public Group group = Group.BAD;

	public Bullet(int x, int y, Dir dir, Group group) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;

		rectangle.x = this.x;
		rectangle.y = this.y;
		rectangle.width = WIDTH;
		rectangle.height = HEIGHT;

		GameModel.getInstance().add(this);
	}

	public void paint(Graphics g) {
		if (!living){
			GameModel.getInstance().remove(this);
		}
		switch (dir){
			case LEFT:
				g.drawImage(ResourceManagers.bulletL,x,y,null);
				break;
			case RIGHT:
				g.drawImage(ResourceManagers.bulletR,x,y,null);
				break;
			case UP:
				g.drawImage(ResourceManagers.bulletU,x,y,null);
				break;
			case DOWN:
				g.drawImage(ResourceManagers.bulletD,x,y,null);
				break;
		}
		move();
	}

	private void move() {
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
		rectangle.x = this.x;
		rectangle.y = this.y;
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
	}

	public boolean collideWith(Tank tank) {
		if (this.group == tank.getGroup()) return false;
		if (rectangle.intersects(tank.rectangle)){
			tank.die();
			this.die();
			int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
			int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
			GameModel.getInstance().add(new Explode(eX,eY));
		}
		return false;
	}

	public void die() {
		this.living = false;
	}

	public Rectangle getRectangle() {
		return rectangle;
	}
}
