package org.usfirst.frc.team1024.robot.commands.auto.left;

import org.usfirst.frc.team1024.robot.FieldConfig.POSITION;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScaleAndGetNextCube;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverSecondCubeToScale;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * THIS HAS NOT BEEN TESTED YET - remove this when it has
 */
public class LeftScaleLeftScale extends CommandGroup {

    public LeftScaleLeftScale() {
    	addSequential(new DeliverCubeToScaleAndGetNextCube());
    	addSequential(new DeliverSecondCubeToScale());
    }
}
