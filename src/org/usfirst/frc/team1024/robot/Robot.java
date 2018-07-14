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
import edu.wpi.first.wpilibj.Relay;
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
import org.usfirst.frc.team1024.robot.commands.LeftSwitchSwitch;
import org.usfirst.frc.team1024.robot.commands.PlainfieldMatch51SpecialCondition;
import org.usfirst.frc.team1024.robot.commands.RightScaleScale;
import org.usfirst.frc.team1024.robot.commands.RightScaleSwitch;
import org.usfirst.frc.team1024.robot.commands.RightSwitchSwitch;
import org.usfirst.frc.team1024.robot.commands.STurn;
import org.usfirst.frc.team1024.robot.commands.ScaleEither;
import org.usfirst.frc.team1024.robot.commands.SemiFinalsSpecial;
import org.usfirst.frc.team1024.robot.commands.StJoeMatch3SpecialCondition;
import org.usfirst.frc.team1024.robot.commands.StJoeMatch53SpecialCondition;
import org.usfirst.frc.team1024.robot.commands.StraightForwardSwitch;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftCrossToRightScale;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftScaleEnd;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftScaleLeftScale;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftSwitch;
import org.usfirst.frc.team1024.robot.commands.auto.middle.AutoSwitchFront;
import org.usfirst.frc.team1024.robot.commands.auto.middle.MiddleSwitchMiddleSwitch;
import org.usfirst.frc.team1024.robot.commands.auto.right.CrossToLeftScale;
import org.usfirst.frc.team1024.robot.commands.auto.right.DoubleScaleZane;
import org.usfirst.frc.team1024.robot.commands.auto.right.DriveToRightScaleEnd;
import org.usfirst.frc.team1024.robot.commands.auto.right.RightScaleEnd;
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
		
		SmartDashboard.putBoolean("Robot on Right (check box), on Left (no check)", true); //Right
		SmartDashboard.putBoolean("1024 is Middle Robot (yes = check box)", false);
		SmartDashboard.putBoolean("Does oppossing alliance scale in auto? (yes = check box)", true);
		SmartDashboard.putBoolean("We have a reliable middle switch robot? (Yes = check box)", true);
		SmartDashboard.putBoolean("Drop second cube? (Yes = check box)", false);
		SmartDashboard.putBoolean("Stay on our side of field? (yes = check box)", false);
		
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
		
//		autoChooser.addDefault("Use Decision Matrix", "DecisionMatrix");
		autoChooser.addObject("Cross", "RightCrossLeft");
		autoChooser.addDefault("Middle Switch", "MiddleSwitch");
		autoChooser.addObject("Right Scale Side", "RightScaleSide");
		autoChooser.addObject("Right Switch", "RightSwitch");
		autoChooser.addObject("R Switch First", "RSwitchPriority");
		autoChooser.addObject("R Scale First", "RScalePriority");
//		autoChooser.addObject("Left Scale", "LeftScale");
//		autoChooser.addObject("Left Switch", "LeftSwitch");
//		autoChooser.addObject("L Switch First", "LSwitchPriority");
//		autoChooser.addObject("L Scale First", "LScalePriority");
		autoChooser.addObject("ScaleEither", "ScaleEither");
		autoChooser.addObject("DoubleScale", "DoubleScale");
