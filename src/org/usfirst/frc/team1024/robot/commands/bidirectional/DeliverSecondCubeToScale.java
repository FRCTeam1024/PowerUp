package org.usfirst.frc.team1024.robot.commands.bidirectional;

import org.usfirst.frc.team1024.robot.FieldConfig;
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
public class DeliverSecondCubeToScale extends CommandGroup {

    public DeliverSecondCubeToScale() {
    	
        // Turn 170 degrees back so we're facing 0, or facing the scale
    	if(Robot.fieldConfig.isScaleLeft()) {
    		addSequential(new TurnLeftAndLift(170.0, 5.0, Level.SCALE_NEUTRAL));
    	} else { // scale on right side
    		addSequential(new TurnRightAndLift(170.0, 5.0, Level.SCALE_NEUTRAL));
    	}
    	
    	// raise lift to scale height
    	// this should be done in parallel, probably with the turn, if possible
    	
    	// drive forward some distance
    	// not sure what the right/current drive straight command is now
    	addSequential(new DriveStraight(70));
    	
    	// drop cube
    	addSequential(new OpenClamp()); 
    }
}
