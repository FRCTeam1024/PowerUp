package org.usfirst.frc.team1024.robot.commands.auto.middle;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRelative;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SwitchEitherSide extends CommandGroup {
	private double WALL_TO_SWITCH_DROPOFF_DISTANCE = 68.0 - Constants.ROBOT_LENGTH_IN;
	private int INITIAL_DRIVE_DISTANCE = 12;

    public SwitchEitherSide() {
    	addSequential(new DriveStraight(INITIAL_DRIVE_DISTANCE));
    	if(Robot.fieldConfig.isSwitchLeft()) {
    		addSequential(new TurnLeft(90.0, 5.0));
    	} else if(Robot.fieldConfig.isSwitchRight()) {
    		addSequential(new TurnRight(90, 5));
    	}
    	addSequential(new DriveStraight(72)); // TODO test this for both sides; do we start in the exact middle,
    										  // or is it different for left than right?
    	if(Robot.fieldConfig.isSwitchLeft()) {
    		addSequential(new TurnLeft(90, 5));
    	} else if(Robot.fieldConfig.isSwitchRight()) {
    		addSequential(new TurnRight(90, 5));
    	}
    	addSequential(new DriveStraight(WALL_TO_SWITCH_DROPOFF_DISTANCE - INITIAL_DRIVE_DISTANCE));
    	addSequential(new OpenClamp());
    }
}
