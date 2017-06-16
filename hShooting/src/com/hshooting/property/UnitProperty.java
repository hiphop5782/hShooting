package com.hshooting.property;

public class UnitProperty {
	public static final int UPPER_ANGLE = 270;
	public static final int RIGHT_ANGLE = 0;
	public static final int DOWN_ANGLE = 90;
	public static final int LEFT_ANGLE = 180;
	private static int airplane_speed = 7;
	public static int getAirplane_speed() {
		return airplane_speed;
	}
	public static void setAirplane_speed(int airplane_speed) {
		UnitProperty.airplane_speed = airplane_speed;
	}
	
	private static int airplain_missile_delay = 5;//5일 경우 , 5 / 60 초 동안 딜레이
	public static int getAirplain_missile_delay() {
		return airplain_missile_delay;
	}
	public static void setAirplain_missile_delay(int airplain_missile_delay) {
		UnitProperty.airplain_missile_delay = airplain_missile_delay;
	}
	
	private static int airplain_misile_speed = 15;
	public static int getAirplain_misile_speed() {
		return airplain_misile_speed;
	}
	public static void setAirplain_misile_speed(int airplain_misile_speed) {
		UnitProperty.airplain_misile_speed = airplain_misile_speed;
	}
	
	private static int airplain_guided_missile_speed = 5;
	public static int getAirplain_guided_missile_speed() {
		return airplain_guided_missile_speed;
	}
	public static void setAirplain_guided_missile_speed(int airplain_guided_missile_speed) {
		UnitProperty.airplain_guided_missile_speed = airplain_guided_missile_speed;
	}
	
	private static int airplain_circle_missile_speed = 5;
	public static int getAirplain_circle_missile_speed() {
		return airplain_circle_missile_speed;
	}
	public static void setAirplain_circle_missile_speed(int airplain_circle_missile_speed) {
		UnitProperty.airplain_circle_missile_speed = airplain_circle_missile_speed;
	}
	
	private  static long effect_time = 500;//0.5초
	public static long getEffect_time() {
		return effect_time;
	}
	public static void setEffect_time(long effect_time) {
		UnitProperty.effect_time = effect_time;
	}
	
}
