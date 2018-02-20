package org.usfirst.frc.team1024.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveUntilAndShift extends CommandGroup {

    public DriveUntilAndShift(double distance, double initialPower) {
    	addSequential(new ShiftHigh());
    	addSequential(new DriveUntilPID(distance, initialPower));
    }
}
