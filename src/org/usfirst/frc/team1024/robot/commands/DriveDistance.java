package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveDistance extends Command {
	double distance; // in inches
	double targetTicks;
	double startAngle;
    public DriveDistance(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.distance = distance;
    	this.targetTicks = Robot.drivetrain.getTicks(distance);
    }
    
    public void setDistance(double distance) {
    	this.distance = distance;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoder();
    	startAngle = Robot.drivetrain.getHeading();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveDistance(distance, startAngle);
    	SmartDashboard.putNumber("startAngle", startAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() { 
    	// TODO check if has reached target ticks, within an inch
    	int ticksPerInch = 71;
    	if(Math.abs(Robot.drivetrain.getRawEncoder() - targetTicks) < ticksPerInch) {
    		return true;
    	} else {
    		return false;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.resetEncoder();
    	Robot.drivetrain.stop();
    	Timer.delay(1);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
