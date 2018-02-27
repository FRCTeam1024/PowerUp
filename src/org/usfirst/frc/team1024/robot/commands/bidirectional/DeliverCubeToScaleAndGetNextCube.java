package org.usfirst.frc.team1024.robot.commands.bidirectional;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.FieldConfig;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveAndLiftAndIntake;
import org.usfirst.frc.team1024.robot.commands.DriveAndShiftAndLift;
import org.usfirst.frc.team1024.robot.commands.PrintToConsole;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.ShiftLow;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.lift.ChangeLiftSpeed;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Copied from latest DriveToRightScaleEnd, and then modified to work both sides
 * 
 * Delivers starting cube to scale, then goes and picks up cube at end of switch wall
 * THIS HAS NOT BEEN TESTED YET - remove this when it has
 */
public class DeliverCubeToScaleAndGetNextCube extends CommandGroup {

    public DeliverCubeToScaleAndGetNextCube() {
    	addSequential(new ChangeDriveSpeed(1.0));
    	addSequential(new ChangeLiftSpeed(0.25));
    	addSequential(new DriveAndShiftAndLift(Constants.BACKWALL_TO_MIDDLE_SCALE_DISTANCE - Constants.ROBOT_LENGTH_IN, 5.0, Level.SCALE_NEUTRAL));

    	addSequential(new ShiftLow());
    	// turn towards scale
    	if(Robot.fieldConfig.isScaleRight()) {
    		addSequential(new TurnLeft(90, 5.0));
    	} else if(Robot.fieldConfig.isScaleLeft()) {
    		addSequential(new TurnRight(90, 5.0));
    	}
    	addSequential(new DriveStraight(12));
    	// drop the cube on to the scale
    	addSequential(new OpenClamp()); //THIS WILL BREAK THE INTAKE! Not sure anymore!
    	
    	// go get the nearest cube on the end of the switch wall
    	addSequential(new ChangeLiftSpeed(1.0));
    	if(Robot.fieldConfig.isScaleRight()) {
    		addSequential(new TurnLeftAndLift(80.0, 5.0, Level.SWITCH));
    	} else if(Robot.fieldConfig.isScaleLeft()) {
    		addSequential(new TurnRightAndLift(80.0, 5.0, Level.SWITCH));
    	}
    	addSequential(new CloseClamp()); // why do we have to do this?
    	addSequential(new IntakeExtend());
    	
    	addSequential(new DriveAndLiftAndIntake(100.0, Level.INTAKE, 3.0));
    	addSequential(new CloseClamp());
    	addSequential(new PrintToConsole("Done @ " + DriverStation.getInstance().getMatchTime()));
    }
}
