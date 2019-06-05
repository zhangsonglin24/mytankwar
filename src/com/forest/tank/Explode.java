package com.forest.tank;

import java.awt.*;

public class Explode extends GameObject {

    public static int WIDTH = ResourceManagers.explodes[0].getWidth();
    public static int HEIGHT = ResourceManagers.explodes[0].getHeight();
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }



    public void paint(Graphics g) {
        g.drawImage(ResourceManagers.explodes[step++], x, y, null);

        if(step >= ResourceManagers.explodes.length)
            GameModel.getInstance().remove(this);
    }

}
