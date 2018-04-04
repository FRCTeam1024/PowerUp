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
import org.usfirst.frc.team1024.robot.commands.CrossTest;
import org.usfirst.frc.team1024.robot.commands.DoNothing;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.FastCrossToScale;
import org.usfirst.frc.team1024.robot.commands.PlainfieldMatch51SpecialCondition;
import org.usfirst.frc.team1024.robot.commands.STurn;
import org.usfirst.frc.team1024.robot.commands.ScaleEither;
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
import org.usfirst.frc.team1024.robot.commands.auto.middle.MiddleSwitchMiddleSwitch;
import org.usfirst.frc.team1024.robot.commands.auto.right.CrossToLeftScale;
import org.usfirst.frc.team1024.robot.commands.auto.right.DoubleScaleZane;
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
		//middle switch
		//Right switch
		//Right scale
		//Right switch (if on our side) or scale
		//Right cross to left scale
		autoChooser.addDefault("Cross", "RightCrossLeft");
		autoChooser.addObject("Middle Switch", "MiddleSwitch");
		autoChooser.addObject("Right Scale", "RightScale");
		autoChooser.addObject("Right Switch", "RightSwitch");
		autoChooser.addObject("R Switch First", "RSwitchPriority");
		autoChooser.addObject("R Scale First", "RScalePriority");
		autoChooser.addObject("Left Scale", "LeftScale");
		autoChooser.addObject("Left Switch", "LeftSwitch");
		autoChooser.addObject("L Switch First", "LSwitchPriority");
		autoChooser.addObject("L Scale First", "LScalePriority");
		autoChooser.addObject("Test", "Test");
		autoChooser.addObject("ScaleEither", "ScaleEither");
		autoChooser.addObject("DoubleScaleZane", "DoubleScaleZane");
		SmartDashboard.putData(autoChooser);
		
		// TODO un-comment when you want to test this
		//CompetitionAutoChooser.getInstance().initSmartDashboard();
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
		String autoSelected = (String) autoChooser.getSelected();
		drivetrain.setBrake();
		lift.disengageAirBag();
		
		System.out.println("before with selected " + autoSelected);
		switch (autoSelected) {
			default:
				m_autonomousCommand = new CrossTest();
				break;
			case "MiddleSwitch":
				m_autonomousCommand = new MiddleSwitchMiddleSwitch();
				break;
			case "RightScale":
				m_autonomousCommand = new RightScaleRightScale();
				break;
			case "RightSwitch":
				m_autonomousCommand = new RightSwitch();
				break;
			case "SwitchPriority":
				m_autonomousCommand = new StJoeMatch53SpecialCondition();
				break;
			case "ScalePriority":
				m_autonomousCommand = new PlainfieldMatch51SpecialCondition();
				break;
			case "ScaleEither":
				m_autonomousCommand = new ScaleEither();
				break;
			case "RightCrossLeft":
				m_autonomousCommand = new CrossToLeftScale();
				break;
			case "DoubleScaleZane":
				m_autonomousCommand = new DoubleScaleZane();
				break;
		}
		
		
		// TODO when you want to try auto-chooser
		//m_autonomousCommand = CompetitionAutoChooser.getInstance().chooseCommand();
		//m_autonomousCommand = new DriveStraight(60.0, 5.0);
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
