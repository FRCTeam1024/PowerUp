package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftSwitchSwitch extends CommandGroup {

    public LeftSwitchSwitch() {
    	addSequential(new DriveAndShift(AutoDriveConstants.BACK_WALL_TO_END_OF_SWITCH_INCHES));
		addSequential(new TurnRightAndLift(90.0, Level.SWITCH));
		addSequential(new OpenClamp());
		addSequential(new TurnRightOneSide(-90.0));
		addSequential(new DriveAndMoveLift(72.0, Level.INTAKE));
		addSequential(new TurnRight(135.0));
		addSequential(new IntakeExtend());
		addSequential(new DriveAndIntake(36.0, 3.0));
		addSequential(new CloseClamp());
		addSequential(new MoveLiftToLevel(Level.SWITCH));
		addSequential(new DriveStraight(12.0));
		addSequential(new OpenClamp());
    }
}
