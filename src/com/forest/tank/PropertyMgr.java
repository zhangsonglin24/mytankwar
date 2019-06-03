package com.forest.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: Forest
 * @Date: 2019/6/3
 */
public class PropertyMgr {
	private static volatile  Properties properties;

	private PropertyMgr(){}

	public static Object get(String key){
		if (properties == null){
			synchronized (PropertyMgr.class){
				if (properties == null){
					properties = new Properties();
					try {
						properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return properties.get(key);
	}

}
