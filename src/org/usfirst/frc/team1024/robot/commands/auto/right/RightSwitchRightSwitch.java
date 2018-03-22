package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.FieldConfig.POSITION;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverFirstCubeToSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitchRightSwitch extends CommandGroup {

    public RightSwitchRightSwitch() {
    	addSequential(new DeliverFirstCubeToSwitch(POSITION.RIGHT));


    }
}
