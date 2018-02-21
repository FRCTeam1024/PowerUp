package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveUntilPID;
import org.usfirst.frc.team1024.robot.commands.lift.MoveLiftPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveUntilAndMoveLift extends CommandGroup {

    public DriveUntilAndMoveLift(double distance, double initialPower, Level level) {
    	addParallel(new DriveUntilPID(distance, initialPower));
    	addSequential(new MoveLiftPID(level));
    }
}
