package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndShiftAndLift extends CommandGroup {

    public DriveAndShiftAndLift(double distance, Level level) {
    	addParallel(new MoveLiftPID(level));
    	addSequential(new DriveAndShift(distance));
    }
}
