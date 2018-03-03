package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.FieldConfig.POSITION;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverFirstCubeToSwitch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RightSwitch extends CommandGroup {

    public RightSwitch() {
    	addSequential(new DeliverFirstCubeToSwitch(POSITION.RIGHT));
    	
    	// TODO put cube on switch
    	/* To scale:
    	 * addSequential(new DriveStraight(-10));
    	 * addSequential(new TurnRelative(90));
    	 * addSequential(new DriveStraight(20));
    	 * 
    	 */
    }
}
