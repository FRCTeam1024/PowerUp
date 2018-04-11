package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeRetract;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchSwitch extends CommandGroup {

	public RightSwitchSwitch() {
		if (Robot.fieldConfig.isSwitchRight()) {
			addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_END_OF_SWITCH_INCHES));
			addSequential(new TurnLeftAndLift(90.0, Level.SWITCH));
			addSequential(new DriveStraight(25.0));
			addSequential(new OpenClamp());
			addSequential(new TurnLeftOneSide(-90.0));
			addSequential(new CloseClamp());
			addSequential(new IntakeExtend());
			addSequential(new DriveAndMoveLift(96.0, Level.INTAKE), 2);
			addSequential(new TurnLeftOneSide(130));
			addSequential(new DriveAndIntake(36.0, 3.0));
			addSequential(new CloseClamp());
			addSequential(new IntakeRetract());
			addSequential(new MoveLiftToLevel(Level.SWITCH));
			addSequential(new DriveStraight(12.0));
			if ("Yes".equals(Robot.dropCube.getSelected())) {
				addSequential(new OpenClamp());
			}
		} else {
			addSequential(new CrossTest());
		}
	}
}
