package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.AutoDriveConstants;
import org.usfirst.frc.team1024.robot.commands.Delay;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.MoveLiftPID;
import org.usfirst.frc.team1024.robot.commands.OpenClamp;
import org.usfirst.frc.team1024.robot.commands.TurnAbsolute;
import org.usfirst.frc.team1024.robot.commands.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.TurnRight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * All routines in this package should be starting from the right-most fixed starting position
 * 
 * Drives past the switch, crosses to left side between scale and switch, turns to scale, places cube on scale
 */
public class CrossToLeftScale extends CommandGroup {

    public CrossToLeftScale() {
    	requires(Robot.drivetrain);
    	addSequential(new DriveStraight(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES));
    	addSequential(new TurnLeft(90));
    	addSequential(new DriveStraight(15.5 * 12)); 
    	// turn right to face scale
    	addSequential(new TurnRight(90));
    	// drive forward to scale
    	addSequential(new MoveLiftPID(Level.SCALE_LOSS), 2);
    	addSequential(new DriveStraight(48));
    	addSequential(new OpenClamp());
    }
}
