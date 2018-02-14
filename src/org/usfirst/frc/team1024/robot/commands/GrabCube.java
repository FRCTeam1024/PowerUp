package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.commands.intake.IntakeRetract;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrabCube extends CommandGroup {

    public GrabCube() {
    	addSequential(new CloseClamp());
    	addSequential(new IntakeRetract());
    }
}
