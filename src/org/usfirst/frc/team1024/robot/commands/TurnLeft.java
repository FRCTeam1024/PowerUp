package org.usfirst.frc.team1024.robot.commands;

public class TurnLeft extends TurnRelative {

	public TurnLeft(int targetHeading) {
		super(-1 * targetHeading);
	}
}
