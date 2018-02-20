package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndMoveLift extends CommandGroup {
    public DriveAndMoveLift(double distance, Level level) {
    	addParallel(new DriveStraight(distance));
    	addSequential(new MoveLiftPID(level));
    }
}
