
package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnRelative extends Command {
	double targetAngle;
	double degreeTolerance;
	int onTargetCount = 0;
    public TurnRelative(double targetAngle) {
    	requires(Robot.drivetrain);
    	this.targetAngle = targetAngle;
    }
    
    public TurnRelative(double targetAngle, double degreeTolerance) {
    	requires(Robot.drivetrain);
    	this.targetAngle = targetAngle;
    	this.degreeTolerance = degreeTolerance;
    }

    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.turnPID.setSetpoint(targetAngle);
    	Robot.drivetrain.turnPID.enable();
    }

    protected void execute() {
    	Robot.drivetrain.pidTurn();
    	SmartDashboard.putNumber("onTargetCount", onTargetCount);
    }
    
    private boolean isOnTarget() {
    	return Math.abs(Robot.drivetrain.getHeading() - targetAngle) < degreeTolerance;  //if the robot is within 1 degrees of the target, stop
    	//return Robot.drivetrain.turnPID.onTarget();
    }
        
    protected boolean isFinished() {
    	if (isOnTarget()) {
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
