package org.usfirst.frc.team1024.robot.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;
import java.lang.Math;
/**
 *
 */
public class AutoSwitchFront extends CommandGroup {
	private int WALL_TO_SWITCH_DISTANCE = 140;
	private int INITIAL_DRIVE_DISTANCE = 12;
	
	/*
	 * switchSide 1 = Left
	 * 			-1 = Right
	 * 
	 * position = distance of robot from the left wall in inches.
	 * 
	 * switchPos = Distance of the switch drop position from the left wall in inches.
	 */
    public AutoSwitchFront(int position, int switchSide, int switchPos) {
       	addSequential(new DriveDistance(INITIAL_DRIVE_DISTANCE));
    	addSequential(new TurnRelative(-90 * switchSide));
    	addSequential(new DriveDistance(Math.abs(position - switchPos)));
    	addSequential(new TurnRelative(90 * switchSide));
    	addSequential(new DriveDistance(WALL_TO_SWITCH_DISTANCE - INITIAL_DRIVE_DISTANCE));    	
    }
}
