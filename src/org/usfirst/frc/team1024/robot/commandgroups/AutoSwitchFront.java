package org.usfirst.frc.team1024.robot.commandgroups;
import edu.wpi.first.wpilibj.command.CommandGroup;
import java.lang.Math;

import org.usfirst.frc.team1024.robot.RobotMap;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.TurnRelative;
import org.usfirst.frc.team1024.robot.Robot;
/**
 *
 */
public class AutoSwitchFront extends CommandGroup {
	private double WALL_TO_SWITCH_DROPOFF_DISTANCE = 140.0 - RobotMap.ROBOT_LENGTH_IN;
	private int INITIAL_DRIVE_DISTANCE = 36;
	
	/*
	 * switchSide -1 = Left
	 * 			1 = Right
	 * 
	 * position = distance of robot from the left wall in inches. ADD 17.75 TO COMPENSATE FROM BUMPER TO MIDDLE
	 * 
	 * switchPos = Distance of the switch drop position from the left wall in inches.
	 * 			94.25 = Left
	 * 			229.13 = Right
	 */
    public AutoSwitchFront(double position, double switchPos) {
    	int switchSide = Robot.fieldConfig.getSwitchPosition();
       	addSequential(new DriveStraight(INITIAL_DRIVE_DISTANCE));
    	addSequential(new TurnRelative(90 * switchSide));
    	addSequential(new DriveStraight(Math.abs(position - switchPos)));
    	addSequential(new TurnRelative(-90 * switchSide));
    	addSequential(new DriveStraight(WALL_TO_SWITCH_DROPOFF_DISTANCE - INITIAL_DRIVE_DISTANCE));
    	//TODO drop cube
    }
}

