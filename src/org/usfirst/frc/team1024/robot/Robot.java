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
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1024.robot.commands.Auto;
import org.usfirst.frc.team1024.robot.commands.CrossLine;
import org.usfirst.frc.team1024.robot.commands.DriveAndTurn;
import org.usfirst.frc.team1024.robot.commands.TurnToAngle;
import org.usfirst.frc.team1024.robot.commands.DriveDistance;
import org.usfirst.frc.team1024.robot.commands.ResetEncoder;
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
	public static Drivetrain drivetrain;
	public static final Sensors sensors = new Sensors();
	public static OI oi;
	public boolean isDone = false;
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		drivetrain = new Drivetrain();
		m_chooser.addDefault("Default Auto", new Auto());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putNumber("Raw Ultrasonic", sensors.getRawUltrasonic());
		SmartDashboard.putNumber("Ultrasonic Distance In Inches", sensors.getDistanceInches());
		SmartDashboard.putNumber("Turn KP", Robot.drivetrain.turnkP);
		SmartDashboard.putNumber("Turn KI", Robot.drivetrain.turnkI);
		SmartDashboard.putNumber("Turn KD", Robot.drivetrain.turnkD);
		SmartDashboard.putNumber("Turn KF", Robot.drivetrain.turnkF);
		SmartDashboard.putNumber("Turn Setpoint", 0);
		SmartDashboard.putNumber("Raw Encoder", drivetrain.getRawEncoder());
		SmartDashboard.putNumber("Encoder Distance (In.)", drivetrain.getDistanceInches());
		drivetrain.resetEncoder();
		SmartDashboard.putData("Reset Encoder", new ResetEncoder());
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
		
		// try to drive 1 revolution of wheel
		double inchesPerRevolution = 19.24;
		m_autonomousCommand = new DriveAndTurn();
		
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
		if (isDone != true) {
			SmartDashboard.putNumber("Raw Encoder", drivetrain.getRawEncoder());
			SmartDashboard.putNumber("Encoder Distance (In.)", drivetrain.getDistanceInches());
			SmartDashboard.putNumber("Raw Encoder Quad", drivetrain.getRawQuad());
			SmartDashboard.putBoolean("isMoving", drivetrain.isMoving());
//			Robot.drivetrain.driveDistance(12);
		}
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
	}
	
	private void log(String msg) {
		System.out.println(msg);
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		log("In teleopPeriodic");
		Scheduler.getInstance().run();
		SmartDashboard.putData("Turn to Angle", new TurnToAngle(/*SmartDashboard.getNumber("Turn Setpoint", 0)*/ 90));
		SmartDashboard.putNumber("Angle", Robot.drivetrain.getHeading());
		SmartDashboard.putNumber("Raw Encoder", drivetrain.getRawEncoder());
		SmartDashboard.putNumber("Encoder Distance (In.)", drivetrain.getDistanceInches());
		SmartDashboard.putNumber("Raw Encoder Quad", drivetrain.getRawQuad());
		SmartDashboard.putData("Reset Encoder", new ResetEncoder());
		SmartDashboard.putBoolean("isMoving", drivetrain.isMoving());
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
