package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraight extends Command {
	double targetDistance;
	
    public DriveStraight(double targetDistance) {
    	requires(Robot.drivetrain);
    	this.targetDistance = targetDistance;
    }

    protected void initialize() {
    	Robot.drivetrain.resetOpticalEncoder();
    	Robot.drivetrain.resetMagneticEncoder();
    	double currentAngle = Robot.drivetrain.getHeading();
    	Robot.drivetrain.posPID.setSetpoint(targetDistance);
    	Robot.drivetrain.turnPID.setSetpoint(currentAngle);
    	Robot.drivetrain.posPID.enable();
    	Robot.drivetrain.turnPID.enable();
    }
    
    protected void execute() {
    	System.out.println("I am here");
    	SmartDashboard.putNumber("targetDistance", targetDistance);
    	if(targetDistance < 0) {
    		Robot.drivetrain.pidDriveBackwardStraight();
    	} else {
    		Robot.drivetrain.pidDriveForwardStraight();
    	}
    }

    protected boolean isFinished() {
    	if(Math.abs(Robot.drivetrain.getOpticalDistanceInches() - targetDistance) < 1.0) { //If the robot is within 1 inch of the target, stop.
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
    	Robot.drivetrain.turnPID.disable();
    }
    
    protected void interrupted() {
    	System.out.print("In interrupted()");
    	Robot.drivetrain.stop();
    	Robot.drivetrain.resetOpticalEncoder();
    	Robot.drivetrain.posPID.disable();
    	Robot.drivetrain.turnPID.disable();
    }
}
