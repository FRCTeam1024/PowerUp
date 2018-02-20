package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveUntilAndMoveLift extends CommandGroup {

    public DriveUntilAndMoveLift(double distance, double initialPower, Level level) {
    	addParallel(new DriveUntilPID(distance, initialPower));
    	addSequential(new MoveLiftPID(level));
    }
}
