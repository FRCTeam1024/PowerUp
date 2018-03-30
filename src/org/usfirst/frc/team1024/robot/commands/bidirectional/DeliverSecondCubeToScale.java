package org.usfirst.frc.team1024.robot.commands.bidirectional;

import org.usfirst.frc.team1024.robot.FieldConfig;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveUntilAndMoveLift;
import org.usfirst.frc.team1024.robot.commands.TurnLeftAndLift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Assuming we have come from the scale, which turned X degrees toward switch
 * i.e., assuming this is following the end of DeliverCubeToScaleAndGetNextCube
 * 
 */
public class DeliverSecondCubeToScale extends CommandGroup {

    public DeliverSecondCubeToScale() {
    	
        // Turn 170 degrees back so we're facing 0, or facing the scale
    	if(Robot.fieldConfig.isScaleLeft()) {
    		addSequential(new TurnLeftAndLift(165.0, 5.0, Level.SCALE_LOSS));
    	} else { // scale on right side
    		addSequential(new TurnRightAndLift(165.0, 5.0, Level.SCALE_LOSS));
    	}
    	
    	
    	// drive forward some distance
    	// not sure what the right/current drive straight command is now
    	addSequential(new DriveUntilAndMoveLift(45.0, 1.0, Level.SCALE_NEUTRAL));
    	
    	// drop cube
    	addSequential(new OpenClamp());
    }
}
