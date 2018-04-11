package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScale;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchScale extends CommandGroup {

	public LeftSwitchScale() {
		if (Robot.fieldConfig.isSwitchRight()) {
			// Run right switch auto
			addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_END_OF_SWITCH_INCHES), 5);
			addSequential(new TurnLeftAndLift(90, Level.SWITCH), 2);
			addSequential(new DriveStraight(25.0), 2);
			addSequential(new OpenClamp());
		} else {
			if (Robot.fieldConfig.isScaleRight()) {
				addSequential(new DeliverCubeToScale()); // but not really get the second cube
			} else {
				addSequential(new CrossTest());
			}
		}
	}
}
