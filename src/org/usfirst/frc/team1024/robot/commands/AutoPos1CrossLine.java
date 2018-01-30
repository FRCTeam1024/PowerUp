package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPos1CrossLine extends Command {
	/*
	double distance;										//create double for set distance to drive to
	boolean initialized = false;
	
	 */
    public AutoPos1CrossLine() {
    	/*
    	requires(Robot.drivetrain);
    	
    	*/
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//initialized = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	
    	Robot.drivetrain.driveDistance(distance);			//Make Robot drive forward for a set distance
    	Robot.drivetrain.prepareTurn(angle);				//Prepare to turn to the set angle and let PIDController turn the robot
    	*/
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Check to see if turn is correct and then set is finished to true
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	/*
    	Robot.drivetrain.stop();						//Stop the robot's drivetrain
    	*/
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
