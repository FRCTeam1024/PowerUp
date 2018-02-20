/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.DetectCube;
import org.usfirst.frc.team1024.robot.commands.IntakeExtendFlat;
import org.usfirst.frc.team1024.robot.commands.IntakeExtendNarrow;
import org.usfirst.frc.team1024.robot.commands.MoveLiftPID;
import org.usfirst.frc.team1024.robot.commands.OpenClamp;
import org.usfirst.frc.team1024.robot.commands.ShiftHigh;
import org.usfirst.frc.team1024.robot.commands.ShiftLow;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeFlat;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeNarrow;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeRetract;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.buttons.Trigger.ButtonScheduler;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	public final Joystick lJoy = new Joystick(Constants.LEFT_JOYSTICK_PORT);
	public final Joystick rJoy = new Joystick(Constants.RIGHT_JOYSTICK_PORT);
	public final Logitech logi = new Logitech(Constants.LOGITECH_JOYSTICK_PORT);

	//JoystickButton intakeExtend   = new JoystickButton(logi, Constants.INTAKE_SLIDE_OUT_BUTTON);
	//JoystickButton intakeRetract  = new JoystickButton(logi, Constants.INTAKE_SLIDE_IN_BUTTON);
	JoystickButton leftShiftHigh  = new JoystickButton(lJoy, Constants.SHIFT_HIGH_BUTTON);
	JoystickButton leftShiftLow   = new JoystickButton(lJoy, Constants.SHIFT_LOW_BUTTON);
	JoystickButton rightShiftHigh = new JoystickButton(rJoy, Constants.SHIFT_HIGH_BUTTON);
	JoystickButton rightShiftLow  = new JoystickButton(rJoy, Constants.SHIFT_LOW_BUTTON);
	JoystickButton closeClamp     = new JoystickButton(logi, Constants.LIFT_CLAMP_CLOSE_BUTTON);
	JoystickButton openClamp      = new JoystickButton(logi, Constants.LIFT_CLAMP_OPEN_BUTTON);
	//JoystickButton intakeNarrow   = new JoystickButton(logi, Constants.INTAKE_NARROW_ORIENTATION_BUTTON);
	//JoystickButton intakeFlat     = new JoystickButton(logi, Constants.INTAKE_FLAT_ORIENTATION_BUTTON);
	JoystickButton cubeDetecter   = new JoystickButton(logi, Constants.CUBE_START_DETECT_BUTTON);
	
	JoystickButton setTrigger     = new JoystickButton(logi, Constants.SET_TRIGGER);
	JoystickButton scaleHeight    = new JoystickButton(logi, Constants.REACH_SCALE_HEIGHT);
	JoystickButton switchHeight   = new JoystickButton(logi, Constants.REACH_SWITCH_HEIGHT);
	JoystickButton zeroHeight     = new JoystickButton(logi, Constants.ZERO_HEIGHT);
	public OI () {
		//intakeExtend.whenPressed(new IntakeExtend());
		//intakeRetract.whenPressed(new IntakeRetract());
		leftShiftHigh.whenPressed(new ShiftHigh());
		leftShiftLow.whenPressed(new ShiftLow());
		rightShiftHigh.whenPressed(new ShiftHigh());
		rightShiftHigh.whenPressed(new ShiftLow());
		closeClamp.whenPressed(new CloseClamp());
		openClamp.whenPressed(new OpenClamp());
		
		//intakeNarrow.whenPressed(new IntakeNarrow());
		//intakeFlat.whenPressed(new IntakeFlat());

		cubeDetecter.whenPressed(new DetectCube());
		
		scaleHeight.whenPressed(new MoveLiftPID(Level.SCALE_LOSS));
		switchHeight.whenPressed(new MoveLiftPID(Level.SWITCH));
		zeroHeight.whenPressed(new MoveLiftPID(Level.INTAKE));

		cubeDetecter.whenPressed(new DetectCube());	
		
		logi.dPad.down.whenPressed(new IntakeRetract());
		logi.dPad.up.whenPressed(new IntakeExtend());
		logi.dPad.left.whenPressed(new IntakeFlat());
		logi.dPad.right.whenPressed(new IntakeNarrow());
		logi.dPad.upLeft.whenPressed(new IntakeExtendFlat());
		logi.dPad.upRight.whenPressed(new IntakeExtendNarrow());
	}
	
	public boolean getOverrideButton() {
		return logi.getRawButton(Constants.LIFT_OVERRIDE_BUTTON);
	}
}
