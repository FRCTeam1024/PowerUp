/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.commands.DriveWithJoysticks;
import org.usfirst.frc.team1024.robot.commands.EncoderCalibrate;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	private TalonSRX frontLeft  = new TalonSRX(42);
	//private TalonSRX middleLeft = new TalonSRX(1);
	private TalonSRX rearLeft = new TalonSRX(1);
	private TalonSRX frontRight = new TalonSRX(2);
	//private TalonSRX middleRight = new TalonSRX(4);
	private TalonSRX rearRight = new TalonSRX(3);
	//public Encoder encoderMain = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	
	public PIDController posPID;
	public PIDController turnPID;
	
	public Drivetrain () {
		//setFollower(middleLeft, frontLeft);
		//setFollower(middleRight, frontRight);
		setFollower(rearLeft, frontLeft);
		setFollower(rearRight, frontRight);
		
		frontLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		frontLeft.getSensorCollection().getQuadraturePosition();
	}
	
	public void drive(double leftPower, double rightPower) {
		frontLeft.set(ControlMode.PercentOutput, -leftPower);
		frontRight.set(ControlMode.PercentOutput, rightPower);
	}
	
	public void setPositionMode() {
		frontLeft.set(ControlMode.Position, 0);
		frontRight.set(ControlMode.Position, 0);
	}
	
	public void setPercentMode() {
		frontLeft.set(ControlMode.PercentOutput, 0);
		frontRight.set(ControlMode.PercentOutput, 0);
	}
	
	public void setFollower(TalonSRX slave, TalonSRX master) {
		slave.set(ControlMode.Follower, master.getDeviceID());
	}
	
	
	public void stop() {
		frontLeft.set(ControlMode.PercentOutput, 0.0);
		frontRight.set(ControlMode.PercentOutput, 0.0);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}
	
	public void smartDash() {
		//SmartDashboard.putNumber("Encoder Value Raw", encoderMain.getRaw());
		//SmartDashboard.putData("Reset Encoder", new EncoderCalibrate());
		SmartDashboard.putNumber("Encoder Value: ", frontLeft.getSensorCollection().getQuadraturePosition());
	}
	
	public void encoderReset() {
		//encoderMain.reset();
		frontLeft.getSensorCollection().setQuadraturePosition(0, 10);
	}
	
	public double getEncoderValue() {
		return frontLeft.getSensorCollection().getQuadraturePosition();
		
	}
}
