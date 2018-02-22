package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeAcquire;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndIntake extends CommandGroup {

    public DriveAndIntake(double distance, double intakeTimeOut) {
    	addParallel(new IntakeAcquire(), intakeTimeOut);
    	addSequential(new DriveStraight(distance));
    }
}
