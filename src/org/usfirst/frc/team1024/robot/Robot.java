/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1024.robot.commandgroups.DriveAndTurn;
import org.usfirst.frc.team1024.robot.commands.DoNothing;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.FastCrossToScale;
import org.usfirst.frc.team1024.robot.commands.STurn;
import org.usfirst.frc.team1024.robot.commands.StJoeMatch3SpecialCondition;
import org.usfirst.frc.team1024.robot.commands.StJoeMatch53SpecialCondition;
import org.usfirst.frc.team1024.robot.commands.StraightForwardSwitch;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftCrossToRightScale;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftScaleLeftScale;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftSwitch;
import org.usfirst.frc.team1024.robot.commands.auto.middle.AutoSwitchFront;
import org.usfirst.frc.team1024.robot.commands.auto.right.CrossToLeftScale;
import org.usfirst.frc.team1024.robot.commands.auto.right.DriveToRightScaleEnd;
import org.usfirst.frc.team1024.robot.commands.auto.right.RightScaleRightScale;
import org.usfirst.frc.team1024.robot.commands.auto.right.RightSwitch;
import org.usfirst.frc.team1024.robot.commands.lift.MoveLiftPID;
import org.usfirst.frc.team1024.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1024.robot.subsystems.Lift;
import org.usfirst.frc.team1024.robot.subsystems.Intake;

public class Robot extends TimedRobot {
	public static FieldConfig fieldConfig;
	public static Drivetrain drivetrain = new Drivetrain();
	public static Lift lift = new Lift();
	public static Intake intake = new Intake();
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	public static OI oi;
	public boolean isDone = false;
	
	Command m_autonomousCommand;
	SendableChooser<String> autoChooser = new SendableChooser<String>();
	
	@Override
	public void robotInit() {
		oi = new OI();
		
		drivetrain.resetOpticalEncoder();
		lift.resetEncoder();
		
		
		/*
		autoChooser.addDefault("Default Do Nothing", "DoNothing");
		//autoChooser.addObject("Drive And Turn", new DriveAndTurn());
		autoChooser.addObject("Right Position Auto", "DriveToRightSwitch");
		
		autoChooser.addObject("Left Position Auto", "LeftPositionAuto");
		autoChooser.addObject("Fast Cross To Left Scale", "FastCrossToScale");
		autoChooser.addObject("DriveToRightScaleEnd", "DriveToRightScaleEnd");
		//autoChooser.addObject("drive straight 20", new DriveStraight(100));
		//autoChooser.addObject("drive backward 20", new DriveStraight(-100));
		//autoChooser.addObject("Go To Level", new MoveLiftPID(Level.SWITCH));
		//autoChooser.addObject("Turn 90", new TurnLeft(90));
		autoChooser.addObject("Straight Forward Switch", "StraightForwardSwitch");
		autoChooser.addObject("DriveStraight", "DriveStraight100");
		//autoChooser.addObject("Go To Intake Level", new MoveLiftPID(Level.INTAKE));
		//autoChooser.addObject("Go To Switch Level", new MoveLiftPID(Level.SWITCH));
		//autoChooser.addObject("Go To Scale Ownership Level", new MoveLiftPID(Level.SCALE_OWNERSHIP));
		//autoChooser.addObject("Go To Scale Neutral Level", new MoveLiftPID(Level.SCALE_NEUTRAL));
		//autoChooser.addObject("Go To Scale Loss Level", new MoveLiftPID(Level.SCALE_LOSS));
		autoChooser.addObject("AutoSwitchFront", "AutoSwitchFront");
		autoChooser.addObject("Cross To Left Scale", "Cross To Left Scale");
		autoChooser.addObject("Cross To Right Scale", "Cross To Right Scale");
		autoChooser.addObject("Turn Left", "Turn Left");
		autoChooser.addObject("Turn Right", "Turn Right");
		autoChooser.addObject("STurn", "STurn");
		autoChooser.addObject("right side right scale", "rightsiderightscaleScale");
		autoChooser.addObject("left side left scale", "leftsideleftscaleScale");
		SmartDashboard.putData("Auto mode", autoChooser);

		autoChooser.addObject("DriveToRightSwitch", "DriveToRightSwitch");
		autoChooser.addObject("DriveToLeftSwitch", "DriveToLeftSwitch");
		SmartDashboard.putNumber("Pos P", Constants.POS_KP);
		SmartDashboard.putNumber("Pos I", Constants.POS_KI);
		SmartDashboard.putNumber("Pos D", Constants.POS_KD);
		*/
		
		
		
		// TODO un-comment when you want to test this
		CompetitionAutoChooser.getInstance().initSmartDashboard();
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
//		String autoSelected = (String) autoChooser.getSelected();
		drivetrain.setBrake();
		lift.disengageAirBag();
		/*
		System.out.println("before with selected " + autoSelected);
		switch (autoSelected) {
			case "DriveToRightSwitch":
				m_autonomousCommand = new RightSwitch();
				break;
			case "DriveToLeftSwitch":
				m_autonomousCommand = new LeftSwitch();
				break;
			case "StraightForwardSwitch":
				m_autonomousCommand = new StraightForwardSwitch();
				break;
			case "AutoSwitchFront":
				m_autonomousCommand = new AutoSwitchFront();
				break;
			case "FastCrossToScale":
				m_autonomousCommand = new FastCrossToScale();
				break;
			case "DriveToRightScaleEnd":
				m_autonomousCommand = new DriveToRightScaleEnd();
				break;
			case "Turn Left":
				m_autonomousCommand = new TurnLeft(90, 5.0);
				break;
			case "Turn Right":
				m_autonomousCommand = new TurnRight(90, 5.0);
				break;
			case "Cross To Left Scale":
				m_autonomousCommand = new CrossToLeftScale();
				break;
			case "Cross To Right Scale":
				m_autonomousCommand = new LeftCrossToRightScale();
				break;
			case "STurn":
				m_autonomousCommand = new STurn(250.0, 140.0 - Constants.ROBOT_LENGTH_IN, -45.0, 36.0);
				break;
			case "rightsiderightscaleScale":
				m_autonomousCommand = new RightScaleRightScale();
				break;
			case "leftsideleftscaleScale":
				m_autonomousCommand = new LeftScaleLeftScale();
				break;
			default:
				m_autonomousCommand = new DoNothing();
				break;
		}
		*/
		
		// TODO when you want to try auto-chooser
		m_autonomousCommand = CompetitionAutoChooser.getInstance().chooseCommand();
		//m_autonomousCommand = new TurnLeft(45.0, 3.0);
		//m_autonomousCommand = new DriveAndShift(250, 1.0);
		//m_autonomousCommand = new StJoeMatch53SpecialCondition();
		Robot.drivetrain.resetOpticalEncoder();
		Robot.drivetrain.resetGyro();
		System.out.println("before start command");
		
		// TODO re-enable autonomous; was disabled to test chooser but didn't want to run it
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
		lift.disengageAirBag();
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
