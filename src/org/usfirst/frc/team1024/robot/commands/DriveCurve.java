package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveCurve extends Command {

	private int targetHeading;
	private double leftPower;
	private double rightPower;
	
    public DriveCurve() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    	outputToSmartDashboard();
    	this.targetHeading = (int) SmartDashboard.getNumber("Curve Target Angle", 90);
    	this.leftPower = (double) SmartDashboard.getNumber("Curve left power", 0.9);
    	this.rightPower = (double) SmartDashboard.getNumber("Curve right power", 0.1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// it's inverted, for some reason?
    	Robot.drivetrain.drive(-leftPower, -rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Math.abs(targetHeading - Robot.drivetrain.getHeading()) < 2) {
    		Robot.drivetrain.stop();
    		return true;
    	} else
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    public void outputToSmartDashboard() {
		SmartDashboard.putNumber("Curve Target Angle", 90);
    	SmartDashboard.putNumber("Curve left power", 0.9);
    	SmartDashboard.putNumber("Curve right power", 0.1);
    	
    	SmartDashboard.putData("Drive Curve", this);
	}
}
