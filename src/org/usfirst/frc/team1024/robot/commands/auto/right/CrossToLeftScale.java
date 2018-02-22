package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.AutoDriveConstants;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.ShiftLow;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.lift.MoveLiftPID;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * All routines in this package should be starting from the right-most fixed starting position
 * 
 * Drives past the switch, crosses to left side between scale and switch, turns to scale, places cube on scale
 */
public class CrossToLeftScale extends CommandGroup {

    public CrossToLeftScale() {
    	addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES));
    	addSequential(new TurnLeft(90, 5.0));
    	addSequential(new DriveAndShift(15.5 * 12)); 
    	// turn right to face scale
    	addSequential(new TurnRight(90, 5.0));
    	// drive forward to scale
    	//addSequential(new MoveLiftPID(Level.SCALE_LOSS), 2);
    	addSequential(new DriveStraight(48));
    	addSequential(new OpenClamp());
    }
}
