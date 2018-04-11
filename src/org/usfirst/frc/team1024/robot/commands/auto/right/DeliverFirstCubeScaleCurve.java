package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.commands.lift.MoveLiftPID;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DeliverFirstCubeScaleCurve extends CommandGroup {
	
	static boolean ACTIVATE_LIFT;

    public DeliverFirstCubeScaleCurve() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addParallel(new MoveLiftPID(Level.SCALE_LOSS));
    	addSequential(new DriveCurveToScale());
    }
}
