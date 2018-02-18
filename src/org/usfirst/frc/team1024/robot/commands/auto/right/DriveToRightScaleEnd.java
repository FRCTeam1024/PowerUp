package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.AutoDriveConstants;
import org.usfirst.frc.team1024.robot.commands.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.ChangeLiftSpeed;
import org.usfirst.frc.team1024.robot.commands.ChangeRamp;
import org.usfirst.frc.team1024.robot.commands.ChangeTurnSpeed;
import org.usfirst.frc.team1024.robot.commands.DriveAndMoveLift;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.DriveAndShiftAndLift;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.DriveUntilAndMoveLift;
import org.usfirst.frc.team1024.robot.commands.DriveUntilAndShiftAndLift;
import org.usfirst.frc.team1024.robot.commands.DriveUntilPID;
import org.usfirst.frc.team1024.robot.commands.OpenClamp;
import org.usfirst.frc.team1024.robot.commands.SetCoast;
import org.usfirst.frc.team1024.robot.commands.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * All routines in this package should be starting from the right-most fixed starting position
 * 
 * Drives to the end (aka the middle) of the scale
 */
public class DriveToRightScaleEnd extends CommandGroup {

    public DriveToRightScaleEnd() {
    	requires(Robot.drivetrain);
    	addSequential(new ChangeRamp(0.35));
    	//addSequential(new ChangeRamp(1));
    	//addSequential(new SetCoast());
    	addSequential(new ChangeDriveSpeed(1.0));
    	addSequential(new DriveStraight(100));
    	//addSequential(new DriveUntilPID(140 - Constants.ROBOT_LENGTH_IN, 0.5));
    	//addSequential(new DriveUntilAndMoveLift(12, 0.5, Level.SWITCH));
    	/*addSequential(new DriveAndMoveLift(134, Level.SWITCH));
    	//addSequential(new DriveAndShiftAndLift(147, Level.SWITCH));
    	//addSequential(new DriveUntilAndShiftAndLift(AutoDriveConstants.BACK_WALL_TO_END_OF_SWITCH_INCHES, 1.0, Level.SWITCH));
    	addSequential(new ChangeDriveSpeed(0.75));
    	addSequential(new ChangeLiftSpeed(0.5));
    	addSequential(new DriveAndMoveLift(100,
    									   Level.SCALE_NEUTRAL));
    	addSequential(new ChangeTurnSpeed(0.5));
    	addSequential(new TurnLeft(90, 5));
    	addSequential(new DriveStraight(12));
    	addSequential(new OpenClamp());*/
    	// TODO put cube on scale
    }
}
