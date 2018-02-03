package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


/**
 * Tries to use navx gyro to auto-correct. DOES NOT WORK, DO NOT USE.
 */
public class DriveStraight extends Command {

	public double distance;
	public double power;
	private double startHeading;
	private double leftPower, rightPower;
	
    public DriveStraight(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	requires(Robot.sensors);
    	this.distance = distance;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.encoderReset();
    	Robot.drivetrain.navx.reset();
    	startHeading = Robot.drivetrain.navx.getAngle();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentHeading = Robot.drivetrain.navx.getAngle();
    	if(currentHeading > startHeading) {
    		rightPower += 0.02;
    		leftPower -= 0.02;
    	} else if (currentHeading < startHeading) {
    		leftPower += 0.02;
    		rightPower -= 0.02;
    	}
    	Robot.drivetrain.drive(leftPower, rightPower);
    	 //double distanceDriven = Robot.drivetrain.getEncoderValue();
    	System.out.println("Driving Straight, leftPower : " + leftPower + ", rightPower : " + rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if( Robot.drivetrain.getEncoderValue() > distance) {
    		System.out.println("Done");
    		return true;
    		
    	}
        return false;
    }
    
    

    public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getPower() {
		return power;
	}

	// from SmartDashboard
	public void setPower(double power) {
		this.power = power;
		leftPower = power;
    	rightPower = power;
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
