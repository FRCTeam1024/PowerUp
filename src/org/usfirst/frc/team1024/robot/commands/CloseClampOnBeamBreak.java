package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseClampOnBeamBreak extends Command {
	boolean isDone;
	
    public CloseClampOnBeamBreak() {
    	requires(Robot.lift);
    	isDone = false;
    }

    protected void initialize() {
    	System.out.println("Trying to close the clamp");
    }

    protected void execute() {
    	if(Robot.intake.cubeIsInClampRange()) {
    		Robot.lift.clamp(false);
    		isDone = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Closing the clamp");
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
