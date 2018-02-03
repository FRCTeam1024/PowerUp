package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnRelative extends Command {
	double targetAngle;
    public TurnRelative(double targetAngle) {
    	requires(Robot.drivetrain);
    	this.targetAngle = targetAngle;
    }

    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.turnPID.setSetpoint(targetAngle);
    	Robot.drivetrain.turnPID.enable();
    }

    protected void execute() {
    	Robot.drivetrain.pidTurn();
    }
    
    private boolean isOnTarget() {
    	//return Math.abs(Robot.drivetrain.getHeading() - targetAngle) < 1;
    	return Robot.drivetrain.turnPID.onTarget();
    }
    
    private boolean motorsDone() {
    	return Robot.drivetrain.turnPID.get() < 0.01; //might be bigger
    }

    
    protected boolean isFinished() {
    	if(isOnTarget()) { //if the robot is within 2 degrees of the target, stop
    		return true;
    	} else {
    		return false;
    	}
    }

    protected void end() {
    	Robot.drivetrain.stop();
    	Robot.drivetrain.turnPID.disable();
    }

    protected void interrupted() {
    	Robot.drivetrain.stop();
    	Robot.drivetrain.turnPID.disable();
    }
}
