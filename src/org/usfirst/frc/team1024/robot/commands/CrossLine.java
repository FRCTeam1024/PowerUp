package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 *
 */
public class CrossLine extends Command {

	public double distance;

	
    public CrossLine(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.sensors);
    	this.distance = distance;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.encoderReset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.drive(.5, .5);
    	 //double distanceDriven = Robot.drivetrain.getEncoderValue();
    	System.out.println("Driving");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if( Robot.drivetrain.getEncoderValue() > distance) {
    		System.out.println("Done");
    		return true;
    		
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
