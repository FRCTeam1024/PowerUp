package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class STurn extends Command {
	int onTargetCount = 0;
	double initialAngle;
	boolean hasShifted = false;
	
	double totalDistance;
	double distanceToFirstTurn;
	double angle;
	double distanceToSecondTurn;
	
	boolean state1 = false;
	boolean state2 = false;
	
    public STurn(double totalDistance, double distanceToFirstTurn, double angle, double distanceToSecondTurn) {
    	this.totalDistance = totalDistance;
    	this.distanceToFirstTurn = distanceToFirstTurn;
    	this.angle = angle;
    	this.distanceToSecondTurn = distanceToSecondTurn;
    }

    protected void initialize() {
    	initialAngle = Robot.drivetrain.getHeading();
    	Robot.drivetrain.resetOpticalEncoder();
    	Robot.drivetrain.resetMagneticEncoder();
    	Robot.drivetrain.posPID.setSetpoint(totalDistance);
    	Robot.drivetrain.trimPID.setSetpoint(initialAngle);
    	Robot.drivetrain.posPID.enable();
    	Robot.drivetrain.trimPID.enable();
    }

    protected void execute() {
    	if (Robot.drivetrain.getOpticalDistanceInches() > 1.0 && !hasShifted) {
    		Robot.drivetrain.shiftHigh();
    		hasShifted = true;
    		SmartDashboard.putNumber("Shifter", Robot.drivetrain.getShiftState() ? 0 : 1);
    	} 
    	
    	if (Robot.drivetrain.getOpticalDistanceInches() > distanceToFirstTurn && hasShifted == true && !state1) {
    		Robot.drivetrain.trimPID.setSetpoint(angle);
    		state1 = true;
    	}
    	
    	if (Robot.drivetrain.getOpticalDistanceInches() > distanceToFirstTurn + distanceToSecondTurn && !state2) {
    		Robot.drivetrain.trimPID.setSetpoint(initialAngle);
    		state2 = true;
    	}
    	
    	Robot.drivetrain.pidDriveForwardStraight();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
