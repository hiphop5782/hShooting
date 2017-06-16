package com.hshooting.data.missile;

import java.awt.Point;

import com.hshooting.data.GameData;
import com.hshooting.data.unit.Unit;
import com.hshooting.property.GraphicProperty;
import com.hshooting.property.UnitProperty;
import com.hshooting.util.ImageFactory;
import com.hshooting.util.MathUtility;

public class GuidedMissile extends Missile{
	private Unit target;

	public GuidedMissile(Point missileShootPoint, Unit target, int angle) {
		super(missileShootPoint, ImageFactory.getMissile(), UnitProperty.getAirplain_guided_missile_speed(),angle); 
		this.target = target;
	}
	
	@Override
	public void run() {
		//중간에 target이 사라지면 타겟을 변경해야 한다
		while(shutdownFlag && isAlive && !isOutofScreen){
			try{
				if(target == null){
					changeTarget();
				}else{
					if(!target.isUnitAlive()){
//						이렇게 할 경우 계속 바꾸면서 이동하는 문제 발생
//						changeTarget();
//						super.move(MathUtility.getNextGuidePoint(this, target, speed));
						super.move(MathUtility.getNextPoint(this.center, angle, speed));
					}else{
						angle = MathUtility.getAngle(center, target.getCenterPoint());
						super.move(MathUtility.getNextGuidePoint(this, target, speed));
					}
				}
				Thread.sleep(GraphicProperty.frameRate);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		removeMissile();
	}

	private void changeTarget(){
		target = GameData.getGuidedMissileTarget(this.center);
	}
}
