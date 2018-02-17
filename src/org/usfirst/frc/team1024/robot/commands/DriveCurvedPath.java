package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.path.FalconPathPlanner;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveCurvedPath extends Command {
	
	private FalconPathPlanner pathPlanner;
	private int indexCount = 0;
	private double totalTime = 3;

    public DriveCurvedPath() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	double[][] scaleToSwitchCubePath = new double[][] {
			{0, 0},
			{0, 3},
			{3, 6},
			{3, 9}
    	};
		
		totalTime = 3; //seconds
		double timeStep = 0.02; //period of control loop on Rio, seconds
		double robotTrackWidth = 2; //distance between left and right wheels, feet

		pathPlanner = new FalconPathPlanner(scaleToSwitchCubePath);
		pathPlanner.calculate(totalTime, timeStep, robotTrackWidth);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// the array outputs in velocity in feet/second; we need to convert that to a power percentage
    	// assuming our max velocity is 15 ft/s
    	double maxVelocity = 15.0;
    	double leftVelocity = pathPlanner.smoothLeftVelocity[indexCount][1];
    	double leftPower = maxVelocity / leftVelocity;
    	double rightVelocity = pathPlanner.smoothRightVelocity[indexCount][1];
    	double rightPower = maxVelocity / rightVelocity;
    	Robot.drivetrain.drive(leftPower, rightPower);
    	indexCount++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return indexCount >= pathPlanner.smoothLeftVelocity.length;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
