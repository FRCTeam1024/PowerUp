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
	public static final int REAR_LEFT_MOTOR_PORT = 1;
	public static final int FRONT_RIGHT_MOTOR_PORT = 2;
	public static final int REAR_RIGHT_MOTOR_PORT = 3;
	
	//I2C
	public static final Port NAVX_PORT = Port.kMXP;
	
	//Analog
	public static final int ULTRASONIC_PORT = 0;
	
	//Digital
	public static final int ENCODER_CHANNEL_A = 0;
	public static final int ENCODER_CHANNEL_B = 1;
}
