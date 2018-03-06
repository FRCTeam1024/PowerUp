package org.usfirst.frc.team1024.robot.commandgroups;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndTurn extends CommandGroup {

    public DriveAndTurn() {
    	requires(Robot.drivetrain);
    	addSequential(new DriveStraight(48));
    	addSequential(new TurnRight(90));
    	addSequential(new DriveStraight(24));
    	addSequential(new TurnRight(90));
    	addSequential(new DriveStraight(48));
    }
}
