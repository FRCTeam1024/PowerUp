package org.usfirst.frc.team1024.robot.commands;

import java.math.RoundingMode;
import java.text.DecimalFormat;

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
		
		log("DriveCurvedPath ctor, path length: " + pathPlanner.smoothLeftVelocity.length);
    }

    private void log(String msg) {
    	System.out.println(msg);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.shiftLow();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// the array outputs in velocity in feet/second; we need to convert that to a power percentage
    	// assuming our max velocity is 15 ft/s
    	// this is making it an inverse relationship, so as velocity increases, power decreases
    	
    	// so just try to divide by 10 so that the ratios of velocity outputs stays the same
    	// i.e. velocity of 3.1 becomes power of .31, velocity of 3.3 becomes power .33
    	double maxVelocity = 10.0;
    	double leftVelocity = pathPlanner.smoothLeftVelocity[indexCount][1];
    	DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);
		
		log("ITERATION " + indexCount);
		
		// we need a minimum of 0.5 percent power to really move
		// when dividing by 10.0, our % maxes around 58%
		// tried basePower - 0.50; went way too fast
		maxVelocity = 11.0;
		double basePower = 0.30;
    	
		//log("iteration " + indexCount + ", leftVelocity " + leftVelocity);
    	double leftPower = basePower + (leftVelocity / maxVelocity);
    	log("          leftPower " + df.format(leftPower));
    	double rightVelocity = pathPlanner.smoothRightVelocity[indexCount][1];
    	// log("          rightVelocity " + rightVelocity);
    	double rightPower = basePower + ( rightVelocity / maxVelocity );
    	log("          rightPower " + df.format(rightPower));
    	// motors are still inverted for some reason, have to pass power * -1 to go forward
    	Robot.drivetrain.drive((-1 * leftPower), (-1 * rightPower));
    	indexCount++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	Robot.drivetrain.shiftHigh();
        return indexCount >= pathPlanner.smoothLeftVelocity.length;
    }

}
