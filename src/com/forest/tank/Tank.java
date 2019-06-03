package com.forest.tank;


import java.awt.*;
import java.util.Random;

/**
 * @Author: Forest
 * @Date: 2019/5/31
 */
public class Tank {

	private int x,y;
	private Dir dir = Dir.RIGHT;
	private int SPEED = 1;
	private boolean moving = true;
	private TankFrame tf = null;
	public static int WIDTH = ResourceManagers.tankD.getWidth();
	public static int HEIGHT = ResourceManagers.tankD.getHeight();
	private boolean living = true;
	public Group group = Group.BAD;
	private Random random = new Random();

	public Tank(int x, int y, Dir dir, Group group, TankFrame tf){
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;
	}

	public void paint(Graphics g) {
		if (!living) tf.tanks.remove(this);
		switch (dir){
			case LEFT:
				g.drawImage(ResourceManagers.tankL,x,y,null);
				break;
			case RIGHT:
				g.drawImage(ResourceManagers.tankR,x,y,null);
				break;
			case UP:
				g.drawImage(ResourceManagers.tankU,x,y,null);
				break;
			case DOWN:
				g.drawImage(ResourceManagers.tankD,x,y,null);
				break;
		}
		move(this.group);
	}

	private void move(Group group) {
		if (!moving) return;
		if (group == Group.GOOD){
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

		if (group == Group.BAD){
			if (random.nextInt(10) > 8) this.fire();
		}

	}

	public void fire() {
		int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
		int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
		tf.bulletList.add(new Bullet(bX,bY,this.dir,this.group,tf));
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

	public TankFrame getTf() {
		return tf;
	}

	public void setTf(TankFrame tf) {
		this.tf = tf;
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
}
