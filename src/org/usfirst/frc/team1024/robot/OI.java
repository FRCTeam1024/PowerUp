/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.DetectCube;
import org.usfirst.frc.team1024.robot.commands.IntakeCube;
import org.usfirst.frc.team1024.robot.commands.MoveLiftToLevel;
import org.usfirst.frc.team1024.robot.commands.Drive.ShiftHigh;
import org.usfirst.frc.team1024.robot.commands.Drive.ShiftLow;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtendFlat;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtendNarrow;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeFlat;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeNarrow;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeRetract;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.DisengageAirbag;
import org.usfirst.frc.team1024.robot.commands.lift.EngageAirbag;
import org.usfirst.frc.team1024.robot.commands.lift.MoveLiftPID;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

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

	JoystickButton leftShiftHigh      = new JoystickButton(lJoy, Constants.SHIFT_HIGH_BUTTON);
	JoystickButton leftShiftLow       = new JoystickButton(lJoy, Constants.SHIFT_LOW_BUTTON);
	JoystickButton rightShiftHigh     = new JoystickButton(rJoy, Constants.SHIFT_HIGH_BUTTON);
	JoystickButton rightShiftLow      = new JoystickButton(rJoy, Constants.SHIFT_LOW_BUTTON);
	JoystickButton closeClamp 		  = new JoystickButton(logi, Constants.LIFT_CLAMP_CLOSE_BUTTON);
	JoystickButton openClamp          = new JoystickButton(logi, Constants.LIFT_CLAMP_OPEN_BUTTON);
	//JoystickButton intakeStartAcquire = new JoystickButton(logi, Constants.INTAKE_START_ACQUIRE);
	JoystickButton switchHeight   	  = new JoystickButton(logi, Constants.REACH_SWITCH_HEIGHT);
	JoystickButton zeroHeight         = new JoystickButton(logi, Constants.REACH_ZERO_HEIGHT);
	JoystickButton portalHeight       = new JoystickButton(logi, Constants.REACH_PORTAL_HEIGHT);
	JoystickButton scaleHeight		  = new JoystickButton(logi, Constants.REACH_SCALE_HEIGHT);
	JoystickButton engageAirbag       = new JoystickButton(logi, Constants.ENGAGE_AIRBAG_BUTTON);
	JoystickButton disengageAirbag    = new JoystickButton(logi, Constants.DISENGAGE_AIRBAG_BUTTON);

	
	public OI () {
		leftShiftHigh.whenPressed(new ShiftHigh());
		leftShiftLow.whenPressed(new ShiftLow());
		rightShiftHigh.whenPressed(new ShiftHigh());
		rightShiftHigh.whenPressed(new ShiftLow());
		closeClamp.whenPressed(new CloseClamp());
		openClamp.whenPressed(new OpenClamp());
		//intakeStartAcquire.whileHeld(new IntakeCube());
		portalHeight.whenPressed(new MoveLiftToLevel(Level.PORTAL));
		switchHeight.whenPressed(new MoveLiftToLevel(Level.SWITCH));
		switchHeight.whenPressed(new IntakeRetract());
		zeroHeight.whenPressed(new MoveLiftToLevel(Level.INTAKE));
		scaleHeight.whenPressed(new MoveLiftToLevel(Level.SCALE_LOSS));
		logi.dPad.down.whenPressed(new IntakeRetract());
		logi.dPad.up.whenPressed(new IntakeExtend());
		logi.dPad.left.whenPressed(new IntakeNarrow());
		logi.dPad.right.whenPressed(new IntakeFlat());
		logi.dPad.upLeft.whenPressed(new IntakeExtendNarrow());
		logi.dPad.upRight.whenPressed(new IntakeExtendFlat());
		disengageAirbag.whenPressed(new DisengageAirbag());
		engageAirbag.whenPressed(new EngageAirbag());
		
	}
	/*
	public boolean getOverrideButton() {
		return logi.getRawButton(Constants.LIFT_OVERRIDE_BUTTON);
	}*/
}
