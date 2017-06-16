package com.hshooting.data;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;

import com.hshooting.data.missile.Missile;
import com.hshooting.data.unit.AirPlane;
import com.hshooting.data.unit.Effect;
import com.hshooting.data.unit.Enemy;
import com.hshooting.data.unit.Unit;
import com.hshooting.util.MathUtility;

public class GameData {
	private static HashSet<Integer> presskey = new HashSet<>();
	public static HashSet<Integer> getPresskey(){
		return presskey;
	}
	public static boolean press(Integer v){
//		System.out.println(presskey);
		return presskey.add(v);
	}
	public static boolean release(Integer v){
		return presskey.remove(v);
	}
	public static boolean isLeft(){
		return presskey.contains(KeyEvent.VK_LEFT) || presskey.contains(KeyEvent.VK_NUMPAD4);
	}
	public static boolean isRight(){
		return presskey.contains(KeyEvent.VK_RIGHT) || presskey.contains(KeyEvent.VK_NUMPAD6);
	}	
	public static boolean isUp(){
		return presskey.contains(KeyEvent.VK_UP) || presskey.contains(KeyEvent.VK_NUMPAD8);
	}
	public static boolean isDown(){
		return presskey.contains(KeyEvent.VK_DOWN) || presskey.contains(KeyEvent.VK_NUMPAD2);
	}
	public static boolean isShoot() {
		return presskey.contains(KeyEvent.VK_Z);
	}
	public static boolean isGuideShoot() {
		return presskey.contains(KeyEvent.VK_X);
	}
	public static boolean isCircleShoot() {
		return presskey.contains(KeyEvent.VK_C);
	}
	
	public static final int width = 800;
	public static final int height = 1000;
	
	public static final int planeStartX = width/2;
	public static final int planeStartY = height - 200;
	private static AirPlane plane = new AirPlane(planeStartX, planeStartY);
	public static AirPlane getPlane() {
		return plane;
	}
	
	public synchronized void drawAll(Graphics g, Window w) {
		plane.draw(g, w);
		for(Missile missile : missilelist)	missile.draw(g, w);
		for(Enemy enemy : enemylist)	enemy.draw(g, w);
		for(Effect effect : effectlist) 		effect.draw(g, w);
		//System.out.println(this);
	}
	
	private static ArrayList<Missile> missilelist = new ArrayList<>();
	public static synchronized void addMissile(Missile missile){
		missilelist.add(missile);
	}
	public static synchronized void removeMissile(Missile missile){
		missilelist.remove(missile);
	}
	public static void clearMissile(){
		missilelist.clear();
	}
	public static synchronized ArrayList<Missile> getMissile(){
		return missilelist;
	}
	
	private static ArrayList<Enemy> enemylist = new ArrayList<>();
	public static synchronized void addEnemy(Enemy enemy){
		enemylist.add(enemy);
	}
	public static synchronized void removeEnemy(Enemy enemy){
		enemylist.remove(enemy);
	}
	public static void clearEnemy(){
		enemylist.clear();
	}
	public static ArrayList<Enemy> getEnemy() {
		return enemylist;
	}
	
	private static ArrayList<Effect> effectlist = new ArrayList<>();
	public static synchronized void addEffect(Effect effect){
		effectlist.add(effect);
	}
	public static synchronized void removeEffect(Effect effect){
		effectlist.remove(effect);
	}
	public static void clearEffect(){
		effectlist.clear();
	}
	
	public static Unit getGuidedMissileTarget(Point p){
//		근접 타겟 반환(성능 위주) - 일정 거리 이내의 타겟 중 한개를 반환
		int length = 100;
		for(Enemy enemy : enemylist){
			if(MathUtility.getLength(p, enemy.getCenterPoint()) < length){
				return enemy;
			}
		}
		
//		무작위 타겟 반환(속도위주)
		if(enemylist.size() > 0){
			return enemylist.get(MathUtility.random(0, enemylist.size()-1));
		}
		return null;
	}
	
	@Override
	public String toString() {
		return "hp = "+plane.getHp()+", isAlive = "+plane.isUnitAlive()+", missile = "+missilelist.size()+", enemy = "+enemylist.size()
						+", effect = " + effectlist.size(); 
	}
	public static String getAllStringData() {
		String str = "";
//		str += "스레드 개수 : "+Thread.activeCount()+"\n";
//		str += "입력 키 정보 : "+presskey.toString()+"\n";
//		str += "===[비행기 정보]===\n";
//		str += getPlane().toString()+"\n";
//		str += "몬스터 개수 : "+enemylist.size()+"\n";
//		str += "미사일 개수 : "+missilelist.size()+"\n";
//		str += "이펙트 개수 : "+effectlist.size()+"\n";
		return str;
	}
}
