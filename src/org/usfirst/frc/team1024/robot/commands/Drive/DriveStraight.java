package org.usfirst.frc.team1024.robot.commands.Drive;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command {
	double targetDistance;
	double tolerance;
	int onTargetCount = 0;
	
    public DriveStraight(double targetDistance) {
    	requires(Robot.drivetrain);
    	this.targetDistance = targetDistance;
    	tolerance = 5;
    }
    
    public DriveStraight(double targetDistance, double tolerance) {
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
    }
    
    protected void execute() {
    	SmartDashboard.putNumber("targetDistance", targetDistance);
    	// Robot.drivetrain.pidDriveForwardStraight();
    	if(targetDistance < 0) {
    		Robot.drivetrain.pidDriveBackwardStraight();
    	} else {
    		Robot.drivetrain.pidDriveForwardStraight();
    	}
    }

    protected boolean isFinished() {
    	/* (Math.abs(Robot.drivetrain.getOpticalDistanceInches() - targetDistance) < tolerance) {
    		onTargetCount++;
    	} else {
    		onTargetCount = 0;
    	}
    	
    	if(onTargetCount >= 10) {
    		return true;
    	} else {
    		return false;
    	} */
    	return Robot.drivetrain.posPID.onTarget();
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
