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

import org.usfirst.frc.team1024.robot.commands.AutoSwitchFront;
import org.usfirst.frc.team1024.robot.commands.DoNothing;
import org.usfirst.frc.team1024.robot.commands.DriveAndTurn;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.TurnRelative;
import org.usfirst.frc.team1024.robot.commands.auto.LeftPositionAuto;
import org.usfirst.frc.team1024.robot.commands.auto.RightPositionAuto;
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
	public static FieldConfig fieldConfig;
	public static Drivetrain drivetrain;
	public static final Sensors sensors = new Sensors();
	public static OI oi;
	public boolean isDone = false;
	
	Command m_autonomousCommand;
	SendableChooser<Command> autoChooser = new SendableChooser<>();
	SendableChooser<Command> testChooser = new SendableChooser<>();
	
	

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();
		drivetrain = new Drivetrain();
		
		drivetrain.initDashboard();
		
		drivetrain.resetMagneticEncoder();
		drivetrain.resetOpticalEncoder();
		
		
		
		
		autoChooser.addDefault("Default Do Nothing", new DoNothing());
		autoChooser.addObject("Drive And Turn", new DriveAndTurn());
		autoChooser.addObject("Right Position Auto", new RightPositionAuto());
		autoChooser.addObject("Left Position Auto", new LeftPositionAuto());
		//autoChooser.addObject("AutoSwitchFront", new AutoSwitchFront(324/2 + 5, 12 + 85.25));
		SmartDashboard.putData("Auto mode", autoChooser);
		
		
		//Testing Space
		SmartDashboard.putBoolean("Test?", false);
		SmartDashboard.putNumber("DriveStraightDistance", 0);
		SmartDashboard.putNumber("TurnRelativeDistance", 0);
		testChooser.addDefault("Default Do Nothing", new DoNothing());
		testChooser.addObject("DriveStraight", new DriveStraight(100));
		testChooser.addObject("TurnRelative", new TurnRelative(90));
		testChooser.addObject("Drive And Turn", new DriveAndTurn());
		SmartDashboard.putData(testChooser);
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
		fieldConfig = new FieldConfig(DriverStation.getInstance().getGameSpecificMessage());
		//m_autonomousCommand = autoChooser.getSelected();
		//m_autonomousCommand = testChooser.getSelected();
		m_autonomousCommand = new AutoSwitchFront(324/2 - 37, 124 + 85.25);
		// schedule the autonomous command (example)
		
		
		Robot.drivetrain.resetOpticalEncoder();
		Robot.drivetrain.resetGyro();
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
		drivetrain.outputToSmartDashboard();
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
		/*if (SmartDashboard.getBoolean("Test?", false) == true) {
			if (testChooser.getSelected() != null) {
				System.out.println(testChooser.getSelected());
				testChooser.getSelected().start();
			}
		}*/
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		drivetrain.outputToSmartDashboard();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		
	}
}
