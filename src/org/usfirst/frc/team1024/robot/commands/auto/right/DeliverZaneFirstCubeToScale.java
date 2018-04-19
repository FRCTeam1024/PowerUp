package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.AutoDriveConstants;
import org.usfirst.frc.team1024.robot.commands.DriveAndMoveLift;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DeliverZaneFirstCubeToScale extends CommandGroup {

    public DeliverZaneFirstCubeToScale() {
    	if (Robot.fieldConfig.isScaleRight()) {
    		addSequential(new ChangeDriveSpeed(1.0));
    		addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES - 21.0));
    		addSequential(new TurnLeftAndLift(22.0, Level.SWITCH));
    		addSequential(new IntakeExtend());
    		addSequential(new ChangeDriveSpeed(1.0));
    		addSequential(new DriveAndMoveLift(78.0, Level.SCALE_LOSS));
   			addSequential(new OpenClamp());
    	} else {
    		addSequential(new ChangeDriveSpeed(1.0));
    		addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES - 21.0));
    		addSequential(new TurnRightAndLift(22.0, Level.SWITCH));
    		addSequential(new IntakeExtend());
    		addSequential(new ChangeDriveSpeed(0.9));
    		addSequential(new DriveAndMoveLift(78.0, Level.SCALE_LOSS));
   			addSequential(new OpenClamp());
    	}
    }
}
