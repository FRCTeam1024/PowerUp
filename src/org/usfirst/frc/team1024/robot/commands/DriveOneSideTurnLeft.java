package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveOneSideTurnLeft extends Command {
	double targetAngle;
	double degreeTolerance;
	int onTargetCount = 0;
    public DriveOneSideTurnLeft(double targetAngle) {
    	requires(Robot.drivetrain);
    	this.targetAngle = targetAngle;
    	degreeTolerance = 2.0;
    }
    
    public DriveOneSideTurnLeft(double targetAngle, double degreeTolerance) {
    	requires(Robot.drivetrain);
    	this.targetAngle = targetAngle;
    	this.degreeTolerance = degreeTolerance;
    }

    protected void initialize() {
    	//Robot.drivetrain.resetGyro();
    	double currentAngle = Robot.drivetrain.getHeading();
    	Robot.drivetrain.turnPID.setSetpoint(currentAngle+targetAngle);
    	Robot.drivetrain.turnPID.enable();
    	Robot.drivetrain.shiftLow();
    }

    protected void execute() {
    	Robot.drivetrain.pidTurnOneSideLeft();
    	SmartDashboard.putNumber("onTargetCount", onTargetCount);
    }
    
    private void log(Object msg) {
    	System.out.println(msg);
    }
    
    private boolean isOnTarget() {
    	return Math.abs(Robot.drivetrain.getHeading() - targetAngle) < degreeTolerance;  //if the robot is within 1 degrees of the target, stop
    	//return Robot.drivetrain.turnPID.onTarget();
    }
        
    protected boolean isFinished() {
    	SmartDashboard.putNumber("OnTargetCount", onTargetCount);
    	if (isOnTarget()) {
    		onTargetCount++;
    	} else {
    		onTargetCount = 0;
    	}
    	
    	if(onTargetCount >= 10) {
    		log("finished turn at " + Robot.drivetrain.getHeading() + "angle");
    		return true;
    	} else {
    		return false;
    	}
    }

    protected void end() {
    	Robot.drivetrain.stop();
    	Robot.drivetrain.turnPID.disable();
    	onTargetCount = 0;
    }

    protected void interrupted() {
    	Robot.drivetrain.stop();
    	Robot.drivetrain.turnPID.disable();
    	onTargetCount = 0;
    }
}
