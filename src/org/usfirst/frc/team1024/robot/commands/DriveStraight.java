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
    	Robot.drivetrain.resetRightEncoder();
    	double currentAngle = Robot.drivetrain.getHeading();
    	Robot.drivetrain.posPID.setSetpoint(targetDistance);
    	Robot.drivetrain.trimPID.setSetpoint(currentAngle);
    	Robot.drivetrain.posPID.enable();
    	Robot.drivetrain.trimPID.enable();
    }
    
    protected void execute() {
    	//System.out.println("I am here");
    	
    	SmartDashboard.putNumber("targetDistance", targetDistance);
    	Robot.drivetrain.pidDriveForwardStraight();
    	/*if(targetDistance < 0) {
    		Robot.drivetrain.pidDriveBackwardStraight();
    	} else {
    		Robot.drivetrain.pidDriveForwardStraight();
    	}*/
    }

    protected boolean isFinished() {
    	if(Math.abs(Robot.drivetrain.getRightDistanceInches() - targetDistance) < 1.0) { //If the robot is within 1 inch of the target, stop.
    		return true;
    	} else {
    		return false;
    	}
    }
    
    protected void end() {
    	//System.out.println("In end()");
    	Robot.drivetrain.stop();
    	Robot.drivetrain.resetRightEncoder();
    	Robot.drivetrain.posPID.disable();
    	Robot.drivetrain.trimPID.disable();
    }
    
    protected void interrupted() {
    	//System.out.print("In interrupted()");
    	Robot.drivetrain.stop();
    	Robot.drivetrain.resetRightEncoder();
    	Robot.drivetrain.posPID.disable();
    	Robot.drivetrain.trimPID.disable();
    }
}
