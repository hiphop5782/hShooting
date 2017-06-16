package com.hshooting.round;

public class EnemyCreator extends Thread{
	protected boolean playing = true;
	
	public EnemyCreator(){}
	
	public void finish(){
		playing = false;
	}
}