//		autoChooser.addObject("Double Scale Curve", "DoubleScaleCurve");
		autoChooser.addObject("DoubleSwitch", "DoubleSwitch");
		autoChooser.addObject("CrossToLeftScale", "CrossToLeftScale");
		autoChooser.addObject("RightScaleSwitch", "RightScaleSwitch");
		autoChooser.addObject("RightScaleScale", "RightScaleScale");
		autoChooser.addObject("LeftScaleScale", "LeftScaleScale");
		autoChooser.addObject("LeftScaleSwitch", "LeftScaleSwitch");
		autoChooser.addObject("LeftSwitch", "LeftSwitchSwitch");
		autoChooser.addObject("Test", "Test");
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
		intake.cubeLight.set(Relay.Value.kForward);
		
	}
	
	@Override
	public void disabledInit() {
		drivetrain.setCoast();
		intake.setCubeLight();
		intake.posIn();
		intake.slideIn();
		lift.clamp(false);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		intake.setCubeLight();
		drivetrain.outputToSmartDashboard();
		lift.outputToSmartDashboard();
		intake.outputToSmartDashboard();
	}
	
	@Override
	public void autonomousInit() {
		fieldConfig = new FieldConfig(DriverStation.getInstance().getGameSpecificMessage());
		//fieldConfig = new FieldConfig("RRR");
		String autoSelected = (String) autoChooser.getSelected();
		drivetrain.setBrake();
		lift.disengageAirBag();
		
		robotPosition = SmartDashboard.getBoolean("Robot on Right (check box), on Left (no check)", true); //Right
		areWeInTheMiddle = SmartDashboard.getBoolean("1024 is Middle Robot (yes = check box)", false);
		opponentScale = SmartDashboard.getBoolean("Does oppossing alliance scale in auto? (yes = check box)", true);
		reliableMiddleSwitch = SmartDashboard.getBoolean("We have a reliable middle switch robot? (Yes = check box)", false);
		dropCube = SmartDashboard.getBoolean("Drop second cube? (Yes = check box)", false);
		stayOnOurSide = SmartDashboard.getBoolean("Stay on our side of field? (yes = check box)", false);
		
		
		System.out.println("before with selected " + autoSelected);
		switch (autoSelected) {
			default:
				m_autonomousCommand = new CrossTest();
				break;
			case "MiddleSwitch":
				m_autonomousCommand = new MiddleSwitchMiddleSwitch();
				break;
			case "RightScaleSide":
				m_autonomousCommand = new RightScaleEnd(); //doesn't look at field config, just does right scale 2 cube
				break;
			case "RightSwitch":
				m_autonomousCommand = new RightSwitch();
				break;
			case "RSwitchPriority": //Old Scale Method
				m_autonomousCommand = new StJoeMatch53SpecialCondition();
				break;
			case "RScalePriority": 
				m_autonomousCommand = new PlainfieldMatch51SpecialCondition();
				break;
			case "DoubleSwitch":
				m_autonomousCommand = new RightSwitchSwitch();
				break;
			case "RightScaleSwitch":
				m_autonomousCommand = new RightScaleSwitch();
				break;
			case "DecisionMatrix":
				m_autonomousCommand = new BinaryChooser().chooseAuto();
				break;
			case "RightScaleScale":
				m_autonomousCommand = new RightScaleScale(); // takes scale side field config into choice
				break;
			case "LeftScaleScale":
				m_autonomousCommand = new LeftScaleScale();
				break;
			case "LeftScaleSwitch":
				m_autonomousCommand = new LeftScaleSwitch();
				break;
			case "CrossToLeftScale":
				m_autonomousCommand = new CrossToLeftScale();
				break;
			case "LeftSwitchSwitch":
				m_autonomousCommand = new LeftSwitchSwitch();
				break;
			case "Test":
				m_autonomousCommand = new LeftScaleEnd();
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
		intake.setCubeLight();
		drivetrain.outputToSmartDashboard();
		lift.outputToSmartDashboard();
		intake.outputToSmartDashboard();
	}

	@Override
	public void teleopInit() {
		drivetrain.setBrake();
		lift.disengageAirBag();
		intake.setCubeLight();
		// This makes sure that the autonomous stops running when teleop starts running. If you want the autonomous to continue until interrupted by another command, remove this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		intake.cubeLight.set(Relay.Value.kForward);
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		intake.setCubeLight();
		drivetrain.outputToSmartDashboard();
		lift.outputToSmartDashboard();
		intake.outputToSmartDashboard();
		intake.cubeLight.set(Relay.Value.kForward);
	}
	
	@Override
	public void testPeriodic() {
		
	}
}
