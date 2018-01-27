/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveWithJoysticks;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends PIDSubsystem {
	private TalonSRX frontLeft  = new TalonSRX(42);
	//private TalonSRX middleLeft = new TalonSRX(1);
	private TalonSRX rearLeft = new TalonSRX(1);
	private TalonSRX frontRight = new TalonSRX(2);
	//private TalonSRX middleRight = new TalonSRX(4);
	private TalonSRX rearRight = new TalonSRX(3);
	
	private AHRS navx;
	
	public double rotateToAngleRate;
	
//	public double pidGet;
	
	//Remove these and any references when set properly
	public double turnkP = Constants.TURN_KP;
	public double turnkI = Constants.TURN_KI;
	public double turnkD = Constants.TURN_KD;
	public double turnkF = Constants.TURN_KF;
	
	public PIDController posPID;
	
	public Drivetrain() {
		super("turnPID", Constants.TURN_KP, Constants.TURN_KI, Constants.TURN_KD);
		//setFollower(middleLeft, frontLeft);
		//setFollower(middleRight, frontRight);
		setFollower(rearLeft, frontLeft);
		setFollower(rearRight, frontRight);
		navx = new AHRS(Port.kMXP);
		
		navx.setPIDSourceType(PIDSourceType.kDisplacement);
        getPIDController().setInputRange(-180,180);
        getPIDController().setContinuous();
        getPIDController().setOutputRange(-0.5,0.5);
        //getPIDController().setSetpoint(setpointInit);
        getPIDController().setAbsoluteTolerance(2);
        getPIDController().setPercentTolerance(10);
        //getPIDController().enable();
        
		
        //turnPID.setAbsoluteTolerance(Constants.NAVX_TOLERANCE);
        //turnPID.setContinuous(true);
	}
	
	public boolean isMoving() {
		return Math.abs(frontLeft.getMotorOutputPercent()) > 0.05 || Math.abs(frontRight.getMotorOutputPercent()) > 0.05;
	}
	
	public double getHeading() {
		return navx.getAngle();
	}
	
	public void prepareTurn(double angle) {
		resetGyro();
		getPIDController().setSetpoint(angle);
		getPIDController().enable();
	}
	
	public void turn(double rotatePower) {
    	drive(-rotatePower, rotatePower);
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


	public void resetGyro() {
		navx.reset();
	}

	@Override
	protected double returnPIDInput() {
		SmartDashboard.putNumber("navx pidGet", navx.pidGet());
		return navx.pidGet();
	}
	
	@Override
	protected void usePIDOutput(double output) {
		getPIDController().setP(SmartDashboard.getNumber("Turn KP", Constants.TURN_KP));
		getPIDController().setI(SmartDashboard.getNumber("Turn KI", Constants.TURN_KI));
		getPIDController().setD(SmartDashboard.getNumber("Turn KD", Constants.TURN_KD));
		turn(output);
//		pidGet = output;
	}
	/*
	public void getCenterRotationDisplacement() {
		getHeading() % 180
		
		
	}
	*/
}
