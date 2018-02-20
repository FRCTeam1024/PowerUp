package org.usfirst.frc.team1024.robot.commandgroups;

import org.usfirst.frc.team1024.robot.commands.DriveCurve;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveSCurve extends CommandGroup {

    public DriveSCurve(double firstDistance, int firstAngle, double middleDistance,
    					int secondAngle, double lastDistance) {
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
    	addSequential(new DriveStraight(firstDistance, false));
    	addSequential(new DriveCurve(firstAngle));
    	addSequential(new DriveStraight(middleDistance, false));
    	addSequential(new DriveCurve(secondAngle));
    	addSequential(new DriveStraight(lastDistance));
    }
}
