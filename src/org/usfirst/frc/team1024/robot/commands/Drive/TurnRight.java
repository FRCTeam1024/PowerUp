package org.usfirst.frc.team1024.robot.commands.Drive;

public class TurnRight extends TurnRelative {
	
	public TurnRight(double targetHeading) {
		super(targetHeading);
	}
	
	public TurnRight(double targetHeading, double degreeTolerance) {
		super(targetHeading, degreeTolerance);
	}
}
