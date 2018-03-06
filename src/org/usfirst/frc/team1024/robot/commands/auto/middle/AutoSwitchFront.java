package org.usfirst.frc.team1024.robot.commands.auto.middle;
import edu.wpi.first.wpilibj.command.CommandGroup;
import java.lang.Math;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.FieldConfig;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRelative;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;
/**
 *
 */
public class AutoSwitchFront extends CommandGroup {
	private double WALL_TO_SWITCH_DROPOFF_DISTANCE = 140.0 - Constants.ROBOT_LENGTH_IN;
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
       	/*addSequential(new DriveStraight(INITIAL_DRIVE_DISTANCE));
       	if(Robot.fieldConfig.isSwitchRight()) {
       		addSequential(new TurnRight(90, 5.0));
       	}
       	else {
       		addSequential(new TurnLeft(90, 5.0));
       	}
    	addSequential(new DriveStraight(Math.abs(position - switchPos)));
    	if(Robot.fieldConfig.isSwitchRight()) {
    		addSequential(new TurnLeftAndLift(90, 5.0, Level.SWITCH));    	}
    	else {
    		addSequential(new TurnRightAndLift(90, 5.0, Level.SWITCH));    	}
    	addSequential(new DriveStraight(WALL_TO_SWITCH_DROPOFF_DISTANCE - INITIAL_DRIVE_DISTANCE));
    	addSequential(new OpenClamp());*/
    	
    	addSequential(new TurnRight(90, 1.0));
    	addSequential(new OpenClamp());
    }
}

