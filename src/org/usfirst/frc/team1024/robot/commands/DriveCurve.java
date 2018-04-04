package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveCurve extends Command {
	double targetDistance;
	double tolerance = 5;
	double trimAngle;
	int onTargetCount = 0;
	boolean hasShifted = false;
	
    public DriveCurve(double targetDistance, double trimAngle) {
    	requires(Robot.drivetrain);
    	this.targetDistance = targetDistance;
    	this.trimAngle = trimAngle;
    }

    protected void initialize() {
    	Robot.drivetrain.resetOpticalEncoder();
    	Robot.drivetrain.resetMagneticEncoder();
    	Robot.drivetrain.posPID.setSetpoint(targetDistance);
    	Robot.drivetrain.trimPID.setSetpoint(trimAngle);
    	Robot.drivetrain.posPID.enable();
    	Robot.drivetrain.trimPID.enable();
    }
    
    protected void execute() {
    	SmartDashboard.putNumber("targetDistance", targetDistance);
    	// Robot.drivetrain.pidDriveForwardStraight();
    	if (Robot.drivetrain.getOpticalDistanceInches() > 1.0 && !hasShifted) {
    		Robot.drivetrain.shiftHigh();
    		hasShifted = true;
    		SmartDashboard.putNumber("Shifter", Robot.drivetrain.getShiftState() ? 0 : 1);
    	}
    	if(targetDistance < 0) {
    		Robot.drivetrain.pidDriveBackwardStraight();
    	} else {
    		Robot.drivetrain.pidDriveForwardStraightBias(0.0);
    	}
    }

    protected boolean isFinished() {
    	if (Math.abs(Robot.drivetrain.getOpticalDistanceInches() - targetDistance) < tolerance) {
    		onTargetCount++;
    	} else {
    		onTargetCount = 0;
    	}
    	
    	if(onTargetCount == 30) {
    		System.out.println("Done at " + Robot.drivetrain.getOpticalDistanceInches() + " inches");
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
