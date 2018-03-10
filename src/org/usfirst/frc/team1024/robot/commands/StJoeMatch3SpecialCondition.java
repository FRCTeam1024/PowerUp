package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.FieldConfig.POSITION;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.ShiftLow;
import org.usfirst.frc.team1024.robot.commands.auto.right.CrossToLeftScale;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScaleAndGetNextCube;
import org.usfirst.frc.team1024.robot.commands.lift.ChangeLiftSpeed;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StJoeMatch3SpecialCondition extends CommandGroup {

    public StJoeMatch3SpecialCondition() {
        if (Robot.fieldConfig.isSwitchRight()) {
        	//Run right switch auto
        	addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_END_OF_SWITCH_INCHES), 5);
        	addSequential(new TurnLeftAndLift(90, 5.0, Level.SWITCH), 2);
        	addSequential(new DriveStraight(25.0), 2);
        	addSequential(new OpenClamp());
        } else {
        	if (Robot.fieldConfig.isScaleRight()) {
        		addSequential(new DeliverCubeToScaleAndGetNextCube()); //but not really get the second cube
        	} else {
        		addSequential(new CrossToLeftScale());
        	}
        }
        	
    }
}
