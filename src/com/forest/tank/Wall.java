package com.forest.tank;

import java.awt.*;

/**
 * @Author: Forest
 * @Date: 2019/6/5
 */
public class Wall extends GameObject {

	int w, h;

	public Rectangle rect;

	public Wall(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		this.rect = new Rectangle(x, y, w, h);
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GREEN);
		g.fillRect(x, y, w, h);
		g.setColor(c);
	}

	public Rectangle getRect() {
		return rect;
	}
}
