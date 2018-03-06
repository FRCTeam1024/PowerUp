package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.FieldConfig.POSITION;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScaleAndGetNextCube;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverSecondCubeToScale;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	deliver both cubes to scale on right side
 *	THIS HAS NOT BEEN TESTED YET - remove this when it has
 */
public class RightScaleRightScale extends CommandGroup {

    public RightScaleRightScale() {
    	addSequential(new DeliverCubeToScaleAndGetNextCube());	
        addSequential(new DeliverSecondCubeToScale());
    }
}
