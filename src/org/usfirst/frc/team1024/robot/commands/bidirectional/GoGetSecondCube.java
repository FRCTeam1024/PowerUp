package org.usfirst.frc.team1024.robot.commands.bidirectional;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveAndLiftAndIntake;
import org.usfirst.frc.team1024.robot.commands.DriveAndMoveLift;
import org.usfirst.frc.team1024.robot.commands.PrintToConsole;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.lift.ChangeLiftSpeed;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GoGetSecondCube extends CommandGroup {

    public GoGetSecondCube() {
    	// go get the nearest cube on the end of the switch wall
    	addSequential(new ChangeLiftSpeed(1.0));
    	addSequential(new ChangeDriveSpeed(0.5));
    	addSequential(new DriveAndMoveLift(-32.0, Level.SWITCH), 2);
    	if(Robot.fieldConfig.isScaleRight()) {
    		addSequential(new TurnLeft(90.0, 5.0), 2);
    	} else if(Robot.fieldConfig.isScaleLeft()) {
    		addSequential(new TurnRight(90.0, 5.0), 2);
    	}
    	
    	addSequential(new OpenClamp()); 
    	addSequential(new IntakeExtend());
    	addSequential(new ChangeDriveSpeed(1.0));
    	addSequential(new DriveAndLiftAndIntake(115.0, Level.INTAKE, 3.0), 3);
    	addSequential(new CloseClamp());
    	addSequential(new PrintToConsole("Done @ " + DriverStation.getInstance().getMatchTime()));
    	
    }
}
