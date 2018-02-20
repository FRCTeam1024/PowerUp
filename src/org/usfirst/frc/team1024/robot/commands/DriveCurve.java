package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveCurve extends Command {

	private int targetHeading = 0;
	private double leftPower = 0.0;
	private double rightPower = 0.0;
	private boolean initialized = false;
	private boolean slowedDown = false;
	
    public DriveCurve(int targetAngle) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.targetHeading = targetAngle;
    }
    
    public DriveCurve() {
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    	outputToSmartDashboard();
    	
    	if(targetHeading == 0) {
    		this.targetHeading = (int) SmartDashboard.getNumber("Curve Target Angle", 60);
    	}
    	this.leftPower = (double) SmartDashboard.getNumber("Curve left power", 1.0);
    	this.rightPower = (double) SmartDashboard.getNumber("Curve right power", 0.5);
    	if(targetHeading < 0) { // turning left
    		double tempPower = leftPower;
    		this.leftPower = this.rightPower;
    		this.rightPower = tempPower;
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// it's inverted, for some reason?
    	// try to slow it down, it's overshooting
    	if(Math.abs(targetHeading - Robot.drivetrain.getHeading()) < 10 && !slowedDown) { 
    		leftPower = leftPower / 2;
    		rightPower = rightPower / 2;
    		// do it just once, not every cycle
    		System.out.println("  SLOWING DOWN");
    		slowedDown = true;
    	}
    	Robot.drivetrain.drive(-leftPower, -rightPower);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	// stop early, allow for some coasting
    	if(Math.abs(targetHeading - Robot.drivetrain.getHeading()) < 4) { 
//    		Robot.drivetrain.
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
		SmartDashboard.putNumber("Curve Target Angle", 60);
    	SmartDashboard.putNumber("Curve left power", 0.9);
    	SmartDashboard.putNumber("Curve right power", 0.1);
    	
    	SmartDashboard.putData("Drive Curve", this);
	}
}
