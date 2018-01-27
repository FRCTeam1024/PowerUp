package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAndTurn extends CommandGroup {

    public DriveAndTurn() {
    	requires(Robot.drivetrain);
    	addSequential(new DriveDistance(30));
    	addSequential(new TurnToAngle(90));
    	addSequential(new ResetEncoder());
    	addSequential(new DriveDistance(96));
    	addSequential(new TurnToAngle(-90));
    	addSequential(new ResetEncoder());
    	
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
    }
}
