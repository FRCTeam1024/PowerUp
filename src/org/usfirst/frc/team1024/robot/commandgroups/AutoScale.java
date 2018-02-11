package org.usfirst.frc.team1024.robot.commandgroups;

import org.usfirst.frc.team1024.robot.RobotMap;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.TurnRelative;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoScale extends CommandGroup {
	private double WALL_TO_SCALE_DROPOFF_DISTANCE = 324 - RobotMap.ROBOT_LENGTH_IN;
	private int INITIAL_DRIVE_DISTANCE = 12;
	
	/*
	 * scaleSide 1 = Left
	 * 			-1 = Right
	 * 
	 * position = distance of robot from the left wall in inches. ADD 17.75 TO COMPENSATE FROM BUMPER TO MIDDLE
	 * 
	 * scalePos = Distance of the scale drop position from the left wall in inches.
	 * 		    52.07 = Left drop position
	 * 		   271.31 = Right drop position
	 */
    public AutoScale(int position, int scaleSide, int scalePos) {
    	addSequential(new DriveStraight(INITIAL_DRIVE_DISTANCE));
    	addSequential(new TurnRelative(-90 * scaleSide));
    	addSequential(new DriveStraight(Math.abs(position - scalePos) + 12));
    	addSequential(new TurnRelative(90 * scaleSide));
    	addSequential(new DriveStraight(WALL_TO_SCALE_DROPOFF_DISTANCE -INITIAL_DRIVE_DISTANCE));
    	addSequential(new TurnRelative(90 * scaleSide));
    	//TODO Lift Cube
    	addSequential(new DriveStraight(12));
    	//TODO Drop Cube
    	
    }
}
