package com.forest.tank.collider;

import com.forest.tank.Bullet;
import com.forest.tank.GameObject;
import com.forest.tank.Wall;

/**
 * @Author: Forest
 * @Date: 2019/6/5
 */
public class BulletWallCollider implements Collider {

	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if (o1 instanceof Bullet && o2 instanceof Wall){
			Bullet b = (Bullet)o1;
			Wall w = (Wall)o2;
			if (b.getRectangle().intersects(w.getRect())){
				b.die();
			}
		}else if (o1 instanceof Wall && o2 instanceof Bullet){
			return collide(o2,o1);
		}
		return true;
	}
}
