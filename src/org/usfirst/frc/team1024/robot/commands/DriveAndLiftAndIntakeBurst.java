package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeAcquire;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndLiftAndIntakeBurst extends CommandGroup {

	public DriveAndLiftAndIntakeBurst(double distance, Level level, double intakeTimeOut) {
		addParallel(new DriveAndMoveLift(distance, level));
		addSequential(new IntakeAcquireBurst(), intakeTimeOut);
	}
}
