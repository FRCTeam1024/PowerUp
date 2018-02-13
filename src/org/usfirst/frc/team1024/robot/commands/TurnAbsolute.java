package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnAbsolute extends Command {
	double targetAngle;
	boolean isDone = false;
	double rotatePower;
    public TurnAbsolute(double angle) {
    	requires(Robot.drivetrain);
    	this.targetAngle = angle;
    }
    
    public void setAngle(double angle) {
    	this.targetAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.drivetrain.prepareTurn(targetAngle);
    	
		isDone = true;
		
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(Robot.drivetrain.getHeading() - targetAngle) < 2) { //if the robot is within 2 degrees of the target, stop
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    	//Robot.drivetrain.getPIDController().disable();
    	isDone = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    	isDone = false;
    }
}
