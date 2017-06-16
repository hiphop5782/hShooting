package com.hshooting.data.missile;

import java.awt.Point;

import com.hshooting.data.GameData;
import com.hshooting.property.UnitProperty;

public class CircleMissile{
	private Point center;
	private int angle;
	private int size;
	private int[] angleList;
	private StraightMissile[] missileList;
	public CircleMissile(Point missileShootPoint, int angle, int size){
		this.center = missileShootPoint;
		this.size = size;
		this.angle = angle;
		this.angleList = new int[size];
		this.missileList = new StraightMissile[size];
		setAngleNMissile();
	}
	
	private void setAngleNMissile(){
		int s = angle;
		for(int i=0; i<size; i++){
			s += (360 / size);
			s %= 360;
			angleList[i] = s;
			missileList[i] = new StraightMissile(center, UnitProperty.getAirplain_circle_missile_speed(), angleList[i]);
			GameData.addMissile(missileList[i]);
		}
	}
}
