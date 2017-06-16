package com.hshooting.data.unit;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;

import com.hshooting.data.DrawableObject;
import com.hshooting.data.GameData;
import com.hshooting.thread.ThreadData;
import com.hshooting.util.ImageFactory;

public abstract class Unit extends Thread implements DrawableObject{
	protected Image image;
	protected Point center;

	protected static boolean shutdownFlag = true;
	public static void shutdown(){
		shutdownFlag = false;
	}
	protected boolean isAlive = true;
	protected boolean isOutofScreen = false;
	protected boolean isProtected = false;
	
	protected int width;
	protected int height;
	protected int type;//원, 사각형 등
	protected int speed;
	protected int hp;
	protected int angle;
	
	protected Point startingPoint;
	
	protected Unit(int x, int y, Image image, int speed, int angle, int hp){
		center = new Point(x, y);
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		this.image = image;
		this.speed= speed;
		this.angle = angle;
		this.hp = hp;
		calcStartingPoint();
		ThreadData.addService(this);
	}
	
	public boolean isUnitAlive(){
		return isAlive;
	}
	
	public int getAngle(){
		return angle;
	}
	
	public int getHp(){
		return hp;
	}
	
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	
	public Point getCenterPoint(){
		return center;
	}
	
	private void calcStartingPoint(){
		startingPoint = new Point(center.x - width/2, center.y - height/2);
	}
	
	public void move(Point p){
		move(p.x, p.y);
	}
	public void move(int x, int y){
		if(!doSomeActionWhenOutofRange() && outOfRange(x, y)){
			return;
		}
		center.x = x;	center.y = y;
		calcStartingPoint();
	}
	
	protected boolean isOutOfRange(){
		return getEndPoint().x < 0 || getEndPoint().y < 0
						|| getStartingPoint().x > GameData.width
						|| getStartingPoint().y > GameData.height;
	}
	
	private boolean outOfRange(int x, int y){
		return x + width/2 >= GameData.width 
								|| y + height/2 >= GameData.height
								|| x - width/2 <= 0 || y - height/2 <= 0;
	}
	
	public void moveUp(){
		move(center.x, center.y-speed); 
	}
	public void moveDown(){
		move(center.x, center.y+speed);
	}
	public void moveLeft(){
		move(center.x-speed, center.y);
	}
	public void moveRight(){
		move(center.x+speed, center.y);
	}
	
	public Point getStartingPoint(){
		return startingPoint;
	}
	
	public Point getEndPoint(){
		return new Point(startingPoint.x + width, startingPoint.y + height);
	}
	
	/**
	 * 
	 * @return 밖으로 내보낼거면 true, 내보내지 않을거면 false
	 */
	public boolean doSomeActionWhenOutofRange(){
		if(isOutOfRange()){
			isOutofScreen = true;
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 유닛과 미사일의 충돌 여부를 판정
	 * @return
	 */
	public Unit checkCrash(){
		return null;
	}
	
	public void crash(Point p){
		if(isProtected) return;
		GameData.addEffect(new Effect(p.x, p.y, ImageFactory.getEffect()));
		if(hp > 0){
			hp--;
		}
		if(hp == 0){
			isAlive = false;
		}
	}
	
	public void draw(Graphics g, Window w){
		g.drawImage(image, startingPoint.x, startingPoint.y, w);
	}
}
