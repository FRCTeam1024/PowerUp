package org.usfirst.frc.team1024.robot.commands.bidirectional;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Assuming we have come from the scale, which turned 80 degrees toward switch
 * i.e., assuming this is following the end of DeliverCubeToScaleAndGetNextCube
 * 
 *  THIS HAS NOT BEEN TESTED YET - remove this when it has
 */
public class DeliverSecondCubeToSwitch extends CommandGroup {

    public DeliverSecondCubeToSwitch() {
        // raise lift to Switch height
    	if(Robot.fieldConfig.isSwitchLeft()) {
    		addSequential(new TurnRightAndLift(10.0, 5.0, Level.SWITCH));
    	} else if(Robot.fieldConfig.isSwitchRight()) {
    		addSequential(new TurnLeftAndLift(10.0, 5.0, Level.SWITCH));
    	}
    	// drive forward a bit?
    	addSequential(new DriveStraight(6.0));
    	// drop cube
    	addSequential(new OpenClamp());
    }
}
