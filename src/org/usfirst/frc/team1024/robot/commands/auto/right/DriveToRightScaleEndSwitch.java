package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveAndIntake;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.DriveAndShiftAndLift;
import org.usfirst.frc.team1024.robot.commands.PrintToConsole;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeTurnSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.ShiftLow;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * All routines in this package should be starting from the right-most fixed starting position
 * 
 * Drives to the end (aka the middle) of the scale
 */
public class DriveToRightScaleEndSwitch extends CommandGroup {

    public DriveToRightScaleEndSwitch() {
    	requires(Robot.drivetrain);
    	addSequential(new ChangeDriveSpeed(1.0));
    	addSequential(new DriveAndShiftAndLift(Constants.BACKWALL_TO_MIDDLE_SCALE_DISTANCE - Constants.ROBOT_LENGTH_IN, 5.0, Level.SWITCH));

    	addSequential(new ShiftLow());
    	addSequential(new TurnLeft(90, 5.0));
    	addSequential(new DriveStraight(12));
    	addSequential(new OpenClamp()); //THIS WILL BREAK THE INTAKE!
    	addSequential(new TurnLeft(80, 5.0));
    	addSequential(new IntakeExtend());
    	
    	addSequential(new DriveAndIntake(100.0, 3.0));
    	addSequential(new CloseClamp());
    	addSequential(new PrintToConsole("Done @ " + DriverStation.getInstance().getMatchTime()));
    }
}
