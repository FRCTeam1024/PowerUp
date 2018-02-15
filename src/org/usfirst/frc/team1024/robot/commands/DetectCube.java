package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DetectCube extends Command {
	boolean isDone;
    public DetectCube() {
    	requires(Robot.lift);
    	requires(Robot.intake);
    	isDone = false;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(!Robot.intake.cubeDetecterState()) {
    		Robot.lift.clamp(false);
    		Robot.intake.slideIn();
    		isDone = true;
    	}
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
