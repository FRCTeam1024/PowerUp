package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.FieldConfig.POSITION;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScale;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverSecondCubeToScale;
import org.usfirst.frc.team1024.robot.commands.bidirectional.GoGetSecondCube;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	deliver both cubes to scale on right side
 */
public class RightScaleRightScale extends CommandGroup {

    public RightScaleRightScale() {
    	addSequential(new DeliverCubeToScale());
    	//addSequential(new GoGetSecondCube());
        //addSequential(new DeliverSecondCubeToScale());
    }
}