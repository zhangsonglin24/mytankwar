package com.forest.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Forest
 * @Date: 2019/5/30
 */
public class TankFrame extends Frame {

	static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;

	TankFrame() {
		setTitle("坦克大战");
		setSize(GAME_WIDTH,GAME_HEIGHT);
		this.addKeyListener(new MyKeyListener());
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	Image offScreenImage = null;

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		GameModel.getInstance().paint(g);
	}


	class MyKeyListener extends KeyAdapter {

		boolean L = false;
		boolean U = false;
		boolean R = false;
		boolean D = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			switch (code) {
				case KeyEvent.VK_LEFT:
					L = true;
					break;
				case KeyEvent.VK_UP:
					U = true;
					break;
				case KeyEvent.VK_RIGHT:
					R = true;
					break;
				case KeyEvent.VK_DOWN:
					D = true;
					break;
			}
			setMainTankDir();
            new Thread(()->new Audio("audio/tank_move.wav").play()).start();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int code = e.getKeyCode();
			switch (code) {
				case KeyEvent.VK_LEFT:
					L = false;
					break;
				case KeyEvent.VK_UP:
					U = false;
					break;
				case KeyEvent.VK_RIGHT:
					R = false;
					break;
				case KeyEvent.VK_DOWN:
					D = false;
					break;
				case KeyEvent.VK_CONTROL:
					GameModel.getInstance().getMyTank().fire();
					break;
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			Tank myTank = GameModel.getInstance().getMyTank();
			if (!L && !U && !R && !D){
				myTank.setMoving(false);
			}else {
				myTank.setMoving(true);
				if (L) myTank.setDir( Dir.LEFT);
				if (U) myTank.setDir( Dir.UP);
				if (R) myTank.setDir( Dir.RIGHT);
				if (D) myTank.setDir( Dir.DOWN);
			}
		}
	}
}
