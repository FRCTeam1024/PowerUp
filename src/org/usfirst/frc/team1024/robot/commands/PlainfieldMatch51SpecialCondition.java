package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.FieldConfig.POSITION;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScale;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverFirstCubeToSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlainfieldMatch51SpecialCondition extends CommandGroup {

    public PlainfieldMatch51SpecialCondition() {
        if (Robot.fieldConfig.isScaleRight()) {
        	addSequential(new DeliverCubeToScale());
        } else {
        	if (Robot.fieldConfig.isSwitchRight()) {
        		addSequential(new DeliverFirstCubeToSwitch(POSITION.RIGHT));
        	} else {
        		addSequential(new CrossTest());
        	}
        }
    }
}