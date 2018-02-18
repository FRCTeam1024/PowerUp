package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FastCrossToScale extends CommandGroup {
	public FastCrossToScale() {
    	requires(Robot.drivetrain);
    	int scaleSide = Robot.fieldConfig.getScalePosition().intValue();
    	
		
    	if(scaleSide == 1) { //Scale is on the Right
    		addSequential(new DriveStraight(324 + 24.35));
    		addSequential(new TurnLeft(90));
    		addSequential(new DriveStraight(40));
    		addSequential(new TurnRight(90));
    	} else { //Scale is on the Left
    		addSequential(new TurnLeft(90));
        	addSequential(new DriveStraight(15.5 * 12)); 
        	// turn right to face scale
        	addSequential(new TurnRight(90));
        	// drive forward to scale
        	/*addSequential(new MoveLiftPID(Level.SCALE_LOSS), 2);
        	addSequential(new DriveStraight(48));
        	addSequential(new OpenClamp());*/
    	}
    }
}
