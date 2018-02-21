package org.usfirst.frc.team1024.robot.commands.Drive;

import org.usfirst.frc.team1024.robot.Robot;

public class TurnLeft extends TurnRelative {

	public TurnLeft(double targetHeading) {
		super(-1 * targetHeading);
	}
	
	public TurnLeft(double targetHeading, double degreeTolerance) {
		super(-1 * targetHeading, degreeTolerance);
	}
	
	public TurnLeft(double targetHeading, double degreeTolerance, double speed) {
		super(-1 * targetHeading, degreeTolerance);
		Robot.drivetrain.turnPID.setOutputRange(-speed, speed);
	}
	
}
