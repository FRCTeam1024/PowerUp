package org.usfirst.frc.team1024.robot.commands.auto.left;

import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScaleAndGetNextCube;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverSecondCubeToSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleLeftSwitch extends CommandGroup {

    public LeftScaleLeftSwitch() {
    	addSequential(new DeliverCubeToScaleAndGetNextCube());	
        addSequential(new DeliverSecondCubeToSwitch());
    }
}
