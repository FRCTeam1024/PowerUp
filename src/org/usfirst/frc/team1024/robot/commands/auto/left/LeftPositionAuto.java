package org.usfirst.frc.team1024.robot.commands.auto.left;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.TurnAbsolute;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftPositionAuto extends CommandGroup {

    public LeftPositionAuto() {
    	requires(Robot.drivetrain);
    	addSequential(new DriveStraight(140));
    	addSequential(new TurnAbsolute(90));
    	addSequential(new DriveStraight(6));
      	// TODO put cube on switch
    }
}
