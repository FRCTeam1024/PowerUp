package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StraightForwardSwitch extends CommandGroup {

    public StraightForwardSwitch() {
    	addParallel(new DriveStraight(140 - Constants.ROBOT_LENGTH_IN));
    	addSequential(new MoveLiftPID(Level.SWITCH));
    }
}
