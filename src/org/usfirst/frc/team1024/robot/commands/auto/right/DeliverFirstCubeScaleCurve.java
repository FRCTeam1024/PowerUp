package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.commands.lift.MoveLiftPID;
import org.usfirst.frc.team1024.robot.commands.lift.MoveLiftPIDDelayed;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DeliverFirstCubeScaleCurve extends CommandGroup {
	
	public static boolean ACTIVATE_LIFT = false;

    public DeliverFirstCubeScaleCurve() {
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    	addParallel(new MoveLiftPIDDelayed(Level.SCALE_LOSS));
    	addSequential(new DriveCurveToScale());
    }
}
