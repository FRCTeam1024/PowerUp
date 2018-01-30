package org.usfirst.frc.team1024.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterAutoExchange extends Command {

    public CenterAutoExchange() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Drive 12 in.
    	//Turn 90 L
    	//Drive 12 in.
    	//Turn 90 L
    	//Drive 12 in.
    	//Put cube in exchange
    	//Drive 130 in. Backwards
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
