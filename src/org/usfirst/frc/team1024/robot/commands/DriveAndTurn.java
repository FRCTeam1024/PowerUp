package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndTurn extends CommandGroup {

    public DriveAndTurn() {
    	requires(Robot.drivetrain);
    	addSequential(new DriveStraight(30));
    	addSequential(new TurnRelative(90));
    	addSequential(new ResetEncoder());
    	addSequential(new DriveStraight(96));
    	addSequential(new TurnRelative(-90));
    	addSequential(new ResetEncoder());
    }
}
