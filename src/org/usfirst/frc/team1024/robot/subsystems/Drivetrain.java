/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.commands.DriveWithJoysticks;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	private TalonSRX frontLeft  = new TalonSRX(0);
	//private TalonSRX middleLeft = new TalonSRX(1);
	private TalonSRX rearLeft = new TalonSRX(2);
	private TalonSRX frontRight = new TalonSRX(3);
	//private TalonSRX middleRight = new TalonSRX(4);
	private TalonSRX rearRight = new TalonSRX(5);
	
	public PIDController posPID;
	public PIDController turnPID;
	
	public Drivetrain () {
		//setFollower(middleLeft, frontLeft);
		//setFollower(middleRight, frontRight);
		setFollower(rearLeft, frontLeft);
		setFollower(rearRight, frontRight);
	}
	
	public void drive(double leftPower, double rightPower) {
		frontLeft.set(ControlMode.PercentOutput, leftPower);
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
}
