package com.hshooting.data.unit;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;

import com.hshooting.data.DrawableObject;
import com.hshooting.data.GameData;
import com.hshooting.property.UnitProperty;

public class Effect extends Thread implements DrawableObject{
	
	protected int x, y;
	protected int width, height;
	protected Image image;
	
	public Effect(Point p, Image image){
		this(p.x, p.y, image);
	}
	
	public Effect(int x, int y, Image image){
		this.x = x;
		this.y = y;
		this.image = image;
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
		this.setDaemon(true);
		this.start();
	}
	
	@Override
	public void run() {
		try{
			Thread.sleep(UnitProperty.getEffect_time());
		}catch(Exception e){
			e.printStackTrace();
		}
		GameData.removeEffect(this);
	}

	@Override
	public void draw(Graphics g, Window w) {
		g.drawImage(image, x-width/2, y-height/2, w);
	}
}
