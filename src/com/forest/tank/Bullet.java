package com.forest.tank;


import java.awt.*;

/**
 * @Author: Forest
 * @Date: 2019/5/31
 */
public class Bullet {
	private static final int SPEED = 10;
	public static int WIDTH = ResourceManagers.bulletD.getWidth();
	public static int HEIGHT = ResourceManagers.bulletD.getHeight();
	private int x,y;
	private Dir dir;
	private boolean living = true;
	TankFrame tf = null;
	//初始化一个Rectangle，跟着bullet
	Rectangle rectangle = new Rectangle();
	public Group group = Group.BAD;

	public Bullet(int x, int y, Dir dir, Group group,TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;

		rectangle.x = this.x;
		rectangle.y = this.y;
		rectangle.width = WIDTH;
		rectangle.height = HEIGHT;

		tf.bulletList.add(this);
	}

	public void paint(Graphics g) {
		if (!living){
			tf.bulletList.remove(this);
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

	public void collideWith(Tank tank) {
		if (this.group == tank.getGroup()) return;
		if (rectangle.intersects(tank.rectangle)){
			tank.die();
			this.die();
			int eX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
			int eY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
			tf.explodes.add(new Explode(eX,eY,tf));
		}
	}

	private void die() {
		this.living = false;
	}
}
