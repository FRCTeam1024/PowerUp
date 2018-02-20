package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveAndShift extends Command {
	double targetDistance;
	double tolerance;
	int onTargetCount = 0;
	boolean hasShifted = false;
	
    public DriveAndShift(double targetDistance) {
    	requires(Robot.drivetrain);
    	this.targetDistance = targetDistance;
    	tolerance = 5;
    }
    
    public DriveAndShift(double targetDistance, double tolerance) {
    	requires(Robot.drivetrain);
    	this.targetDistance = targetDistance;
    	this.tolerance = tolerance;
    }

    protected void initialize() {
    	Robot.drivetrain.resetOpticalEncoder();
    	Robot.drivetrain.resetMagneticEncoder();
    	double currentAngle = Robot.drivetrain.getHeading();
    	Robot.drivetrain.posPID.setSetpoint(targetDistance);
    	Robot.drivetrain.trimPID.setSetpoint(currentAngle);
    	Robot.drivetrain.posPID.enable();
    	Robot.drivetrain.trimPID.enable();
    	Robot.drivetrain.shiftLow();
    }
    
    protected void execute() {
    	SmartDashboard.putNumber("targetDistance", targetDistance);
    	// Robot.drivetrain.pidDriveForwardStraight();
    	if (Robot.drivetrain.getOpticalDistanceInches() > 12.0 && !hasShifted) {
    		Robot.drivetrain.shiftHigh();
    		hasShifted = true;
    		SmartDashboard.putNumber("Shifter", Robot.drivetrain.getShiftState() ?0:1);
    	}
    	if(targetDistance < 0) {
    		Robot.drivetrain.pidDriveBackwardStraight();
    	} else {
    		Robot.drivetrain.pidDriveForwardStraight();
    	}
    }

    protected boolean isFinished() {
    	if (Math.abs(Robot.drivetrain.getOpticalDistanceInches() - targetDistance) < tolerance) {
    		onTargetCount++;
    	} else {
    		onTargetCount = 0;
    	}
    	
    	if(onTargetCount == 30) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    protected void end() {
    	System.out.println("In end()");
    	Robot.drivetrain.stop();
    	Robot.drivetrain.resetOpticalEncoder();
    	Robot.drivetrain.posPID.disable();
    	Robot.drivetrain.trimPID.disable();
    }
    
    protected void interrupted() {
    	//System.out.print("In interrupted()");
    	Robot.drivetrain.stop();
    	Robot.drivetrain.resetOpticalEncoder();
    	Robot.drivetrain.posPID.disable();
    	Robot.drivetrain.trimPID.disable();
    }
}
