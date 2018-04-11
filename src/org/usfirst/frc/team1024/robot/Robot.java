/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
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
import org.usfirst.frc.team1024.robot.commands.DoubleScaleCurve;
import org.usfirst.frc.team1024.robot.commands.DoubleSwitch;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.FastCrossToScale;
import org.usfirst.frc.team1024.robot.commands.LeftScaleScale;
import org.usfirst.frc.team1024.robot.commands.LeftScaleSwitch;
import org.usfirst.frc.team1024.robot.commands.PlainfieldMatch51SpecialCondition;
import org.usfirst.frc.team1024.robot.commands.RightScaleScale;
import org.usfirst.frc.team1024.robot.commands.RightScaleSwitch;
import org.usfirst.frc.team1024.robot.commands.RightSwitchSwitch;
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
	
	Command m_autonomousCommand;
	SendableChooser<String> autoChooser = new SendableChooser<String>();
	/*public static SendableChooser<String> robotPosition = new SendableChooser<String>();
	public static SendableChooser<String> opponentScale = new SendableChooser<String>();
	public static SendableChooser<String> reliableMiddleSwitch = new SendableChooser<String>();
	public static SendableChooser<String> dropCube = new SendableChooser<String>();
	public static SendableChooser<String> stayOnOurSide = new SendableChooser<String>();
	*/
	public static boolean robotPosition;
	public static boolean areWeInTheMiddle;
	public static boolean opponentScale;
	public static boolean reliableMiddleSwitch;
	public static boolean dropCube;
	public static boolean stayOnOurSide;
	@Override
	public void robotInit() {
		oi = new OI();
		SmartDashboard.putBoolean("robotPosition (Right=true)", true); //Right
		SmartDashboard.putBoolean("Are we in the middle? (Right=true)", false);
		SmartDashboard.putBoolean("Opponent scale? (Yes=true)", true);
		SmartDashboard.putBoolean("Reliable middle switch robot? (Yes=true)", false);
		SmartDashboard.putBoolean("Drop second cube? (Yes=true)", false);
		SmartDashboard.putBoolean("Stay on our side? (yes=true)", false);
		
		try {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(160, 120);
			camera.setExposureManual(50);
			camera.setBrightness(50);
			camera.setWhiteBalanceManual(255);
			
			camera.setFPS(30);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		drivetrain.resetOpticalEncoder();
		lift.resetEncoder();
		
		autoChooser.addDefault("Cross", "RightCrossLeft");
		autoChooser.addObject("Middle Switch", "MiddleSwitch");
		autoChooser.addObject("Right Scale Side", "RightScaleSide");
		autoChooser.addObject("Right Switch", "RightSwitch");
		autoChooser.addObject("R Switch First", "RSwitchPriority");
		autoChooser.addObject("R Scale First", "RScalePriority");
		autoChooser.addObject("Left Scale", "LeftScale");
		autoChooser.addObject("Left Switch", "LeftSwitch");
		autoChooser.addObject("L Switch First", "LSwitchPriority");
		autoChooser.addObject("L Scale First", "LScalePriority");
		autoChooser.addObject("ScaleEither", "ScaleEither");
		autoChooser.addObject("DoubleScale", "DoubleScale");
		autoChooser.addObject("Double Scale Curve", "DoubleScaleCurve");
		autoChooser.addObject("DoubleSwitch", "DoubleSwitch");
		autoChooser.addObject("New Chooser", "NewChooser");
		autoChooser.addObject("Test", "Test");
		autoChooser.addObject("RightScaleSwitch", "RightScaleSwitch");
		autoChooser.addObject("RightScaleScale", "RightScaleScale");
		autoChooser.addObject("LeftScaleScale", "LeftScaleScale");
		autoChooser.addObject("LeftScaleSwitch", "LeftScaleSwitch");
		SmartDashboard.putData("Auto Options", autoChooser);
		/*
		robotPosition.addObject("Robot Position", "Right");
		robotPosition.addDefault("Right", "Right");
		robotPosition.addObject("Left", "Left");
		robotPosition.addObject("Middle", "Middle");
		SmartDashboard.putData("RobotPosition", robotPosition);

		opponentScale.addDefault("Yes", "Yes");
		opponentScale.addObject("Scale Robot?", "Yes");
		opponentScale.addObject("No", "No");
		SmartDashboard.putData("Opponent Scale?", opponentScale);
		
		reliableMiddleSwitch.addDefault("No", "No");
		reliableMiddleSwitch.addObject("Switch Robot?", "No");
		reliableMiddleSwitch.addObject("Yes", "Yes");
		SmartDashboard.putData("Switch Robot?", reliableMiddleSwitch);
		
		dropCube.addObject("No", "No");
		dropCube.addObject("Drop Cube?", "No");
		dropCube.addDefault("Yes", "Yes");
		SmartDashboard.putData("Drop Cube?", dropCube);
		
		stayOnOurSide.addDefault("No", "No");
		stayOnOurSide.addObject("Our Side Only?", "No");
		stayOnOurSide.addObject("Yes", "Yes");
		SmartDashboard.putData("Our Side Only?", stayOnOurSide);
		*/
		
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
		
		robotPosition = SmartDashboard.getBoolean("robotPosition (Right=true)", true); //Right
		areWeInTheMiddle = SmartDashboard.getBoolean("Are we in the middle? (Right=true)", false);
		opponentScale = SmartDashboard.getBoolean("Opponent scale? (Yes=true)", true);
		reliableMiddleSwitch = SmartDashboard.getBoolean("Reliable middle switch robot? (Yes=true)", false);
		dropCube = SmartDashboard.getBoolean("Drop second cube? (Yes=true)", false);
		stayOnOurSide = SmartDashboard.getBoolean("Stay on our side? (yes=true)", false);
		
		
		System.out.println("before with selected " + autoSelected);
		switch (autoSelected) {
			default:
				m_autonomousCommand = new CrossTest();
				break;
			case "MiddleSwitch":
				m_autonomousCommand = new MiddleSwitchMiddleSwitch();
				break;
			case "RightScaleSide":
				m_autonomousCommand = new RightScaleRightScale();
				break;
			case "RightSwitch":
				m_autonomousCommand = new RightSwitch();
				break;
			case "RSwitchPriority": //Old Scale Method
				m_autonomousCommand = new StJoeMatch53SpecialCondition();
				break;
			case "RScalePriority": //Old Scale Method
				m_autonomousCommand = new PlainfieldMatch51SpecialCondition();
				break;
			case "ScaleEither": //Old Scale Method
				m_autonomousCommand = new ScaleEither();
				break;
			case "DoubleScale": //New Scale Method
				m_autonomousCommand = new DoubleScaleZane();
				break;
			case "DoubleScaleCurve":
				m_autonomousCommand = new DoubleScaleCurve();
				break;
			case "DoubleSwitch":
				m_autonomousCommand = new RightSwitchSwitch();
				break;
			case "RightScaleSwitch":
				m_autonomousCommand = new RightScaleSwitch();
				break;
			case "NewChooser":
				m_autonomousCommand = new BinaryChooser().chooseAuto();
				break;
			case "RightScaleScale":
				m_autonomousCommand = new RightScaleScale();
				break;
			case "LeftScaleScale":
				m_autonomousCommand = new LeftScaleScale();
				break;
			case "LeftScaleSwitch":
				m_autonomousCommand = new LeftScaleSwitch();
				break;
			case "Test":
				m_autonomousCommand = new CrossToLeftScale();
				break;
		}
		
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
