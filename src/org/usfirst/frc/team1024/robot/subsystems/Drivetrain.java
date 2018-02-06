/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.RobotMap;
import org.usfirst.frc.team1024.robot.commands.ResetEncoder;

import com.ctre.phoenix.ParamEnum;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	private TalonSRX frontLeft  = new TalonSRX(RobotMap.FRONT_LEFT_MOTOR_PORT);
	//private TalonSRX middleLeft = new TalonSRX(RobotMap.MIDDLE_LEFT_MOTOR_PORT);
	private TalonSRX rearLeft = new TalonSRX(RobotMap.REAR_LEFT_MOTOR_PORT);
	private TalonSRX frontRight = new TalonSRX(RobotMap.FRONT_RIGHT_MOTOR_PORT);
	//private TalonSRX middleRight = new TalonSRX(RobotMap.MIDDLE_RIGHT_MOTOR_PORT);
	private TalonSRX rearRight = new TalonSRX(RobotMap.REAR_RIGHT_MOTOR_PORT);
	
	
	private AHRS navx;
	
	public double rotateToAngleRate;
	
//	public double pidGet;
	
	//Remove these and any references when set properly
	public double turnkP = Constants.TURN_KP;
	public double turnkI = Constants.TURN_KI;
	public double turnkD = Constants.TURN_KD;
	public double turnkF = Constants.TURN_KF;
	
	public Encoder encoder = new Encoder(RobotMap.ENCODER_CHANNEL_A, RobotMap.ENCODER_CHANNEL_B, false, EncodingType.k4X);
	
	public PIDController posPID;
	public PIDController turnPID;
	public PIDController trimPID;
	
	public Drivetrain() {
		frontRight.setInverted(false); //might take this out
		rearRight.setInverted(false);
		frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		setFollower(rearLeft, frontLeft);
		setFollower(rearRight, frontRight);
		navx = new AHRS(RobotMap.NAVX_PORT);
		navx.setPIDSourceType(PIDSourceType.kDisplacement);
		navx.reset();
		
		turnPID = new PIDController(Constants.TURN_KP, Constants.TURN_KI, Constants.TURN_KD, navx, output->{});
        turnPID.setInputRange(Constants.MIN_ROTATION_ANGLE, Constants.MAX_ROTATION_ANGLE);
        turnPID.setContinuous(true);
        turnPID.setOutputRange(Constants.TURN_PID_MIN_OUTPUT, Constants.TURN_PID_MAX_OUTPUT); //probably will be much less
        turnPID.setAbsoluteTolerance(Constants.TURN_PID_ABSOLUTE_TOLERANCE);
        //turnPID.setPercentTolerance(2.0/360.0);
        
        trimPID = new PIDController(Constants.TRIM_KP, Constants.TRIM_KI, Constants.TRIM_KD, navx, output->{});
        trimPID.setInputRange(Constants.MIN_ROTATION_ANGLE, Constants.MAX_ROTATION_ANGLE);
        trimPID.setContinuous(true);
        trimPID.setOutputRange(Constants.TRIM_PID_MIN_OUTPUT, Constants.TRIM_PID_MAX_OUTPUT); //probably will be much less
        //trimPID.setAbsoluteTolerance(0.5);
        
        
        encoder.setPIDSourceType(PIDSourceType.kDisplacement);
        encoder.setDistancePerPulse(Constants.DRIVETRAIN_ENCODER_DISTANCE_PER_PULSE);
        encoder.setReverseDirection(true);
        
        posPID = new PIDController(Constants.POS_KP, Constants.POS_KI, Constants.POS_KD, encoder, output->{});
        posPID.setOutputRange(Constants.POS_PID_MIN_OUTPUT, Constants.POS_PID_MAX_OUTPUT);
        
        //turnPID.setPercentTolerance(1.0);
        
        
	}
	
	public boolean isRotating() {
		return navx.isRotating();
	}
	
	/**
	 * @returns true if the robot is moving.
	 */
	public boolean isMoving() {
		return Math.abs(frontLeft.getMotorOutputPercent()) > 0.05 || 
			   Math.abs(frontRight.getMotorOutputPercent()) > 0.05;
	}
	
	/**
	 * @returns The heading from the navx (in degrees).
	 */
	public double getHeading() {
		return navx.getAngle();
	}
	
	/**
	 * Drives the motors toward the current angle setpoint
	 * Assumes that there is a setpoint set and that the pid has been enabled
	 */
	public void pidTurn() {
		drive(-turnPID.get(), turnPID.get());
	}
	
	/**
	 * Drives the motors toward the current distance setpoint
	 * Maintains the current angle in order to drive straight
	 */
	public void pidDriveForwardStraight() {
		//drive(posPID.get() + turnPID.get(), posPID.get() + -1*turnPID.get());
		drive(-posPID.get() - trimPID.get(), -posPID.get() + trimPID.get());
	}
	
	/**
	 * Drives the motors toward the current distance setpoint
	 * Maintains the current angle in order to drive straight
	 * (This is a separate function because the gyro is backwards)
	 */
	public void pidDriveBackwardStraight() {
		drive(-posPID.get() + trimPID.get(), -posPID.get() - trimPID.get());
	}

	/**
	 * Drives the motors based on a percent
	 * @param leftPower value from -1.0 to 1.0
	 * @param rightPower value from -1.0 to 1.0
	 */
	public void drive(double leftPower, double rightPower) {
		frontLeft.set(ControlMode.PercentOutput, -leftPower);
		frontRight.set(ControlMode.PercentOutput, rightPower);
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
		//navx.zeroYaw();
	}
	
	public double getRawRightEncoder() {
		return encoder.get();
	}
	
	
	public double getOpticalDistanceInches() {
		/*//getWheelRotation() = distance / (Math.PI * Constants.WHEEL_DIAMETER) * Constants.ENCODER_COUNTS_PER_REVOLUTION;
		return (((getRawMagneticEncoder() / Constants.ENCODER_RATIO_TO_WHEEL * Math.PI * Constants.WHEEL_DIAMETER) / 
				Constants.OPTICAL_ENCODER_COUNTS_PER_REVOLUTION)/ 4) * 3 * 3;*/
		return encoder.getDistance(); //must have a specified distance per pulse set
	}
	
	public double getTicks(double distanceInInches) {
//		return (distance * 9 * Constants.ENCODER_COUNTS_PER_REVOLUTION * Constants.ENCODER_RATIO_TO_WHEEL * 
//				Math.PI * Constants.WHEEL_DIAMETER) / 12;
		
		int ticksPerInch = 71;
		return -1*(ticksPerInch * distanceInInches);
	}
	
	public void resetMagneticEncoder() {
		frontRight.setSelectedSensorPosition(0, 0, 0);
	}
	
	public void resetOpticalEncoder() {
		encoder.reset();
	}
	
	public void initDashboard() {
		SmartDashboard.putNumber("Raw Ultrasonic", Robot.sensors.getRawUltrasonic());
		SmartDashboard.putNumber("Ultrasonic Distance In Inches", Robot.sensors.getDistanceInches());
		SmartDashboard.putNumber("Turn KP", Robot.drivetrain.turnkP);
		SmartDashboard.putNumber("Turn KI", Robot.drivetrain.turnkI);
		SmartDashboard.putNumber("Turn KD", Robot.drivetrain.turnkD);
		SmartDashboard.putNumber("Turn KF", Robot.drivetrain.turnkF);
		SmartDashboard.putNumber("Turn Setpoint", 0);
		SmartDashboard.putData("Reset Encoder", new ResetEncoder());
	}
	
	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("Gyro Angle", getHeading());
    	SmartDashboard.putNumber("Optical Encoder Distance (IN)", getOpticalDistanceInches());
    	SmartDashboard.putBoolean("isMoving", Robot.drivetrain.isMoving());
    	SmartDashboard.putNumber("Encoder Raw", encoder.getRaw());
    	SmartDashboard.putNumber("posPID.get()", posPID.get());
    	SmartDashboard.putNumber("turnPID.get()", turnPID.get());
    	SmartDashboard.putBoolean("onTarget", turnPID.onTarget());
	}
}
	
