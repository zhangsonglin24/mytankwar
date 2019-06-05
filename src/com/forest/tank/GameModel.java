package com.forest.tank;

import com.forest.tank.collider.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameModel {

    Tank myTank;
    ColliderChain chain = new ColliderChain();
    private static final GameModel INSTANCE = new GameModel();
    static {
        INSTANCE.init();
    }

    private void init() {
        myTank = new Tank(100,400,Dir.RIGHT,Group.GOOD);
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        //初始化敌方坦克
        for (int i = 0;i<initTankCount;i++){
            add(new Tank(50 + i*80,200,Dir.DOWN,Group.BAD));
        }
    }

    private GameModel(){}

    public static GameModel getInstance(){
        return INSTANCE;
    }

   /* List<Bullet> bulletList = new ArrayList<>();
    List<Tank> tanks = new ArrayList<>();
    List<Explode> explodes = new ArrayList<>();*/

    List<GameObject> objects = new ArrayList<>();

    public void add(GameObject go){
        this.objects.add(go);
    }

    public void remove(GameObject go){
        this.objects.remove(go);
    }


    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
       /* g.drawString("子弹的数量:" + bulletList.size(), 10, 60);
        g.drawString("敌人的数量:" + tanks.size(), 10, 80);
        g.drawString("爆炸的数量:" + explodes.size(), 10, 100);*/
        g.setColor(c);

        myTank.paint(g);

        for (int i = 0;i < objects.size();i++){
            objects.get(i).paint(g);
        }

        // 互相碰撞
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                chain.collide(o1, o2);
            }
        }
    }

    public Tank getMyTank(){
        return myTank;
    }
}
