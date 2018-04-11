package org.usfirst.frc.team1024.robot.commands.Drive;

import org.usfirst.frc.team1024.robot.Robot;

public class TurnLeft extends TurnRelative {

	public TurnLeft(double targetHeading) {
		super(-1 * targetHeading);
	}
	
	public TurnLeft(double targetHeading, double speed) {
		this(-1 * targetHeading);
		Robot.drivetrain.turnPID.setOutputRange(-speed, speed);
	}
	
}
