package com.forest.tank.collider;

import com.forest.tank.Bullet;
import com.forest.tank.Explode;
import com.forest.tank.GameModel;
import com.forest.tank.GameObject;
import com.forest.tank.Tank;

/**
 * @Author: Forest
 * @Date: 2019/6/5
 */
public class BulletTankCollider implements Collider {
	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet b = (Bullet)o1;
			Tank t = (Tank)o2;
			if(b.group == t.getGroup()) return true;

			if(b.getRectangle().intersects(t.getRectangle())) {
				t.die();
				b.die();
				int eX = t.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
				int eY = t.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
				new Explode(eX, eY);
				return false;
			}
		} else if (o1 instanceof Tank && o2 instanceof Bullet) {
			return collide(o2, o1);
		}
		return true;
	}

}
