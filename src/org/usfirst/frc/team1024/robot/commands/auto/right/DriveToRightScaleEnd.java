package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveAndIntake;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.PrintToConsole;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangePosPID;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeTurnSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.ShiftLow;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * All routines in this package should be starting from the right-most fixed starting position
 * 
 * Drives to the end (aka the middle) of the scale
 */
public class DriveToRightScaleEnd extends CommandGroup {

    public DriveToRightScaleEnd() {
    	requires(Robot.drivetrain);
    	addSequential(new ChangeDriveSpeed(1.0));
    	addSequential(new DriveAndShift(Constants.BACKWALL_TO_MIDDLE_SCALE_DISTANCE - Constants.ROBOT_LENGTH_IN, 5.0));


    	//addSequential(new DriveUntilPID(140 - Constants.ROBOT_LENGTH_IN, 0.5));
    	//addSequential(new DriveUntilAndMoveLift(12, 0.5, Level.SWITCH));
    	/*addSequential(new DriveAndMoveLift(134, Level.SWITCH));
    	//addSequential(new DriveAndShiftAndLift(147, Level.SWITCH));
    	//addSequential(new DriveUntilAndShiftAndLift(AutoDriveConstants.BACK_WALL_TO_END_OF_SWITCH_INCHES, 1.0, Level.SWITCH));
    	addSequential(new ChangeDriveSpeed(0.75));
    	addSequential(new ChangeLiftSpeed(0.5));
    	addSequential(new DriveAndMoveLift(100,
    									   Level.SCALE_NEUTRAL));
    									   */
    	addSequential(new ChangeTurnSpeed(1.0));
    	addSequential(new ShiftLow());
    	addSequential(new TurnLeft(90, 5.0));
    	addSequential(new DriveStraight(12));
    	addSequential(new OpenClamp()); //THIS WILL BREAK THE INTAKE!
    	addSequential(new TurnLeft(80, 5.0));
    	addSequential(new IntakeExtend());
    	
    	addSequential(new DriveAndIntake(100.0, 3.0));
    	addSequential(new CloseClamp());
    	addSequential(new PrintToConsole("Done @ " + DriverStation.getInstance().getMatchTime()));
    	// TODO put cube on scale
    }
}
