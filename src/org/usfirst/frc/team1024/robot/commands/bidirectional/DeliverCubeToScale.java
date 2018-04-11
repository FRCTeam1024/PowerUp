package org.usfirst.frc.team1024.robot.commands.bidirectional;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.FieldConfig;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveAndLiftAndIntake;
import org.usfirst.frc.team1024.robot.commands.DriveAndMoveLift;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.DriveAndShiftAndLift;
import org.usfirst.frc.team1024.robot.commands.PrintToConsole;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.ShiftLow;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.lift.ChangeLiftSpeed;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.MoveLiftPID;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Copied from latest DriveToRightScaleEnd, and then modified to work both sides
 * 
 * Delivers starting cube to scale, then goes and picks up cube at end of switch wall
 */
public class DeliverCubeToScale extends CommandGroup {

    public DeliverCubeToScale() {
    	addSequential(new ChangeDriveSpeed(1.0));
    	addSequential(new ChangeLiftSpeed(1.0));
    	addSequential(new DriveAndShift(Constants.BACKWALL_TO_MIDDLE_SCALE_DISTANCE - (Constants.ROBOT_LENGTH_IN / 2) - 20.0));
    	addSequential(new ChangeDriveSpeed(0.5));
    	// turn towards scale
    	if(Robot.fieldConfig.isScaleRight()) {
    		addSequential(new TurnLeft(90),2);
    		addSequential(new DriveAndMoveLift(-12.0, Level.SCALE_LOSS), 2);
    		
    	} else if(Robot.fieldConfig.isScaleLeft()) {
    		addSequential(new TurnRight(90),2);
    		addSequential(new DriveAndMoveLift(-12.0, Level.SCALE_LOSS));
    	}
    	addSequential(new DriveStraight(29.0));
    	// drop the cube on to the scale
    	addSequential(new OpenClamp());
    
    }
}
