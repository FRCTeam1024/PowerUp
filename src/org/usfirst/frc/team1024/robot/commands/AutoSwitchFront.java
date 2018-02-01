package org.usfirst.frc.team1024.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import java.lang.Math;

import org.usfirst.frc.team1024.robot.Constants;
/**
 *
 */
public class AutoSwitchFront extends CommandGroup {
	private double WALL_TO_SWITCH_DROPOFF_DISTANCE = 140.0 - Constants.ROBOT_LENGTH_IN;
	private int INITIAL_DRIVE_DISTANCE = 12;
	
	/*
	 * switchSide 1 = Left
	 * 			-1 = Right
	 * 
	 * position = distance of robot from the left wall in inches. ADD 17.75 TO COMPENSATE FROM BUMPER TO MIDDLE
	 * 
	 * switchPos = Distance of the switch drop position from the left wall in inches.
	 * 			94.25 = Left
	 * 			229.13 = Right
	 */
    public AutoSwitchFront(int position, int switchSide, int switchPos) {
       	addSequential(new DriveDistance(INITIAL_DRIVE_DISTANCE));
    	addSequential(new TurnRelative(-90 * switchSide));
    	addSequential(new DriveDistance(Math.abs(position - switchPos)));
    	addSequential(new TurnRelative(90 * switchSide));
    	addSequential(new DriveDistance(WALL_TO_SWITCH_DROPOFF_DISTANCE - INITIAL_DRIVE_DISTANCE));
    	//TODO drop cube
    }
}

