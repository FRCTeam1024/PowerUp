package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndTurn extends CommandGroup {

    public DriveAndTurn() {
    	requires(Robot.drivetrain);
    	addSequential(new DriveStraight(48));
    	addSequential(new TurnRelative(-90));
    	addSequential(new DriveStraight(24));
    	addSequential(new TurnRelative(-90));
    	addSequential(new DriveStraight(48));
    }
}
