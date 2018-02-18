package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeDriveSpeed extends Command {
	double speed;
    public ChangeDriveSpeed(double speed) {
    	requires(Robot.drivetrain);
    	this.speed = speed;
    }

    protected void initialize() {
    	Robot.drivetrain.posPID.setOutputRange(-speed, speed);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
