package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseClamp extends Command {
	boolean isDone;
    public CloseClamp() {
    	requires(Robot.lift);
    	isDone = false;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.lift.clamp(false);
    	isDone = true;
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
