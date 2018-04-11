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
import org.usfirst.frc.team1024.robot.commands.auto.right.DoubleScaleZane;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeRetract;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleSwitch extends CommandGroup {

    public LeftScaleSwitch() {
    	if (Robot.fieldConfig.isScaleLeft()) {
			addSequential(new DeliverZaneFirstCubeToScale());
			addSequential(new ChangeTurnSpeed(0.6));
			addSequential(new TurnRight(145.0));
			addSequential(new ChangeTurnSpeed(1.0));
			addSequential(new ChangeTrimPID(0, 0, 0, 0.1));
			addSequential(new ChangeDriveSpeed(0.60));
			addSequential(new DriveAndLiftAndIntake(60.0, Level.INTAKE, 2.5), 2.5);
			addSequential(new CloseClamp());
			addSequential(new IntakeRetract());
			addSequential(new Delay(0.2));
			
			addSequential(new MoveLiftToLevel(Level.SWITCH));
			addSequential(new Delay(0.5));
			addSequential(new DriveStraight(24.0), 1.5);
			if ("Yes".equals(Robot.dropCube.getSelected())) {
				addSequential(new OpenClamp());
			}
		} else {
			addSequential(new ChangeDriveSpeed(1.0));
			addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_CROSSING_PATH_INCHES + 3.0));
			addSequential(new TurnRight(90.0), 2);
			//if (Robot.stayOnOurSide.getSelected().equals("Yes")) {
				addSequential(new ChangeDriveSpeed(0.6)); // This could be faster probably
				addSequential(new DriveAndShift(195.0), 5);
				// turn right to face scale
				addSequential(new TurnLeftAndLift(90.0, Level.SCALE_LOSS), 5);
				// drive forward to scale
				addSequential(new DriveStraight(45.0), 2);
				addSequential(new OpenClamp());
			//}
		}
    }
}
