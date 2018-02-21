package org.usfirst.frc.team1024.robot.commands.lift;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeLiftSpeed extends Command {
	double speed;
    public ChangeLiftSpeed(double speed) {
    	requires(Robot.lift);
    	this.speed = speed;
    }

    protected void initialize() {
    	Robot.lift.configMaxOutputs(speed);
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
