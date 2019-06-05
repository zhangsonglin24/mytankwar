package com.forest.tank.collider;

import com.forest.tank.GameObject;

/**
 * @Author: Forest
 * @Date: 2019/6/5
 */
public interface Collider {

	boolean collide(GameObject o1,GameObject o2);
}
