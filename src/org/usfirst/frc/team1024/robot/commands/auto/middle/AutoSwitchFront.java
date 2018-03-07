package org.usfirst.frc.team1024.robot.commands.auto.middle;
import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

/**
 * Delivers cube to switch from starting in middle position
 * Delivers to either side, depending on fieldConfig
 * Start position is adjacent to the exchange zone
 */
public class AutoSwitchFront extends CommandGroup {
	private double WALL_TO_SWITCH_DROPOFF_DISTANCE = 144.0 - Constants.ROBOT_LENGTH_IN;
	private int INITIAL_DRIVE_DISTANCE = 24;
	
	public AutoSwitchFront() {
       	addSequential(new DriveStraight(INITIAL_DRIVE_DISTANCE));
       	
       	if(Robot.fieldConfig.isSwitchRight()) {
       		addSequential(new TurnRight(45, 3.0));
       		addSequential(new DriveStraight(80));
       		addSequential(new TurnLeftAndLift(45, 3.0, Level.SWITCH));   
       		addSequential(new DriveStraight(36), 2);
        } else {
       		addSequential(new TurnLeft(45, 3.0));
       		addSequential(new DriveStraight(85));
       		addSequential(new TurnRightAndLift(45, 3.0, Level.SWITCH));
       		addSequential(new DriveStraight(30), 2);
        }
       	
    	addSequential(new OpenClamp());
    	
    	// this was for testing turn tuning, opening the clamp so we could see the turn was finished
//    	addSequential(new TurnRight(90, 1.0));
//    	addSequential(new OpenClamp());
    }
}

