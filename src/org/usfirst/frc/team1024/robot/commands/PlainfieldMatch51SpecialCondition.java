package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.FieldConfig.POSITION;
import org.usfirst.frc.team1024.robot.commands.auto.right.AcquireSecondCubeFromScale;
import org.usfirst.frc.team1024.robot.commands.auto.right.DeliverZaneFirstCubeToScale;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScale;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverFirstCubeToSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlainfieldMatch51SpecialCondition extends CommandGroup {

    public PlainfieldMatch51SpecialCondition() {
        if (Robot.fieldConfig.isScaleRight()) {
        	//addSequential(new DeliverCubeToScale());
        	addSequential(new DeliverZaneFirstCubeToScale());
			addSequential(new AcquireSecondCubeFromScale());
			addSequential(new DeliverZaneSecondCubeToScale());
        } else {
        	if (Robot.fieldConfig.isSwitchRight()) {
        		addSequential(new DeliverFirstCubeToSwitch(POSITION.RIGHT));
        	} else {
        		addSequential(new CrossTest());
        	}
        }
    }
}