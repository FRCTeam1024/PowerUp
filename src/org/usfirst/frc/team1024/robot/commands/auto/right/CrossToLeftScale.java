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
    	addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES + 6.0));
    	addSequential(new TurnLeft(90.0), 2);
    	addSequential(new ChangeDriveSpeed(0.6)); //This could be faster probably
    	addSequential(new DriveAndShift(195.0), 5); 
    	// turn right to face scale
    	addSequential(new TurnRightAndLift(90.0, Level.SCALE_LOSS), 5);
    	// drive forward to scale
    	addSequential(new DriveStraight(45.0), 2);
    	addSequential(new Delay(0.5));
    	addSequential(new OpenClamp());
    }
}
