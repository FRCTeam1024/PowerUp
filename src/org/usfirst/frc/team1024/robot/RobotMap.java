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
	
	public static final int INTAKE_IN_PORT = 3;
	public static final int INTAKE_OUT_PORT = 4;
	
	public static final int INTAKE_MANIPULATE_PORT = 5;
	
	
	//I2C
	public static final Port NAVX_PORT = Port.kMXP;
	
	//Analog
	public static final int ULTRASONIC_PORT = 0;
	
	//Digital
	public static final int ENCODER_CHANNEL_A = 0;
	public static final int ENCODER_CHANNEL_B = 1;
}
