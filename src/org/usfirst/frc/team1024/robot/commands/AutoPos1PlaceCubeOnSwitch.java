package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoPos1PlaceCubeOnSwitch extends Command {
	/*
	double angle;         									//create double for set angle to turn to
	boolean initialized = false;
	double rotatePower;
	
	 */
    public AutoPos1PlaceCubeOnSwitch() {
      
    	/*
    	requires(Robot.drivetrain);
    	requires(Robot.sensors);
    	*/
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//initialized = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	/*
    	
    	Robot.drivetrain.driveDistance(distance_1);			//Make Robot drive forward for a set distance
    	Robot.drivetrain.prepareTurn(angle);				//Prepare to turn to the set angle and let PIDController turn the robot
    	
    	distance_2 = Robot.sensors.getDistanceInches();		//Collect data from sonar to get robot within distance to place a cube
    	Robot.drivetrain.driveDistance(distance_2);			//Drive to distance collected from sonar
    		//Activate mechanism to place a cube on the switch
      	}
    	 
    	*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//Set isFinished to true after task is done 
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	/*
    	Robot.drivetrain.stop();						//Stop the robot's drivetrain
    	//Stop the intake
    	*/
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
