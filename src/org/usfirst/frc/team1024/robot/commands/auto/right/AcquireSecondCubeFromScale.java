package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Delay;
import org.usfirst.frc.team1024.robot.commands.DriveAndLiftAndIntake;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeTrimPID;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeTurnSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeAcquire;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeFlat;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeNarrow;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeRetract;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AcquireSecondCubeFromScale extends CommandGroup {

	public AcquireSecondCubeFromScale() {
		if (Robot.fieldConfig.isScaleRight()) {
			addSequential(new ChangeTurnSpeed(0.6));
			addSequential(new TurnLeft(145.0));
			addSequential(new ChangeTurnSpeed(1.0));
			addSequential(new ChangeTrimPID(0, 0, 0, 0.1));
			addSequential(new ChangeDriveSpeed(0.60));
			addSequential(new DriveAndLiftAndIntake(60.0, Level.INTAKE, 3.0), 3.0);
			//addSequential(new IntakeFlat());
			//addSequential(new IntakeAcquire(), 0.5);
			addSequential(new CloseClamp());
			addSequential(new IntakeRetract());
			addSequential(new Delay(0.5));
		} else {
			addSequential(new ChangeTurnSpeed(0.6));
			addSequential(new TurnRight(145.0));
			addSequential(new ChangeTurnSpeed(1.0));
			addSequential(new ChangeTrimPID(0, 0, 0, 0.1));
			addSequential(new ChangeDriveSpeed(0.60));
			addSequential(new DriveAndLiftAndIntake(60.0, Level.INTAKE, 3.0), 3.0);
			//addSequential(new IntakeFlat());
			//addSequential(new IntakeAcquire(), 0.5);
			addSequential(new CloseClamp());
			addSequential(new IntakeRetract());
			addSequential(new Delay(0.5));
		}
	}
}
