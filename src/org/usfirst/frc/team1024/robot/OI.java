/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.DetectCube;
import org.usfirst.frc.team1024.robot.commands.OpenClamp;
import org.usfirst.frc.team1024.robot.commands.ShiftHigh;
import org.usfirst.frc.team1024.robot.commands.ShiftLow;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeFlat;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeNarrow;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeRetract;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick lJoy = new Joystick(0);
	public final Joystick rJoy = new Joystick(1);
	public final Joystick logi = new Joystick(RobotMap.LOGITECH_JOYSTICK_PORT);

	JoystickButton intakeExtendButton = new JoystickButton(logi, RobotMap.INTAKE_SLIDE_OUT_BUTTON);
	JoystickButton intakeRetractButton = new JoystickButton(logi, RobotMap.INTAKE_SLIDE_IN_BUTTON);
	JoystickButton intakeFlatButton = new JoystickButton(logi, 1);
	JoystickButton intakeNarrowButton = new JoystickButton(logi,4);
	JoystickButton leftShiftHigh = new JoystickButton(lJoy, RobotMap.LEFT_SHIFT_HIGH_BUTTON);
	JoystickButton leftShiftLow = new JoystickButton(lJoy, RobotMap.LEFT_SHIFT_LOW_BUTTON);
	JoystickButton rightShiftHigh = new JoystickButton(rJoy, RobotMap.RIGHT_SHIFT_HIGH_BUTTON);
	JoystickButton rightShiftLow = new JoystickButton(rJoy, RobotMap.RIGHT_SHIFT_LOW_BUTTON);
	JoystickButton closeClamp = new JoystickButton(logi, RobotMap.LIFT_CLAMP_CLOSE_BUTTON);
	JoystickButton openClamp = new JoystickButton(logi, RobotMap.LIFT_CLAMP_OPEN_BUTTON);
	JoystickButton intakeNarrow = new JoystickButton(logi, RobotMap.INTAKE_NARROW_ORIENTATION_BUTTON);
	JoystickButton intakeFlat = new JoystickButton(logi, RobotMap.INTAKE_FLAT_ORIENTATION_BUTTON);
	JoystickButton cubeDetecter = new JoystickButton(logi, RobotMap.CUBE_START_DETECT_BUTTON);

	
	public OI () {
		intakeExtendButton.whenPressed(new IntakeExtend());
		intakeRetractButton.whenPressed(new IntakeRetract());
		intakeFlatButton.whenPressed(new IntakeFlat());
		intakeNarrowButton.whenPressed(new IntakeNarrow());
		
		leftShiftHigh.whenPressed(new ShiftHigh());
		leftShiftLow.whenPressed(new ShiftLow());
		rightShiftHigh.whenPressed(new ShiftHigh());
		rightShiftHigh.whenPressed(new ShiftLow());
		
		closeClamp.whenPressed(new CloseClamp());
		openClamp.whenPressed(new OpenClamp());
		
		intakeNarrow.whenPressed(new IntakeNarrow());
		intakeFlat.whenPressed(new IntakeFlat());

		cubeDetecter.whenPressed(new DetectCube());
		
	}
}
