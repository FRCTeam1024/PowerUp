package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossTest extends CommandGroup {

    public CrossTest() {
    	addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_END_OF_SWITCH_INCHES), 5);
    }
}
