package org.usfirst.frc.team1024.robot.commands.auto.left;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.TurnAbsolute;
import org.usfirst.frc.team1024.robot.commands.TurnRelative;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftPositionAuto extends CommandGroup {

    public LeftPositionAuto() {
    	requires(Robot.drivetrain);
    	addSequential(new DriveStraight(140));
    	addSequential(new TurnRelative(90));
    	addSequential(new DriveStraight(10));
      	// TODO put cube on switch
    	/* To scale:
    	 * addSequential(new DriveStraight(-10));
    	 * addSequential(new TurnRelative(-90));
    	 * addSequential(new DriveStraight(20));
    	 */
    }
}
