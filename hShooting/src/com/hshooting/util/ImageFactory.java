package com.hshooting.util;

import java.awt.Image;

import javax.swing.ImageIcon;

public class ImageFactory {
	private static String dir = System.getProperty("user.dir");
	private static Image airplain = new ImageIcon(dir+"/image/ex/airplain.png").getImage();
	private static Image airplain_protected = 
									new ImageIcon(dir+"/image/ex/airplain_protected.png").getImage();
	private static Image missile = new ImageIcon(dir+"/image/ex/missile.png").getImage();
	private static Image enemy = new ImageIcon(dir+"/image/ex/enemy.png").getImage();
	public static Image getAirplain() {
		return airplain;
	}
	public static void setAirplain(Image airplain) {
		ImageFactory.airplain = airplain;
	}
	public static Image getMissile() {
		return missile;
	}
	public static void setMissile(Image missile) {
		ImageFactory.missile = missile;
	}
	public static Image getEnemy() {
		return enemy;
	}
	public static void setEnemy(Image enemy) {
		ImageFactory.enemy = enemy;
	}
	private static Image effect = new ImageIcon(dir+"/image/ex/effect.png").getImage();
	public static Image getEffect() {
		return effect;
	}
	public static void setEffect(Image effect) {
		ImageFactory.effect = effect;
	}
	public static Image getAirplain_protected() {
		return airplain_protected;
	}
	public static void setAirplain_protected(Image airplain_protected) {
		ImageFactory.airplain_protected = airplain_protected;
	}
}
