package org.usfirst.frc.team1024.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CenterAutoSwitchAndScale extends Command {
	/*
	 * boolean initialized = true
	 *                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	 */
    public CenterAutoSwitchAndScale() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//scan for which side is ours
    	//set a variable to R or L for Switch
    	//set a variable to R or L for Scale
      }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //R & L indicate which side of the switch is ours.
    	//If Switch is R, turn 35 degrees R;
    	//else, turn 35 L;
    	
    	//Drive 175 inches
    	
    	//If Switch is R, turn 125 degrees L;
    	//else, turn 125 degrees R.
    	
    	//Drive 43 inches.
    	
    	//Place cube.
    	
    	//If Switch is R, turn 90 degrees R;
    	//else, turn 90 degrees L.
    	
    	//Drive 28 inches.
    	
    	//If Switch is R, turn 90 degrees L;
    	//else, turn 90 degrees R.
    	
    	//Drive 6 inches.
    	
    	//Pick up cube.
    	
    	//If Switch is R, turn 90 degrees R;
    	//else, turn 90 degrees L.
    	
    	//If Switch is R
    		//If Scale is R
    			//turn 18 degrees R;
    	    	//drive 135 inches;
    		//If Scale is L
    			//drive 33 inches;
    			//turn 90 degrees L;
    			//drive 192 inches;
    			//turn 90 degrees R;
    			//drive 95 inches;
    			//turn 90 degrees R;
    			//drive 35 inches;
    			//lift to max height and drop cube;
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
