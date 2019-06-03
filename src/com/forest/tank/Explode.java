package com.forest.tank;

import java.awt.*;

public class Explode {

    public static int WIDTH = ResourceManagers.explodes[0].getWidth();
    public static int HEIGHT = ResourceManagers.explodes[0].getHeight();

    private int x, y;

    TankFrame tf = null;

    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }



    public void paint(Graphics g) {
        g.drawImage(ResourceManagers.explodes[step++], x, y, null);

        if(step >= ResourceManagers.explodes.length)
            tf.explodes.remove(this);
    }

}
