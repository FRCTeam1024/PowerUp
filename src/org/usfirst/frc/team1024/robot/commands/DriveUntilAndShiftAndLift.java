package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveUntilAndShiftAndLift extends CommandGroup {

    public DriveUntilAndShiftAndLift(double distance, double initialPower, Level level) {
    	addParallel(new MoveLiftPID(level));
    	addSequential(new DriveUntilAndShift(distance, initialPower));
    }
}
