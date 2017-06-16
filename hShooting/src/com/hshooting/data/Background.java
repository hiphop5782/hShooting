package com.hshooting.data;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Window;

public abstract class Background extends Thread{
	protected Image image;
	
	protected int width;
	protected int height;
	protected int speed;
	protected int angle;
	
	protected Background(Image image, int width, int height, int speed, int angle){
		this.image = image;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.angle = angle;
	}
	
	protected abstract void draw(Graphics g , Window w);
}
