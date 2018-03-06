package org.usfirst.frc.team1024.robot.commands.auto.left;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.AutoDriveConstants;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
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
public class LeftCrossToRightScale extends CommandGroup {

    public LeftCrossToRightScale() {
    	addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES + 3));
    	addSequential(new TurnRight(90, 5.0));
    	addSequential(new DriveAndShift(15.5 * 12 + 16)); 
    	// turn right to face scale
    	addSequential(new TurnLeftAndLift(90, 5.0, Level.SCALE_NEUTRAL));
    	// drive forward to scale
    	//addSequential(new MoveLiftPID(Level.SCALE_LOSS), 2);
    	addSequential(new DriveStraight(57));
    	addSequential(new OpenClamp());
    }
}
