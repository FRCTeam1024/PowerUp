package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.lift.ChangeLiftSpeed;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SemiFinalsSpecial extends CommandGroup {

	public SemiFinalsSpecial() {
		if (Robot.fieldConfig.isScaleLeft()) {
			addSequential(new ChangeDriveSpeed(1.0));
			addSequential(new ChangeLiftSpeed(1.0));
			addSequential(new DriveAndShift(Constants.BACKWALL_TO_MIDDLE_SCALE_DISTANCE - (Constants.ROBOT_LENGTH_IN / 2) - 20.0));
			addSequential(new ChangeDriveSpeed(0.5));
			// turn towards scale
			addSequential(new TurnRight(90), 2);
			addSequential(new DriveAndMoveLift(-12.0, Level.SCALE_LOSS), 2);
			addSequential(new DriveStraight(29.0));
			// drop the cube on to the scale
			addSequential(new OpenClamp());
		} else {
			addSequential(new ChangeDriveSpeed(1.0));
			addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES + 9.0));
			addSequential(new TurnRight(90.0), 2);
			if (Robot.stayOnOurSide == false) {
				addSequential(new ChangeDriveSpeed(0.6)); // This could be faster probably
				addSequential(new DriveAndShift(195.0), 5);
				// turn right to face scale
				addSequential(new TurnLeftAndLift(90.0, Level.SCALE_LOSS), 5);
				// drive forward to scale
				addSequential(new DriveStraight(45.0), 2);
				addSequential(new OpenClamp());
			}
		}
	}
}
