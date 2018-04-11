package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoubleScaleCurve extends CommandGroup {

    public DoubleScaleCurve() {
    	if (Robot.fieldConfig.isScaleRight()) {
    		addSequential(new DriveCurve(264.0, -10.0));
    		
    	} else {
    		addSequential(new DriveCurve(264.0, 10.0));
    	}
    	
    }
}
