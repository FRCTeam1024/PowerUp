package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeTurnSpeed extends Command {
	double speed;
    public ChangeTurnSpeed(double speed) {
    	requires(Robot.drivetrain);
    	this.speed = speed;
    }

    protected void initialize() {
    	Robot.drivetrain.turnPID.setOutputRange(-speed, speed);
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
