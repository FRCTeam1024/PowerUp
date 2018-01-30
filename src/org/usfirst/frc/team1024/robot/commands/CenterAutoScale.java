package org.usfirst.frc.team1024.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterAutoScale extends Command {
	/*
	 * boolean initialized = true
	 *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	 */
    public CenterAutoScale() {
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
    //R & L indicate which side of the scale is ours.
    	//If L, turn 42 L;
    	//else, turn  42 R.
    	
    	//Drive 178.5 inches
    	
    	//If L, turn 42 R;
    	//else, turn  42 L.
    	
    	//Drive 204 inches.
    	
    	//If L, turn 90 R;
    	//else, turn 90 L.
    	
    	//Place cube on scale.
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
