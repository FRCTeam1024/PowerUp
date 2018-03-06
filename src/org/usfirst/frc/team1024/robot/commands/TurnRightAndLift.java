package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.lift.MoveLiftPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TurnRightAndLift extends CommandGroup {
	
	public TurnRightAndLift(double targetHeading, Level level) {
		addParallel(new MoveLiftPID(level));
		addSequential(new TurnRight(targetHeading));
	}
	
    public TurnRightAndLift(double targetHeading, double tolerance, Level level) {
    	addParallel(new MoveLiftPID(level));
    	addSequential(new TurnRight(targetHeading, tolerance));
    }
}
