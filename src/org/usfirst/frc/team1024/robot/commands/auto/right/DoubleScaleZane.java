package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.commands.AutoDriveConstants;
import org.usfirst.frc.team1024.robot.commands.Delay;
import org.usfirst.frc.team1024.robot.commands.DriveAndLiftAndIntake;
import org.usfirst.frc.team1024.robot.commands.DriveAndMoveLift;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeTrimPID;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeTurnSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.ShiftLow;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeNarrow;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeRetract;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleScaleZane extends CommandGroup {

    public DoubleScaleZane() {
    	addSequential(new ChangeDriveSpeed(1.0));
    	addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES - 21));
    	addSequential(new TurnLeftAndLift(22, Level.SWITCH));
    	addSequential(new ChangeDriveSpeed(0.9));
    	addSequential(new DriveAndMoveLift(75, Level.SCALE_LOSS)); 
    	addSequential(new OpenClamp());
    	addSequential(new ChangeTurnSpeed(0.6));
    	addSequential(new TurnLeft(142.0));
    	addSequential(new ChangeTurnSpeed(1.0));
    	addSequential(new ChangeTrimPID(0, 0, 0, 0.1));
    	//addSequential(new ShiftLow());
    	//addSequential(new DriveAndShift(65, 2));
    	addSequential(new IntakeExtend());
    	addSequential(new ChangeDriveSpeed(0.60));
    	// Need to slow down when acquiring cube
    	addSequential(new DriveAndLiftAndIntake(60.0, Level.INTAKE, 2.5), 2.5);
    	//addSequential(new IntakeNarrow());
    	//addSequential(new Delay(.5));
    	addSequential(new CloseClamp());
    	addSequential(new IntakeRetract());
    	addSequential(new Delay(0.2));
    	addSequential(new TurnRight(153));
    	addSequential(new DriveAndMoveLift(63, Level.SCALE_LOSS));
    	addSequential(new OpenClamp());
    }
}
