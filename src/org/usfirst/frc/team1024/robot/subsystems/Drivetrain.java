/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
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
public class Drivetrain extends Subsystem {
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
	
	public Encoder encoder = new Encoder(RobotMap.ENCODER_CHANNEL_A, RobotMap.ENCODER_CHANNEL_B);
	public PIDController posPID;
	public TrimPID trimPid;
	
	
	public PIDController trimPID;
	public Drivetrain() {
		frontRight.setInverted(false);
		rearRight.setInverted(false);
		setFollower(rearLeft, frontLeft);
		setFollower(rearRight, frontRight);
		navx = new AHRS(Port.kMXP);
		
		//trimPid = new TrimPID();
		
		navx.setPIDSourceType(PIDSourceType.kDisplacement);
        

		frontRight.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		frontRight.configNominalOutputForward(0, 10);
        frontRight.configNominalOutputReverse(0, 10);
        frontRight.configPeakOutputForward(0.5, 10);
        frontRight.configPeakOutputReverse(-0.5, 10);
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
		
        //turnPID.setAbsoluteTolerance(Constants.NAVX_TOLERANCE);
        //turnPID.setContinuous(true);
        
        
        trimPID = new PIDController(Constants.POS_KP, Constants.POS_KI, Constants.POS_KD, navx, output->{});
        trimPID.setInputRange(-180, 180);
        trimPID.setContinuous(true);
        trimPID.setOutputRange(-1.0, 1.0); //probably will be much less
	}
	
	public boolean isMoving() {
		return Math.abs(frontLeft.getMotorOutputPercent()) > 0.05 || 
      Math.abs(frontRight.getMotorOutputPercent()) > 0.05;
	}
	
	public double getHeading() {
		return navx.getAngle();
	}
	
	/*
	public void prepareTurn(double angle) {
		resetGyro();
		getPIDController().setSetpoint(angle);
		getPIDController().enable();
	}
	*/
	
	public void pidDrive(double distance) {
		pidDrive(distance, true);
	}
	
	public void pidDrive(double distance, boolean maintainAngle) {
		posPID.setSetpoint(distance);
		posPID.enable();
		if (maintainAngle) {
			drive(posPID.get() * trimPID.get(), posPID.get() * -trimPID.get());
		} else {
			drive(posPID.get(), posPID.get());
		}
	}
	
	public void turn(double rotatePower) {
    	drive(-rotatePower, rotatePower);
    	//This is a temp fix. Remove later
    	frontLeft.setInverted(false);
		rearLeft.setInverted(false);
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
		return (((getRawEncoder() / Constants.ENCODER_RATIO_TO_WHEEL * Math.PI * Constants.WHEEL_DIAMETER) / 
				Constants.ENCODER_COUNTS_PER_REVOLUTION)/ 4) * 3 * 3;
	}
	
	public double getTicks(double distanceInInches) {
//		return (distance * 9 * Constants.ENCODER_COUNTS_PER_REVOLUTION * Constants.ENCODER_RATIO_TO_WHEEL * 
//				Math.PI * Constants.WHEEL_DIAMETER) / 12;
		
		int ticksPerInch = 71;
		return -1*(ticksPerInch * distanceInInches);
	}
	
	public void resetEncoder() {
		// frontRight.getSensorCollection().setQuadraturePosition(0, 10);
		// frontRight.get
		frontRight.setSelectedSensorPosition(0, 0, 0);
	}
	/*
	public void driveDistance(double inches, double angle) {
		double ticks = getTicks(inches);
		trimPid.getPIDController().setSetpoint(angle);
		SmartDashboard.putNumber("trim error", trimPid.getPIDController().getError());
		System.out.println("num Ticks for " + inches + " inches : " + ticks);
		frontRight.set(ControlMode.Position, ticks);
		//this is a temp fix and should not be on the actual robot. 
		//We did this because there is an encoder on only one side of the drivetrain
		frontLeft.set(ControlMode.PercentOutput, frontRight.getMotorOutputPercent()+trimPid.getOutput()); 
		frontLeft.setInverted(true);
		rearLeft.setInverted(true);
		//frontLeft.set(ControlMode.PercentOutput, frontRight.getMotorOutputPercent());
//		frontRight.set(ControlMode.Position, -3000);
		SmartDashboard.putNumber("Encoder Distance (In.)", getDistanceInches());
	}*/
}
	
