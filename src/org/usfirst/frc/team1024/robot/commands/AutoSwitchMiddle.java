package org.usfirst.frc.team1024.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchMiddle extends CommandGroup {
	private int WALL_TO_SWITCH_DISTANCE = 168;
	private int INITIAL_DRIVE_DISTANCE = 12;
	
	/*
	 * switchSide 1 = Left
	 * 			-1 = Right
	 * 
	 * position = distance of robot from the left wall in inches. ADD 17.75 TO COMPENSATE FROM BUMPER TO MIDDLE
	 * 
	 * switchPos = Distance of the switch drop position from the left wall in inches.
	 */
    public AutoSwitchMiddle(int position, int switchSide, int switchPos) {
    	addSequential(new DriveDistance(INITIAL_DRIVE_DISTANCE));
    	addSequential(new TurnRelative(-90 * switchSide));
    	addSequential(new DriveDistance(Math.abs(position - switchPos) + 12));
    	addSequential(new TurnRelative(90 * switchSide));
    	addSequential(new DriveDistance(WALL_TO_SWITCH_DISTANCE -INITIAL_DRIVE_DISTANCE));
    	addSequential(new TurnRelative(90 * switchSide));
    	addSequential(new TurnRelative(12));
    	//TODO Drop Cube
    	
    }
}
