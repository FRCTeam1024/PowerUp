package org.usfirst.frc.team1024.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterAutoSwitchSide extends Command {
	/*
	 * boolean initialized = true
	 *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	 */
    public CenterAutoSwitchSide() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//scan for which side is ours
    	//set a variable to R or L
      }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//If L, turn 46 degrees to L;
    	//else, turn 46 degrees R.
    	
    	//Drive 192 inches.
    	
    	//If L, turn 46 degrees R;
    	//else, turn 46 degrees L.
    	
    	//Place cube
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
