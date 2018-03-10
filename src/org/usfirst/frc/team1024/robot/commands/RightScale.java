package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScaleAndGetNextCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScale extends CommandGroup {

    public RightScale() {
    	addSequential(new DeliverCubeToScaleAndGetNextCube());
    }
}
