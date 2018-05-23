package org.usfirst.frc.team1024.robot.commands.auto.left;

import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScale;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleEnd extends CommandGroup {

    public LeftScaleEnd() {
        addSequential(new DeliverCubeToScale());
    }
}