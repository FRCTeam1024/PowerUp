package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.AutoDriveConstants;
import org.usfirst.frc.team1024.robot.commands.Delay;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
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
    	addSequential(new ChangeDriveSpeed(1.0));
    	addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES + 3));
    	addSequential(new TurnLeft(90, 1.0), 2);
    	addSequential(new ChangeDriveSpeed(0.6));
    	addSequential(new DriveAndShift((15.5 * 12) + 9), 5); 
    	// turn right to face scale
    	addSequential(new TurnRightAndLift(90.0, 1.0, Level.SCALE_LOSS), 5);
    	// drive forward to scale
    	addSequential(new DriveStraight(45), 2);
    	addSequential(new Delay(.5));
    	addSequential(new OpenClamp());
    }
}
