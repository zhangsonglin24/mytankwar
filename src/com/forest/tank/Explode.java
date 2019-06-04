package com.forest.tank;

import java.awt.*;

public class Explode extends GameObject {

    public static int WIDTH = ResourceManagers.explodes[0].getWidth();
    public static int HEIGHT = ResourceManagers.explodes[0].getHeight();

    private int x, y;

    GameModel gm = null;

    private int step = 0;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
    }



    public void paint(Graphics g) {
        g.drawImage(ResourceManagers.explodes[step++], x, y, null);

        if(step >= ResourceManagers.explodes.length)
            gm.remove(this);
    }

}
