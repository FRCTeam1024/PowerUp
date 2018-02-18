package org.usfirst.frc.team1024.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndShift extends CommandGroup {

    public DriveAndShift(double distance) {
    	addSequential(new ShiftHigh());
    	addSequential(new DriveStraight(distance));
    	
    }
}
