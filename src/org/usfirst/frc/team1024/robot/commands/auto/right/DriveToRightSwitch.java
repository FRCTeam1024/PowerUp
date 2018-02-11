package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.TurnAbsolute;
import org.usfirst.frc.team1024.robot.commands.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToRightSwitch extends CommandGroup {

    public DriveToRightSwitch() {
    	requires(Robot.drivetrain);
    	addSequential(new DriveStraight(146));
    	addSequential(new TurnLeft(90));
    	addSequential(new DriveStraight(20));

    	// TODO put cube on switch
    	/* To scale:
    	 * addSequential(new DriveStraight(-10));
    	 * addSequential(new TurnRelative(90));
    	 * addSequential(new DriveStraight(20));
    	 * 
    	 */
    }
}
