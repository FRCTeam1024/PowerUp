/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot;

import edu.wpi.first.wpilibj.I2C.Port;

public class RobotMap {
	//Motors:
	public static final int LEFT_INTAKE_MOTOR_PORT = 42;
	public static final int RIGHT_INTAKE_MOTOR_PORT = 1;
	public static final int FRONT_LEFT_MOTOR_PORT = 2;
	public static final int MIDDLE_LEFT_MOTOR_PORT = 3;
	public static final int REAR_LEFT_MOTOR_PORT = 4;
	public static final int FRONT_RIGHT_MOTOR_PORT = 5;
	public static final int LIFT_MOTOR_1_PORT = 6;
	public static final int LIFT_MOTOR_2_PORT = 7;
	public static final int MIDDLE_RIGHT_MOTOR_PORT = 8;
	public static final int REAR_RIGHT_MOTOR_PORT = 9;
	
	//Pnuematics:
	public static final int INTAKE_SLIDE_PORT = 1;
	public static final int SHIFTER_PORT = 2;
	public static final int INTAKE_POS_PORT = 4;
	public static final int LIFT_CLAMP_PORT = 5;
	
	//I2C
	public static final Port NAVX_PORT = Port.kMXP;
	
	//Analog
	public static final int ULTRASONIC_PORT = 0;
	
	//Digital
	public static final int DRIVE_ENCODER_CHANNEL_A = 0;
	public static final int DRIVE_ENCODER_CHANNEL_B = 1;
	public static final int INTAKE_BREAKBEAM_RECIEVER_PORT = 2;
	
	//Controller Constants:
	public static final int LEFT_JOYSTICK_PORT = 0;
	public static final int RIGHT_JOYSTICK_PORT = 1;
	public static final int LOGITECH_JOYSTICK_PORT = 2;
	
	//
	
	public static final int LIFT_STICK_AXIS = 1;
	
	public static final int LEFT_SHIFT_HIGH_BUTTON = 13;
	public static final int LEFT_SHIFT_LOW_BUTTON = 16;
	public static final int RIGHT_SHIFT_HIGH_BUTTON = 13;
	public static final int RIGHT_SHIFT_LOW_BUTTON = 16;
	
	public static final int INTAKE_SLIDE_IN_BUTTON = 8;
	public static final int INTAKE_SLIDE_OUT_BUTTON = 6;

	public static final int INTAKE_NARROW_ORIENTATION_BUTTON = 3;
	public static final int INTAKE_FLAT_ORIENTATION_BUTTON = 4;
	
	public static final int LIFT_CLAMP_CLOSE_BUTTON = 7;
	public static final int LIFT_CLAMP_OPEN_BUTTON = 5;
	
	public static final int CUBE_START_DETECT_BUTTON = 2;
	public static final int LIFT_OVERRIDE_BUTTON = 0;
	public static final int INTAKE_BREAKBEAM_EMITTER_PORT = 3;
	
	
	public static final int SET_TRIGGER = 3;
	
	public static final int REACH_SCALE_HEIGHT = 2;
	public static final int REACH_SWITCH_HEIGHT = 1;
	public static final int ZERO_HEIGHT = 4;
	
}
