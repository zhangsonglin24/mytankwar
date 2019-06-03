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
	public Group group = Group.BAD;

	public Bullet(int x, int y, Dir dir, Group group,TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
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
		if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;
	}

	public void collideWith(Tank tank) {
		if (this.group == tank.getGroup()) return;
		Rectangle rectangle1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
		Rectangle rectangle2 = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
		if (rectangle1.intersects(rectangle2)){
			tank.die();
			this.die();
			tf.explodes.add(new Explode(x,y,tf));
		}
	}

	private void die() {
		this.living = false;
	}
}
