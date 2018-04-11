package org.usfirst.frc.team1024.robot.commands.bidirectional;

import org.usfirst.frc.team1024.robot.FieldConfig;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.FieldConfig.POSITION;
import org.usfirst.frc.team1024.robot.commands.AutoDriveConstants;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRelative;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 
 */
public class DeliverFirstCubeToSwitch extends CommandGroup {

    public DeliverFirstCubeToSwitch(FieldConfig.POSITION switchSide) {
    	addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_END_OF_SWITCH_INCHES));
    	if(POSITION.LEFT.equals(switchSide)) {
    		addSequential(new TurnRightAndLift(90.0, Level.SWITCH));
    	} else if(POSITION.RIGHT.equals(switchSide)) {
    		addSequential(new TurnLeftAndLift(90.0, Level.SWITCH));
    	}
    	addSequential(new DriveStraight(25.0));
    	addSequential(new OpenClamp());
    }
}
