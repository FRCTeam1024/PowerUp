package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DeliverZaneSecondCubeToSwitch extends CommandGroup {

    public DeliverZaneSecondCubeToSwitch() {
    	addSequential(new MoveLiftToLevel(Level.SWITCH));
		addSequential(new Delay(0.5));
		addSequential(new DriveStraight(24.0), 1.5);
		if (Robot.dropCube == true) {
			addSequential(new OpenClamp());
		}
    }
}
