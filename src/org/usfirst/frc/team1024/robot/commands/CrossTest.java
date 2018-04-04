package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossTest extends CommandGroup {

    public CrossTest() {
    	addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES + 3 + 12), 5);
    	addSequential(new TurnLeft(90, 1.0), 2);
    	
    }
}
