package com.hshooting.data.missile;

import java.awt.Image;
import java.awt.Point;

import com.hshooting.property.GraphicProperty;
import com.hshooting.util.ImageFactory;
import com.hshooting.util.MathUtility;

public class StraightMissile extends Missile{

	public StraightMissile(Point missileShootPoint, int speed, int angle) {
		super(missileShootPoint, ImageFactory.getMissile(), speed, angle);
	}

	public void run(){
		while(shutdownFlag && isAlive && !isOutofScreen){
			try{
				super.move(MathUtility.getNextPoint(center, angle, speed));
				Thread.sleep(GraphicProperty.frameRate);
			}catch(Exception e){
				break;
			}
		}
		removeMissile();
	}
}
