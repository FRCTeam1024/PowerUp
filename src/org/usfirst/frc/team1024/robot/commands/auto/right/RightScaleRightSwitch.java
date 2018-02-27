package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScaleAndGetNextCube;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverSecondCubeToScale;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverSecondCubeToSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightScaleRightSwitch extends CommandGroup {

    public RightScaleRightSwitch() {
    	addSequential(new DeliverCubeToScaleAndGetNextCube());	
        addSequential(new DeliverSecondCubeToSwitch());
    }
}
