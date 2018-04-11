package org.usfirst.frc.team1024.robot.commands.auto.middle;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Delay;
import org.usfirst.frc.team1024.robot.commands.DriveAndIntake;
import org.usfirst.frc.team1024.robot.commands.DriveAndMoveLift;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnLeftOneSide;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightOneSide;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeTrimPID;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeTurnSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeRetract;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MiddleSwitchMiddleSwitch extends CommandGroup {

	public MiddleSwitchMiddleSwitch() {
		addSequential(new AutoSwitchFront());
		
		if (Robot.fieldConfig.isSwitchRight()) {
			addSequential(new TurnRight(45.0), 5);
			addSequential(new IntakeExtend());
			addSequential(new DriveAndMoveLift(-72.0, Level.INTAKE), 2);
			addSequential(new TurnLeft(54.0), 5);
			addSequential(new DriveAndIntake(24.0, 3.0), 2);
			addSequential(new CloseClamp());
			addSequential(new Delay(0.2));
			addSequential(new DriveStraight(-36.0), 2);
			addSequential(new IntakeRetract());
			addSequential(new TurnRight(45.0), 5);
			addSequential(new DriveAndMoveLift(84.0, Level.SWITCH), 2);
			addSequential(new TurnLeftOneSide(45.0), 1);
			//if ("Yes".equals(Robot.dropCube.getSelected())) {
				addSequential(new OpenClamp());
			//}
		} else {
			addSequential(new TurnLeft(45.0), 5);
			addSequential(new IntakeExtend());
			addSequential(new DriveAndMoveLift(-72.0, Level.INTAKE), 2);
			addSequential(new TurnRight(54.0), 5);
			addSequential(new DriveAndIntake(24.0, 3.0), 2);
			addSequential(new CloseClamp());
			addSequential(new Delay(0.2));
			addSequential(new DriveStraight(-36.0), 2);
			addSequential(new IntakeRetract());
			addSequential(new TurnLeft(45.0), 5);
			addSequential(new DriveAndMoveLift(84.0, Level.SWITCH), 2);
			addSequential(new TurnRightOneSide(45.0), 1);
			if ("Yes".equals(Robot.dropCube.getSelected())) {
				addSequential(new OpenClamp());
			}
		}
	}
}
