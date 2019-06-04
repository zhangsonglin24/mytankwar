package com.forest.tank;

/**
 * @Author: Forest
 * @Date: 2019/5/30
 */
public class TankTest {
	public static void main(String[] args) throws InterruptedException {
		TankFrame tankFrame = new TankFrame();

		while (true){
			Thread.sleep(50);
			tankFrame.repaint();
		}
	}


}
