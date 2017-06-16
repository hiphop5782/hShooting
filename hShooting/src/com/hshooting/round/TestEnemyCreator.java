package com.hshooting.round;

import com.hshooting.data.GameData;
import com.hshooting.data.unit.Enemy;
import com.hshooting.util.MathUtility;

public class TestEnemyCreator extends EnemyCreator{
	@Override
	public void run() {
		while(playing){
			try{
				int x = MathUtility.random(0+1, GameData.width-1);
				GameData.addEnemy(new Enemy(x, 1));
				int pause = MathUtility.random(1, 4);
				Thread.sleep(pause * 100);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
