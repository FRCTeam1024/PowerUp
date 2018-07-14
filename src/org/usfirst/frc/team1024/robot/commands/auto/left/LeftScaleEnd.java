package org.usfirst.frc.team1024.robot.commands.auto.left;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.FieldConfig.POSITION;
import org.usfirst.frc.team1024.robot.commands.AutoDriveConstants;
import org.usfirst.frc.team1024.robot.commands.CrossTest;
import org.usfirst.frc.team1024.robot.commands.DriveAndMoveLift;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScale;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverFirstCubeToSwitch;
import org.usfirst.frc.team1024.robot.commands.lift.ChangeLiftSpeed;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleEnd extends CommandGroup {

	public LeftScaleEnd() {
		if (Robot.fieldConfig.isScaleLeft()) {
			addSequential(new ChangeDriveSpeed(1.0));
			addSequential(new ChangeLiftSpeed(1.0));
			addSequential(new DriveAndShift(
					Constants.BACKWALL_TO_MIDDLE_SCALE_DISTANCE - (Constants.ROBOT_LENGTH_IN / 2) - 20.0));
			addSequential(new ChangeDriveSpeed(0.5));
			// turn towards scale

			addSequential(new TurnRight(90.0), 2);
			addSequential(new DriveAndMoveLift(-17.0, Level.SCALE_LOSS), 2);
			addSequential(new DriveStraight(29.0));
			// drop the cube on to the scale
			addSequential(new OpenClamp());

		} else {
			if(Robot.fieldConfig.isSwitchLeft()) {
			    addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_END_OF_SWITCH_INCHES));
			    addSequential(new TurnRightAndLift(90.0, Level.SWITCH));
			    addSequential(new DriveStraight(25.0));
				addSequential(new OpenClamp());
			}
			else {
				addSequential(new CrossTest());
			}
		}
	}
}