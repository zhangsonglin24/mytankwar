package com.forest.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank = new Tank(100,400,Dir.RIGHT,Group.GOOD,this);

    List<Bullet> bulletList = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();

    public GameModel(){
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        //初始化敌方坦克
        for (int i = 0;i<initTankCount;i++){
            this.tanks.add(new Tank(50 + i*80,200,Dir.DOWN,Group.BAD,this));
        }
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bulletList.size(), 10, 60);
        g.drawString("敌人的数量:" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);
        g.setColor(c);

        myTank.paint(g);
        for (int i = 0; i < bulletList.size(); i++) {
            Bullet b = bulletList.get(i);
            b.paint(g);
        }
        for (int i = 0; i < tanks.size(); i++) {
            Tank b = tanks.get(i);
            b.paint(g);
        }
        for (int i = 0;i <explodes.size();i++){
            explodes.get(i).paint(g);
        }
        //子弹与坦克碰撞检测
        for (int i = 0;i<bulletList.size();i++){
            for (int j= 0;j<tanks.size();j++){
                bulletList.get(i).collideWith(tanks.get(j));
            }
        }
    }

    public Tank getMyTank(){
        return myTank;
    }
}
