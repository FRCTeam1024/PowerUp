package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoSwitchMiddle extends CommandGroup {
	private double WALL_TO_SWITCH_DROPOFF_DISTANCE = 168.0 - RobotMap.ROBOT_LENGTH_IN;
	private int INITIAL_DRIVE_DISTANCE = 12;
	
	/*
	 * switchSide 1 = Left
	 * 			-1 = Right
	 * 
	 * position = distance of robot from the left wall in inches. ADD 17.75 TO COMPENSATE FROM BUMPER TO MIDDLE
	 * 
	 * switchPos = Distance of the switch drop position from the left wall in inches.
	 * 			65.75 = Left drop position
	 * 		   257.63 = Right drop position
	 */
    public AutoSwitchMiddle(int position, int switchSide, int switchPos) {
    	addSequential(new DriveStraight(INITIAL_DRIVE_DISTANCE));
    	addSequential(new TurnRelative((-90 * switchSide)));
    	addSequential(new DriveStraight(Math.abs(position - switchPos) + 12));
    	addSequential(new TurnRelative(90 * switchSide));
    	addSequential(new DriveStraight(WALL_TO_SWITCH_DROPOFF_DISTANCE -INITIAL_DRIVE_DISTANCE));
    	addSequential(new TurnRelative(90 * switchSide));
    	addSequential(new DriveStraight(12));
    	//TODO Drop Cube
    	
    }
}
