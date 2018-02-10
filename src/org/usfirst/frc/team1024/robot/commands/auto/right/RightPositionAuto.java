package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.TurnAbsolute;
import org.usfirst.frc.team1024.robot.commands.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightPositionAuto extends CommandGroup {

    public RightPositionAuto() {
    	requires(Robot.drivetrain);
    	addSequential(new DriveStraight(140));
    	addSequential(new TurnLeft(90));
    	addSequential(new DriveStraight(6));
    	// TODO put cube on switch
    }
}