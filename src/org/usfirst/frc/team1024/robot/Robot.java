/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1024.robot.commands.Auto;
import org.usfirst.frc.team1024.robot.commands.EncoderCalibrate;
import org.usfirst.frc.team1024.robot.commands.ResetEncoder;
import org.usfirst.frc.team1024.robot.commands.Turn;
import org.usfirst.frc.team1024.robot.commands.CrossLine;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1024.robot.subsystems.Sensors;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Sensors sensors = new Sensors();
	public static OI oi;

	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	CrossLine crossLine = new CrossLine(120);
	DriveStraight driveStraight = new DriveStraight(120);
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		m_chooser.addDefault("Default Auto", new Auto());
		// chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", m_chooser);

		//SmartDashboard.putData("Encoder Calibrate", new EncoderCalibrate());
		
		SmartDashboard.putData("Reset Encoder", new ResetEncoder());
		SmartDashboard.putData("Cross Line", crossLine);
		SmartDashboard.putNumber("Drive Straight Distance", 100);
		SmartDashboard.putNumber("Drive Straight Power", 0.6);
		SmartDashboard.putData("Drive Straight", driveStraight);


		//SmartDashboard.putNumber("Raw Ultrasonic", sensors.getRawUltrasonic());
		//SmartDashboard.putNumber("Ultrasonic Distance In Inches", sensors.getDistanceInches());

	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//m_autonomousCommand = m_chooser.getSelected();
		m_autonomousCommand = new CrossLine(120);
		
//		m_autonomousCommand = new Turn(90);
		
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		
		drivetrain.smartDash();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		driveStraight.setPower(0.6);
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
//		driveStraight.setDistance(SmartDashboard.getNumber("Drive Straight Distance", 0));
//		driveStraight.setPower(SmartDashboard.getNumber("Drive Straight Power", 0));
		SmartDashboard.putNumber("Left Power:", Robot.drivetrain.leftOutput());
		SmartDashboard.putNumber("Right Power:", Robot.drivetrain.rightOutput());	
		Robot.drivetrain.smartDash();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
