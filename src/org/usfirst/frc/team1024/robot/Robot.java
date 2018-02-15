/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1024.robot.commandgroups.DriveAndTurn;
import org.usfirst.frc.team1024.robot.commands.DoNothing;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.MoveLiftPID;
import org.usfirst.frc.team1024.robot.commands.StraightForwardSwitch;
import org.usfirst.frc.team1024.robot.commands.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftPositionAuto;
import org.usfirst.frc.team1024.robot.commands.auto.right.DriveToRightSwitch;
import org.usfirst.frc.team1024.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1024.robot.subsystems.Lift;
import org.usfirst.frc.team1024.robot.subsystems.Intake;

public class Robot extends TimedRobot {
	public static FieldConfig fieldConfig;
	public static Drivetrain drivetrain = new Drivetrain();
	public static Lift lift = new Lift();
	public static Intake intake = new Intake();
	public static OI oi;
	public boolean isDone = false;
	
	Command m_autonomousCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();
	
	@Override
	public void robotInit() {
		oi = new OI();
		
		drivetrain.resetOpticalEncoder();
		lift.resetEncoder();
		
		autoChooser.addDefault("Default Do Nothing", new DoNothing());
		autoChooser.addObject("Drive And Turn", new DriveAndTurn());
		autoChooser.addObject("Right Position Auto", new DriveToRightSwitch());
		autoChooser.addObject("Left Position Auto", new LeftPositionAuto());
		autoChooser.addObject("drive straight 20", new DriveStraight(100));
		autoChooser.addObject("drive backward 20", new DriveStraight(-100));
		autoChooser.addObject("Go To Level", new MoveLiftPID(Level.SWITCH));
		autoChooser.addObject("Turn 90", new TurnLeft(90));
		autoChooser.addObject("Straight Forward Switch", new StraightForwardSwitch());
		autoChooser.addObject("Go To Intake Level", new MoveLiftPID(Level.INTAKE));
		autoChooser.addObject("Go To Switch Level", new MoveLiftPID(Level.SWITCH));
		autoChooser.addObject("Go To Scale Ownership Level", new MoveLiftPID(Level.SCALE_OWNERSHIP));
		autoChooser.addObject("Go To Scale Neutral Level", new MoveLiftPID(Level.SCALE_NEUTRAL));
		autoChooser.addObject("Go To Scale Loss Level", new MoveLiftPID(Level.SCALE_LOSS));
		SmartDashboard.putData("Auto mode", autoChooser);
//autoChooser.addObject("AutoSwitchFront", new AutoSwitchFront(324/2 + 5, 12 + 85.25));
	}
	
	@Override
	public void disabledInit() {
		drivetrain.setCoast();
		intake.posIn();
		intake.slideIn();
		lift.clamp(false);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		drivetrain.outputToSmartDashboard();
		lift.outputToSmartDashboard();
		intake.outputToSmartDashboard();
	}
	
	@Override
	public void autonomousInit() {
		fieldConfig = new FieldConfig(DriverStation.getInstance().getGameSpecificMessage());
		m_autonomousCommand = autoChooser.getSelected();
		drivetrain.setBrake();
		//m_autonomousCommand = new AutoSwitchFront(324/2 + 134-27.5, 124 + 85.25);
		
		Robot.drivetrain.resetOpticalEncoder();
		Robot.drivetrain.resetGyro();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}
	
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		drivetrain.outputToSmartDashboard();
		lift.outputToSmartDashboard();
		intake.outputToSmartDashboard();
	}

	@Override
	public void teleopInit() {
		drivetrain.setBrake();
		// This makes sure that the autonomous stops running when teleop starts running. If you want the autonomous to continue until interrupted by another command, remove this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		drivetrain.outputToSmartDashboard();
		lift.outputToSmartDashboard();
		intake.outputToSmartDashboard();
	}
	
	@Override
	public void testPeriodic() {
		
	}
}
