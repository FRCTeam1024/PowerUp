package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeTrimPID;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeTurnSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.auto.right.AcquireSecondCubeFromScale;
import org.usfirst.frc.team1024.robot.commands.auto.right.DeliverZaneFirstCubeToScale;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeRetract;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleScale extends CommandGroup {

	public RightScaleScale() {
		if (Robot.fieldConfig.isScaleRight()) {
			addSequential(new DeliverZaneFirstCubeToScale());
			addSequential(new AcquireSecondCubeFromScale());
			addSequential(new DeliverZaneSecondCubeToScale());
		} else {
			addSequential(new ChangeDriveSpeed(1.0));
			addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES + 12.0));
			addSequential(new TurnLeft(90.0), 2);
			if (Robot.stayOnOurSide == false) {
				addSequential(new ChangeDriveSpeed(1.0)); // This could be faster probably
				addSequential(new DriveAndShift(192.0 - 14.0), 5);
				// turn right to face scale
				addSequential(new TurnRightAndLift(90.0, Level.SCALE_LOSS), 5);
				// drive forward to scale
				addSequential(new DriveStraight(47.0), 2);
				addSequential(new OpenClamp());
				addSequential(new ChangeTurnSpeed(0.6));
				addSequential(new Delay(0.5));
				addSequential(new DriveStraight(-24.0));
				addSequential(new TurnLeftAndLift(180.0, Level.INTAKE), 3);
				addSequential(new DriveAndIntake(66.0, 3.0), 3);
				
			}
		}
	}
}
