package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.auto.right.CrossToLeftScale;
import org.usfirst.frc.team1024.robot.commands.auto.right.RightScaleEnd;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ScaleEither extends CommandGroup {

    public ScaleEither() {
        if (Robot.fieldConfig.isScaleRight()) {
        	addSequential(new RightScaleEnd());
        } else {
        	addSequential(new CrossToLeftScale());
        }
    }
}
