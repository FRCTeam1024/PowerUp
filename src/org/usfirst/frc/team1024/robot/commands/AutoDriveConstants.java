package org.usfirst.frc.team1024.robot.commands;

public class AutoDriveConstants {
	public final static int BACK_WALL_TO_END_OF_SWITCH_INCHES = 146; // depends on depth of robot
	public final static int SWITCH_TO_SCALE_DIST_INCHES = 150;
	public final static int BACK_WALL_TO_END_OF_SCALE_INCHES = BACK_WALL_TO_END_OF_SWITCH_INCHES + SWITCH_TO_SCALE_DIST_INCHES;
	
	public final static int SWITCH_TO_CROSSING_PATH_INCHES = 66;
	public final static int BACK_WALL_TO_CROSSING_PATH_INCHES = BACK_WALL_TO_END_OF_SWITCH_INCHES + SWITCH_TO_CROSSING_PATH_INCHES;
}