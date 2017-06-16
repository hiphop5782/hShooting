package com.hshooting.util;

import java.awt.Point;

import com.hshooting.data.missile.Missile;
import com.hshooting.data.unit.Unit;

public class MathUtility {
	public static int random(int s, int e){
		return (int)(Math.random() * (e-s+1)) + s;
	}
	public static Point getNextPoint(Point p, int angle, int speed){
		return new Point(
					(int)(p.x + Math.cos(angle * Math.PI / 180) * speed),
					(int)(p.y + Math.sin(angle * Math.PI / 180) * speed)
				);
	}
	public static Point getNextGuidePoint(Missile m, Unit u, int speed){
		int angle = getAngle(m.getCenterPoint(), u.getCenterPoint());
		int mAngle = m.getAngle();
		int theta = 5;
		if(mAngle > angle){
			mAngle -= Math.min(Math.abs(mAngle - angle), theta);
		}else if(mAngle < angle){
			mAngle += Math.min(Math.abs(mAngle - angle), theta);
		}
		return getNextPoint(m.getCenterPoint(), angle, speed);
	}
	public synchronized static boolean checkCrash(Point p1, int r1, Point p2, int r2){
		int xgap = Math.abs(p1.x - p2.x);
		int ygap = Math.abs(p1.y - p2.y);
		double length = Math.sqrt(xgap * xgap + ygap * ygap);
		return length < r1 + r2;
	}
	public static int getAngle(Point p1, Point p2){
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		int angle = (int)(Math.atan2(dy, dx) * 180 / Math.PI);
		return angle > 0 ? angle : angle + 360;
	}
	public static Point getCrashPoint(Point p1, int r1, Point p2, int r2){
		int angle = getAngle(p1, p2);
		return getNextPoint(p1, angle, r1);
	}
	public static int getLength(Point p1, Point p2) {
		int dx = Math.abs(p1.x - p2.x);
		int dy = Math.abs(p1.y - p2.y);
		return (int)Math.sqrt(dx*dx + dy*dy);
	}
}
