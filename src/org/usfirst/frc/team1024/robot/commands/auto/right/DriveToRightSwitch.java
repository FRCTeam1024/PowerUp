package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.AutoDriveConstants;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.OpenClamp;
import org.usfirst.frc.team1024.robot.commands.TurnAbsolute;
import org.usfirst.frc.team1024.robot.commands.TurnLeft;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveToRightSwitch extends CommandGroup {

    public DriveToRightSwitch() {
    	requires(Robot.drivetrain);
    	addSequential(new DriveStraight(AutoDriveConstants.BACK_WALL_TO_END_OF_SWITCH_INCHES));
    	addSequential(new TurnLeft(90));
    	addSequential(new DriveStraight(20));

    	// TODO put cube on switch
    	//addSequential(new MoveLiftPID());    Add lift level height
    	//addSequential(new OpenClamp());
    }
}
