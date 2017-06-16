package com.hshooting.data.unit;

import java.awt.Point;
import java.util.Iterator;

import com.hshooting.data.GameData;
import com.hshooting.data.missile.Missile;
import com.hshooting.property.GraphicProperty;
import com.hshooting.property.UnitProperty;
import com.hshooting.util.ImageFactory;
import com.hshooting.util.MathUtility;

public class Enemy extends Unit{
	public Enemy(int x, int y) {
		super(x, y, ImageFactory.getEnemy(), 3, UnitProperty.DOWN_ANGLE, 1);
	}
	
	@Override
	public void run() {
		while(shutdownFlag && isAlive && !isOutofScreen){
			try{
				super.moveDown();
				Unit u = checkCrash();
				if(u != null){
					Point p = MathUtility.getCrashPoint(u.center, u.width, center, width);
					crash(p);
					u.crash(p);
				}
				Thread.sleep(GraphicProperty.frameRate);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		GameData.removeEnemy(this);
	}
	
	/**
	 * �� ������ �浹 �˻�
	 * �߽��� ������ �Ÿ��� ���� ���� �������� �հ�� ��
	 * @return �浹�� �̻��� ��ü, ������ null
	 */
	@Override
	public Unit checkCrash() {
		//�̻��ϵ� ��, ��ü�� ���̶�� �����ϰ� ���� : �������� width
		for(Iterator<Missile> it = GameData.getMissile().iterator(); it.hasNext(); ){
			try{
				Missile missile = it.next();
				if(missile != null){
					boolean isCrashed = MathUtility.checkCrash(
							missile.getCenterPoint(), missile.getWidth(), this.center, this.width);
					if(isCrashed) return missile;
				}
			}catch(Exception e){}
		}
		return null;
	}
}











