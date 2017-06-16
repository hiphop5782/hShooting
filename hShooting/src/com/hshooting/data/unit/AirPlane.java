package com.hshooting.data.unit;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Window;
import java.util.Iterator;

import com.hshooting.data.GameData;
import com.hshooting.data.missile.CircleMissile;
import com.hshooting.data.missile.GuidedMissile;
import com.hshooting.data.missile.StraightMissile;
import com.hshooting.property.GraphicProperty;
import com.hshooting.property.UnitProperty;
import com.hshooting.util.ImageFactory;
import com.hshooting.util.MathUtility;

public class AirPlane extends Unit{
	
	public AirPlane(int x, int y) {
		super(x, y, ImageFactory.getAirplain(), UnitProperty.getAirplane_speed(), UnitProperty.UPPER_ANGLE, 5);
		isProtected = true;
	}
	
	public void run(){
		int delay = 0;
		while(shutdownFlag && isAlive){
			try{
				System.out.println(this);
				Unit u = checkCrash();
				if(u != null){
					Point p = MathUtility.getCrashPoint(u.center, u.width, center, width);
					//System.out.println(p);
					crash(p);
					u.crash(p);
				}
				if(GameData.isDown())				moveDown();
				if(GameData.isUp())					moveUp();
				if(GameData.isLeft())					moveLeft();
				if(GameData.isRight())				moveRight();
				delay++;
				if(delay >= UnitProperty.getAirplain_missile_delay()){
					if(GameData.isShoot()){
						shootStraightMissile();			delay = 0;
					}
					if(GameData.isGuideShoot())	{
						shootGuidedMissile();			delay = 0;
					}
					if(GameData.isCircleShoot()){
						shootCircleMissile();				delay = 0;
					}
				}
				Thread.sleep(GraphicProperty.frameRate);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		System.out.println("===[비행기 쓰레드가 소멸하였습니다.]===");
	}
	
	private Point getMissileShootPoint(){
		return new Point(center.x, center.y - height/2);
	}
	
	@Override
	public void draw(Graphics g, Window w) {
		if(isProtected){
			g.drawImage(ImageFactory.getAirplain_protected(), getStartingPoint().x, getStartingPoint().y, w);
		}else{
			g.drawImage(image, getStartingPoint().x, getStartingPoint().y, w);
		}
		
	}
	
	@Override
	public Unit checkCrash() {
		for(Iterator<Enemy> it = GameData.getEnemy().iterator(); it.hasNext(); ){
			try{
				Enemy e = it.next();
				if(MathUtility.checkCrash(e.getCenterPoint(), e.width, center, width)){
					return e;
				}
			}catch(Exception e){}
		}
		return null;
	}
	
	private void shootGuidedMissile(){
		int sAngle = UnitProperty.UPPER_ANGLE - 20;
		int eAngle = UnitProperty.UPPER_ANGLE + 20;
		int angle = MathUtility.random(sAngle, eAngle);
		new GuidedMissile(
				getMissileShootPoint(), GameData.getGuidedMissileTarget(this.center), angle);
	}
	
	private void shootStraightMissile() {
			new StraightMissile(getMissileShootPoint(), 
						UnitProperty.getAirplain_misile_speed(), angle);
	}
	
	private void shootCircleMissile(){
//		int size = MathUtility.random(1, 20);
		int size = 20;
		new CircleMissile(getMissileShootPoint(), angle, size);
	}
	
	@Override
	public boolean doSomeActionWhenOutofRange() {
		return false;
	}
	
	@Override
	public String toString() {
		String str = "";
		str += "비행기 체력="+hp+"\n";
		str += "비행기 위치="+center+"\n";
		str += "비행기 보호막="+isProtected+"\n";
		str += "비행기 생존="+isAlive+"\n";
		str += "비행기 스레드="+Thread.currentThread()+"\n";
		str += "스레드 생존="+!isInterrupted()+"\n";
		return str;
	}
}
