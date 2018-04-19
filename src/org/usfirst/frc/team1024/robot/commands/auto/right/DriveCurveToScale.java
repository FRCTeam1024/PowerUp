package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An attempt at a smooth curve to deliver first cube to scale, roughly in the path of 
 * the 'zane' path
 * 
 * idea is to have 3 'legs' of the path:
 * 1. accelerate smoothly from beginning; drive straight to the point where the zane path turns; 
 * 2. transition smoothly to a one-side powered turn for a specific angle; 22 degrees is the Zane path
 * 3. continue driving straight, smoothly maintaining speed, then decelerate to scale; 
 * 		3b. in parallel somehow, raise lift 
 */
public class DriveCurveToScale extends Command {
	
	double targetDrivePower = 0.9;
	double currentDrivePower = 0.0;
	double currentInnerTurnPower;
	double targetInnerTurnPower = 0.25;
	int currentLeg = 1;
	long startTime;
	double distanceToTurn = 159; // originally 189; reducing some because driving thru turn now
	double targetAngle = -22;
	double distanceToScale = 48; // was originally 78 but need to shorten it because it'll be driving forward on the turn
	boolean isFinished = false;

    public DriveCurveToScale() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    	Robot.drivetrain.resetOpticalEncoder();
    	Robot.drivetrain.resetMagneticEncoder();
    	double currentAngle = Robot.drivetrain.getHeading();
    	currentLeg = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	long currentTime = System.currentTimeMillis();
    	long timeElapsedMillis = currentTime - startTime;

    	if (currentLeg == 1 &&
    			Math.abs(Robot.drivetrain.getOpticalDistanceInches() - distanceToTurn) < 3) {
    		currentLeg = 2; // start the turn
    		currentInnerTurnPower = currentDrivePower;
    	}
    	
    	if(currentLeg == 1) {
    		if(currentDrivePower <= targetDrivePower && timeElapsedMillis < 500) {
    			// accelerate over first 0.5 second?
    			// so if this is called roughly every 20 ms, or 50x a sec, then split the acceleration over 25 calls
    			currentDrivePower += (0.9 / 25.0);
    		}
    		Robot.drivetrain.drive(-currentDrivePower, -currentDrivePower);
    	} else if(currentLeg == 2) {
    		// powered turn left
    		if(currentInnerTurnPower > targetInnerTurnPower) {
    			// go from 0.9 down to 0.25
    			currentInnerTurnPower -= 0.05;
    		}
    		if (Math.abs(Robot.drivetrain.getHeading() - targetAngle) < 1.0) {
    			//reached target, start driving straight to scale
    			currentLeg = 3;
    			Robot.drivetrain.resetOpticalEncoder();
    	    	Robot.drivetrain.resetMagneticEncoder();
    	    	DeliverFirstCubeScaleCurve.ACTIVATE_LIFT = true;
    		} else {
    			Robot.drivetrain.drive(-currentDrivePower, -currentDrivePower);
    		}
    	} else if(currentLeg == 3) {
    		if(currentInnerTurnPower < targetDrivePower) {
    			currentInnerTurnPower += 0.05;
    		}
    		
    		if(Math.abs(Robot.drivetrain.getOpticalDistanceInches() - distanceToScale) < 3) {
    			// close enough, coast to stop
    			currentInnerTurnPower = 0.0;
    			currentDrivePower = 0.0;
    			isFinished = true;
    		} else if(Math.abs(Robot.drivetrain.getOpticalDistanceInches() - distanceToScale) < 12) {
    			// try to decelerate
    			currentInnerTurnPower -= 0.05;
    			currentDrivePower -= 0.05;
    		}
    		Robot.drivetrain.drive(-currentInnerTurnPower, -currentDrivePower);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
