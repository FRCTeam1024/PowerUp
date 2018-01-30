package org.usfirst.frc.team1024.robot.commands.auto;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.DriveDistance;
import org.usfirst.frc.team1024.robot.commands.ResetEncoder;
import org.usfirst.frc.team1024.robot.commands.TurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LeftPositionAuto extends CommandGroup {

    public LeftPositionAuto() {
    	requires(Robot.drivetrain);
    	addSequential(new DriveDistance(140));
    	addSequential(new TurnToAngle(90));
    	addSequential(new ResetEncoder());
    	addSequential(new DriveDistance(6));
      	// TODO put cube on switch
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
