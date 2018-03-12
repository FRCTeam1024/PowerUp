package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScale;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScale extends CommandGroup {

    public RightScale() {
    	addSequential(new DeliverCubeToScale());
    }
}
