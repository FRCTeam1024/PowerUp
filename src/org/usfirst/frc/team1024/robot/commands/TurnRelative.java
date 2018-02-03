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

    protected boolean isFinished() {
    	if(Math.abs(Robot.drivetrain.getHeading() - targetAngle) < 10) { //if the robot is within 2 degrees of the target, stop
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
