/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.commands.EncoderCalibrate;

import org.usfirst.frc.team1024.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
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
		frontRight.setInverted(false);
		rearRight.setInverted(false);
		//setFollower(middleLeft, frontLeft);
		//setFollower(middleRight, frontRight);
		setFollower(rearLeft, frontLeft);
		setFollower(rearRight, frontRight);
		

		frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		//frontRight.setSensorPhase(true);
		
		frontRight.configNominalOutputForward(0, 10);
        frontRight.configNominalOutputReverse(0, 10);
        frontRight.configPeakOutputForward(1, 10);
        frontRight.configPeakOutputReverse(-1, 10);
        /* set the allowable closed-loop error,
         * Closed-Loop output will be neutral within this range.
         * See Table in Section 17.2.1 for native units per rotation. 
         */
        //frontRight.configAllowableClosedloopError(0, 0, 10); /* always servo */
        /* set closed loop gains in slot0 */
        frontRight.config_kF(0, 0.0, 10);
        frontRight.config_kP(0, 1.0, 10);
        frontRight.config_kI(0, 0.0, 10);
        frontRight.config_kD(0, 0.0, 10);
		
        
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
	
	public double getEncoderValue() {
		return frontLeft.getSensorCollection().getQuadraturePosition();
	}
	
	public double getRawEncoder() {
		return frontRight.getSelectedSensorPosition(0);
	}
	
	public double getRawQuad() {
		return frontRight.getSensorCollection().getQuadraturePosition();
	}
	
	public double getWheelRotation() {
		return getRawEncoder() / 3;
	}
	
	public double getDistanceInches() {
		//getWheelRotation() = distance / (Math.PI * Constants.WHEEL_DIAMETER) * Constants.ENCODER_COUNTS_PER_REVOLUTION;
		return (((getRawEncoder() / Constants.ENCODER_TO_WHEEL_RATIO * Math.PI * Constants.WHEEL_DIAMETER) / 
				Constants.ENCODER_COUNTS_PER_REVOLUTION)/ 4) * 3 * 3;
	}
	
	public double getTicks(double distanceInInches) {
//		return (distance * 9 * Constants.ENCODER_COUNTS_PER_REVOLUTION * Constants.ENCODER_RATIO_TO_WHEEL * 
//				Math.PI * Constants.WHEEL_DIAMETER) / 12;
		
		int ticksPerInch = 71;
		return -1*(ticksPerInch * distanceInInches);
	} 
	
	public void resetEncoder() {
		frontRight.getSensorCollection().setQuadraturePosition(0, 0);
	}
	
	public boolean isMoving() {
		if (frontRight.getMotorOutputPercent() > 0.1) {
			return true;
		} else {
			return false;
		}
	}
	
	public void driveDistance(double inches) {
		double ticks = getTicks(inches);
		System.out.println("num Ticks for " + inches + " inches : " + ticks);
		frontRight.set(ControlMode.Position, ticks);
		frontLeft.set(ControlMode.PercentOutput, -1*frontRight.getMotorOutputPercent());
//		frontRight.set(ControlMode.Position, -3000);
	}
}
	