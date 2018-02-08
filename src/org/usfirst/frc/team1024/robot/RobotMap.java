/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot;

import edu.wpi.first.wpilibj.I2C.Port;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Motors:
	public static final int FRONT_LEFT_MOTOR_PORT = 42;
	public static final int MIDDLE_LEFT_MOTOR_PORT = 1;
	public static final int REAR_LEFT_MOTOR_PORT = 1; //will be 2
	public static final int FRONT_RIGHT_MOTOR_PORT = 2; //will be 3
	public static final int MIDDLE_RIGHT_MOTOR_PORT = 4;
	public static final int REAR_RIGHT_MOTOR_PORT = 3; //will be 5
	
	public static final int LIFT_MOTOR_1_PORT = 6; //motors will go the same direction
	public static final int LIFT_MOTOR_2_PORT = 7;
	
	public static final int LEFT_INTAKE_MOTOR_PORT = 8;
	public static final int RIGHT_INTAKE_MOTOR_PORT = 9;
	
	//Pnuematics:
	public static final int SHIFTER_PORT = 0;
	
	public static final int LIFT_CLAMP_FOWARD_PORT = 1;
	public static final int LIFT_CLAMP_REVERSE_PORT = 2;
	
	public static final int INTAKE_SLIDE_PORT = 3;
	public static final int INTAKE_OUT_PORT = 4;
	
	public static final int INTAKE_MANIPULATE_PORT = 5;
	
	
	//I2C
	public static final Port NAVX_PORT = Port.kMXP;
	public static final double NAVX_TOLERANCE = 2.0f;
	public static final double MIN_ROTATION_ANGLE = -180;
	public static final double MAX_ROTATION_ANGLE = 180;

	public static final double TRIM_PID_MIN_OUTPUT = -0.2;
	public static final double TRIM_PID_MAX_OUTPUT = 0.2;

	public static final double POS_PID_MIN_OUTPUT = -1.0;
	public static final double POS_PID_MAX_OUTPUT = 1.0;
	
	public static final double TURN_PID_MIN_OUTPUT = -1.0;
	public static final double TURN_PID_MAX_OUTPUT = 1.0;
	
	public static final double TURN_PID_ABSOLUTE_TOLERANCE = 0.5;


	public static final double DRIVETRAIN_ENCODER_DISTANCE_PER_PULSE = (1.0/71.0)*4.0;
	
	
	//Analog
	public static final int ULTRASONIC_PORT = 0;
	
	//Digital
	public static final int ENCODER_CHANNEL_A = 0;
	public static final int ENCODER_CHANNEL_B = 1;
	
	//PID Constants
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
	
	public static final double ENCODER_RATIO_TO_LIFT_SHAFT = 64;
	
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
