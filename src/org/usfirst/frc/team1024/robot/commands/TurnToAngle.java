package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngle extends Command {
	double targetAngle;
	boolean initialized = false;
	double rotatePower;
    public TurnToAngle(double angle) {
    	requires(Robot.drivetrain);
    	this.targetAngle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.prepareTurn(targetAngle);
    	
		initialized = true;
		
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	SmartDashboard.putNumber("rotatePower", rotatePower);
    	SmartDashboard.putNumber("Angle from Turn To Angle: ", Robot.drivetrain.getHeading());
    	SmartDashboard.putBoolean("isMoving", Robot.drivetrain.isMoving());
    	System.out.println("I got here");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Timer.delay(5);
    	return true;
//      return initialized 
//        		&& !Robot.drivetrain.isMoving() ;
//        		&& Math.abs(targetAngle - Robot.drivetrain.getHeading()) < 5; //fiddle with the 10 number
//    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    	initialized = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    	initialized = false;
    }
}
