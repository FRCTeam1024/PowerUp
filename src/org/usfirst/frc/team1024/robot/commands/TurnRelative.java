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
    	
    }

    protected void execute() {
    	if (targetAngle != Robot.drivetrain.getHeading()) {
    		if (targetAngle < 180 && targetAngle > 0) {
    			//turn clockwise
    		} else if (targetAngle > -180 && targetAngle < 0) {
    			//turn counter-clockwise
    		}
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
