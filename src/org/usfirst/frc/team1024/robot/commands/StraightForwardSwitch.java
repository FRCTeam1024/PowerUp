package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightForwardSwitch extends CommandGroup {

    public StraightForwardSwitch() {
    	addSequential(new DriveAndShiftAndLift(140 - Constants.ROBOT_LENGTH_IN - 24 - 4, Level.SWITCH));
    	//addSequential(new DriveAndMoveLift(140 - Constants.ROBOT_LENGTH_IN - 24 - 12 - 3, Level.SWITCH));
    	addSequential(new OpenClamp());
    }
}
