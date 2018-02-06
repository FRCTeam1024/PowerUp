/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.RobotMap;
import org.usfirst.frc.team1024.robot.commands.ResetEncoder;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Drivetrain extends Subsystem {
	private TalonSRX frontLeft  = new TalonSRX(RobotMap.FRONT_LEFT_MOTOR_PORT);
	//private TalonSRX middleLeft = new TalonSRX(RobotMap.MIDDLE_LEFT_MOTOR_PORT);
	private TalonSRX rearLeft = new TalonSRX(RobotMap.REAR_LEFT_MOTOR_PORT);
	private TalonSRX frontRight = new TalonSRX(/*RobotMap.FRONT_RIGHT_MOTOR_PORT*/ 6);
	//private TalonSRX middleRight = new TalonSRX(4);
	private TalonSRX rearRight = new TalonSRX(/*RobotMap.REAR_RIGHT_MOTOR_PORT*/ 7);
	
	
	private AHRS navx;
	
	public double rotateToAngleRate;
	
//	public double pidGet;
	
	//Remove these and any references when set properly
	public double turnkP = RobotMap.TURN_KP;
	public double turnkI = RobotMap.TURN_KI;
	public double turnkD = RobotMap.TURN_KD;
	public double turnkF = RobotMap.TURN_KF;
	
	public Encoder encoder = new Encoder(RobotMap.ENCODER_CHANNEL_A, RobotMap.ENCODER_CHANNEL_B, false, EncodingType.k4X);
	
	public PIDController posPID;
	public PIDController turnPID;
	public PIDController trimPID;
	
	public Drivetrain() {
		//frontRight.setInverted(false); //might take this out
		//rearRight.setInverted(false); //IDS ARE WRONG!!!
		frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		//setFollower(rearLeft, frontLeft);
		//setFollower(rearRight, frontRight);
		navx = new AHRS(RobotMap.NAVX_PORT);
		navx.setPIDSourceType(PIDSourceType.kDisplacement);
		navx.reset();
		
		turnPID = new PIDController(RobotMap.TURN_KP, RobotMap.TURN_KI, RobotMap.TURN_KD, navx, output->{});
        turnPID.setInputRange(RobotMap.MIN_ROTATION_ANGLE, RobotMap.MAX_ROTATION_ANGLE);
        turnPID.setContinuous(true);
        turnPID.setOutputRange(RobotMap.TURN_PID_MIN_OUTPUT, RobotMap.TURN_PID_MAX_OUTPUT); //probably will be much less
        turnPID.setAbsoluteTolerance(RobotMap.TURN_PID_ABSOLUTE_TOLERANCE);
        //turnPID.setPercentTolerance(2.0/360.0);
        
        trimPID = new PIDController(RobotMap.TRIM_KP, RobotMap.TRIM_KI, RobotMap.TRIM_KD, navx, output->{});
        trimPID.setInputRange(RobotMap.MIN_ROTATION_ANGLE, RobotMap.MAX_ROTATION_ANGLE);
        trimPID.setContinuous(true);
        trimPID.setOutputRange(RobotMap.TRIM_PID_MIN_OUTPUT, RobotMap.TRIM_PID_MAX_OUTPUT); //probably will be much less
        //trimPID.setAbsoluteTolerance(0.5);
        
        
        encoder.setPIDSourceType(PIDSourceType.kDisplacement);
        encoder.setDistancePerPulse(RobotMap.DRIVETRAIN_ENCODER_DISTANCE_PER_PULSE);
        encoder.setReverseDirection(true);
        
        posPID = new PIDController(RobotMap.POS_KP, RobotMap.POS_KI, RobotMap.POS_KD, encoder, output->{});
	
        posPID.setOutputRange(RobotMap.POS_PID_MIN_OUTPUT, RobotMap.POS_PID_MAX_OUTPUT);
        
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
		rearLeft.set(ControlMode.PercentOutput, -leftPower);
		rearRight.set(ControlMode.PercentOutput, rightPower);
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

	/**
	 * Resets the gyro angle reading
	 * CAUTION: This method contains a delay, which might not seem like much, 
	 * but if called in a periodic block, it WILL crash the code
	 */
	public void resetNavx() {
		navx.reset();
		Timer.delay(0.2); //Delay so we can't read the gyro while it is doing its reset calibration
		//navx.zeroYaw();
	}
	
	public double getRawRightEncoder() {
		return encoder.get();
	}
	
	
	public double getOpticalDistanceInches() {
		/*//getWheelRotation() = distance / (Math.PI * RobotMap.WHEEL_DIAMETER) * RobotMap.ENCODER_COUNTS_PER_REVOLUTION;
		return (((getRawMagneticEncoder() / RobotMap.ENCODER_RATIO_TO_WHEEL * Math.PI * RobotMap.WHEEL_DIAMETER) / 
				RobotMap.OPTICAL_ENCODER_COUNTS_PER_REVOLUTION)/ 4) * 3 * 3;*/
		return encoder.getDistance(); //must have a specified distance per pulse set
	}
	
	public double getTicks(double distanceInInches) {
//		return (distance * 9 * RobotMap.ENCODER_COUNTS_PER_REVOLUTION * RobotMap.ENCODER_RATIO_TO_WHEEL * 
//				Math.PI * RobotMap.WHEEL_DIAMETER) / 12;
		
		int ticksPerInch = 71;
		return -1*(ticksPerInch * distanceInInches);
	}
	
	public void resetMagneticEncoder() {
		frontRight.setSelectedSensorPosition(0, 0, 0);
	}
	
	public void resetOpticalEncoder() {
		encoder.reset();
	}
	
	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("Gyro Angle", getHeading());
    	SmartDashboard.putNumber("Optical Encoder Distance (IN)", getOpticalDistanceInches());
    	SmartDashboard.putNumber("Encoder Raw", encoder.getRaw());
    	SmartDashboard.putNumber("posPID.get()", posPID.get());
    	SmartDashboard.putNumber("turnPID.get()", turnPID.get());
    	SmartDashboard.putBoolean("onTarget", turnPID.onTarget());
	}
}
	
