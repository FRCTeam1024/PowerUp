package org.usfirst.frc.team1024.robot.commands.auto.middle;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveAndIntake;
import org.usfirst.frc.team1024.robot.commands.DriveAndMoveLift;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
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
    	addSequential(new ChangeTrimPID(Constants.TRIM_KP, Constants.TRIM_KI, Constants.TRIM_KD, 0.1));
    	addSequential(new ChangeTurnSpeed(0.5));
    	addSequential(new DriveAndMoveLift(-72.0, Level.INTAKE), 3);
    	if (Robot.fieldConfig.isSwitchRight()) {
    		addSequential(new TurnLeft(65.0, 5.0), 2);
    		addSequential(new IntakeExtend());
    		addSequential(new DriveAndIntake(48.0, 3.0));
    		addSequential(new CloseClamp());
    		addSequential(new DriveStraight(-48.0, 5.0));
    		addSequential(new TurnRightAndLift(45.0, 5.0, Level.SWITCH), 3);
    		addSequential(new IntakeRetract());
    		addSequential(new DriveStraight(48.0, 5.0));
    		addSequential(new OpenClamp());
    	} else {
    		addSequential(new TurnRight(65.0, 5.0), 2);
    		addSequential(new IntakeExtend());
    		addSequential(new DriveAndIntake(48.0, 5.0));
    		addSequential(new CloseClamp());
    		addSequential(new DriveStraight(-48.0, 5.0));
    		addSequential(new TurnLeftAndLift(45.0, 5.0, Level.SWITCH), 3);
    		addSequential(new IntakeRetract());
    		addSequential(new DriveStraight(48.0, 5.0));
    		addSequential(new OpenClamp());
    	}
    }
}
