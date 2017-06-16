package com.hshooting.data.missile;

import java.awt.Image;
import java.awt.Point;

import com.hshooting.data.GameData;
import com.hshooting.data.unit.Unit;
import com.hshooting.property.GraphicProperty;
import com.hshooting.util.ImageFactory;
import com.hshooting.util.MathUtility;

public abstract class Missile extends Unit{
	public Missile(int x, int y, Image image, int speed, int angle){
		super(x, y, image, speed, angle, 1);
		GameData.addMissile(this);
	}
	
	public Missile(int x, int y, int angle, int speed) {
		this(x, y, ImageFactory.getMissile(), speed, angle);
	}
	
	protected Missile(Point missileShootPoint,  Image image, int speed, int angle){
		this(missileShootPoint.x, missileShootPoint.y, image, speed, angle);
	}
	
	public Missile(Point missileShootPoint, int angle, int speed) {
		this(missileShootPoint.x, missileShootPoint.y, angle, speed);
	}

	public synchronized void removeMissile(){
		isOutofScreen = true;
		isAlive = false;
		GameData.removeMissile(this);
	}

	@Override
	public String toString() {
		Point s = getStartingPoint();
		Point e = getEndPoint();
		Point c = center;
		return isOutofScreen+", s["+s.x+","+s.y+"], c["+c.x+","+c.y+"], e["+e.x+","+e.y+"]";
	}
}
