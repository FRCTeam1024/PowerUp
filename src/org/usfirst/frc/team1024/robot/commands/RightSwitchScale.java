package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.auto.right.DeliverZaneFirstCubeToScale;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchScale extends CommandGroup {

    public RightSwitchScale() {
    	if (Robot.fieldConfig.isSwitchRight()) {
			// Run right switch auto
			addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_END_OF_SWITCH_INCHES), 5);
			addSequential(new TurnLeftAndLift(90.0, Level.SWITCH), 2);
			addSequential(new DriveStraight(25.0), 2);
			addSequential(new OpenClamp());
		} else {
			addSequential(new CrossTest());
		}
    }
}
