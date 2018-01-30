package org.usfirst.frc.team1024.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterAutoSwitch extends Command {
	/*
	 * boolean initialized = true
	 *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	 */
    public CenterAutoSwitch() {
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
    //R & L indicate which side of the switch is ours.
    	//If R, turn 35 R;
    	//else, turn 35 L;
    	
    	//Drive 175 inches
    	
    	//If Switch is R, turn 125 inches L;
    	//else, turn 125 inches R.
    	
    	//Drive 43 inches.
    	
    	//Place cube.
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
