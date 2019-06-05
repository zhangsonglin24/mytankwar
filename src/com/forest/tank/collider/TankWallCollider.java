package com.forest.tank.collider;

import com.forest.tank.GameObject;
import com.forest.tank.Tank;
import com.forest.tank.Wall;

/**
 * @Author: Forest
 * @Date: 2019/6/5
 */
public class TankWallCollider implements Collider {
	@Override
	public boolean collide(GameObject o1, GameObject o2) {
		if (o1 instanceof Tank && o2 instanceof Wall){
			Tank t = (Tank) o1;
			Wall w = (Wall) o2;
			if (t.getRectangle().intersects(w.getRect())){
				t.back();
			}
		} else if (o1 instanceof Wall && o2 instanceof Tank) {
			return collide(o2, o1);
		}

		return true;
	}
}
