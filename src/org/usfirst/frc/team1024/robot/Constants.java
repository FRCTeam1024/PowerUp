package org.usfirst.frc.team1024.robot;

public class Constants {
	
	public static final double NAVX_TOLERANCE = 2.0f;
	public static final double TURN_KP = 0.05; //was 0.055
	public static final double TURN_KI = 0.0;
	public static final double TURN_KD = 0.05; //was 0.1
	public static final double TURN_KF = 0.0;
	
	public static final double POS_KP = 0.1;
	public static final double POS_KI = 0.0;
	public static final double POS_KD = 0.0;
	public static final double POS_KF = 0.0;

	public static final double TRIM_KP = 0.055;
	public static final double TRIM_KI = 0.0;
	public static final double TRIM_KD = 0.1;
	public static final double TRIM_KF = 0.0;
	
	
	public static final double WHEEL_DIAMETER_IN = 6.125;
	public static final double MAGNETIC_ENCODER_COUNTS_PER_REVOLUTION = 1024;
	public static final double OPTICAL_ENCODER_COUNTS_PER_REVOLUTION = 250;
	public static final double ENCODER_RATIO_TO_WHEEL = 3;
	
	//Ultrasonic Constants:
	public static final double MILLIMETER_SCALE_FACTOR = 124;
	public static final double INCH_SCALE_FACTOR = 48.8188; //MILLIMETER_SCALE_FACTOR * 0.3937;
	
	
	//Robot Constants:
	public static final double ROBOT_LENGTH_IN = 32.0; //front-to-back For 2018 Bot 39
	public static final double ROBOT_WIDTH_IN = 41.0; //side-to-side For 2018 Bot 35.5
	
	//Field Constants:
	public static final double FIELD_WIDTH = 324.0;
	public static final double FIELD_LENGTH = 648.0;
	public static final double WALL_TO_SWITCH_DISTANCE = 140.0;
	public static final double WALL_TO_SCALE_DISTANCE = 299.65;
	
	
	
	
	
	
	
}
