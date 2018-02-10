/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.IntakeFlat;
import org.usfirst.frc.team1024.robot.commands.IntakeNarrow;
import org.usfirst.frc.team1024.robot.commands.IntakeRetract;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	/*public final Joystick lJoy = new Joystick(0);
	public final Joystick rJoy = new Joystick(1);
	*/
	public final Joystick logi = new Joystick(2);
	JoystickButton intakeExtendButton = new JoystickButton(logi, 2);
	JoystickButton intakeRetractButton = new JoystickButton(logi, 3);
	JoystickButton intakeFlatButton = new JoystickButton(logi, 1);
	JoystickButton intakeNarrowButton = new JoystickButton(logi,4);

	
	public OI () {
		intakeExtendButton.whenPressed(new IntakeExtend());
		intakeRetractButton.whenPressed(new IntakeRetract());
		intakeFlatButton.whenPressed(new IntakeFlat());
		intakeNarrowButton.whenPressed(new IntakeNarrow());
		
		
	}
}
