package org.usfirst.frc.team1024.robot.commands.auto.left;

import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScale;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverSecondCubeToSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftScaleLeftSwitch extends CommandGroup {

    public LeftScaleLeftSwitch() {
    	addSequential(new DeliverCubeToScale());	
        addSequential(new DeliverSecondCubeToSwitch());
    }
}
