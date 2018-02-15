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

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public final Joystick lJoy = new Joystick(0);
	public final Joystick rJoy = new Joystick(1);
	public final Joystick logi = new Joystick(Constants.LOGITECH_JOYSTICK_PORT);

	JoystickButton intakeExtend = new JoystickButton(logi, Constants.INTAKE_SLIDE_OUT_BUTTON);
	JoystickButton intakeRetract = new JoystickButton(logi, Constants.INTAKE_SLIDE_IN_BUTTON);
	JoystickButton leftShiftHigh = new JoystickButton(lJoy, Constants.SHIFT_HIGH_BUTTON);
	JoystickButton leftShiftLow = new JoystickButton(lJoy, Constants.SHIFT_LOW_BUTTON);
	JoystickButton rightShiftHigh = new JoystickButton(rJoy, Constants.SHIFT_HIGH_BUTTON);
	JoystickButton rightShiftLow = new JoystickButton(rJoy, Constants.SHIFT_LOW_BUTTON);
	JoystickButton closeClamp = new JoystickButton(logi, Constants.LIFT_CLAMP_CLOSE_BUTTON);
	JoystickButton openClamp = new JoystickButton(logi, Constants.LIFT_CLAMP_OPEN_BUTTON);
	JoystickButton intakeNarrow = new JoystickButton(logi, Constants.INTAKE_NARROW_ORIENTATION_BUTTON);
	JoystickButton intakeFlat = new JoystickButton(logi, Constants.INTAKE_FLAT_ORIENTATION_BUTTON);
	JoystickButton cubeDetecter = new JoystickButton(logi, Constants.CUBE_START_DETECT_BUTTON);

	public OI () {
		intakeExtend.whenPressed(new IntakeExtend());
		intakeRetract.whenPressed(new IntakeRetract());
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
