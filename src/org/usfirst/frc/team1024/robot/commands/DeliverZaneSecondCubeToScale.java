package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnRight;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DeliverZaneSecondCubeToScale extends CommandGroup {

    public DeliverZaneSecondCubeToScale() {
        if (Robot.fieldConfig.isScaleRight()) {
        	addSequential(new TurnRight(153));
			addSequential(new DriveAndMoveLift(63, Level.SCALE_LOSS));
			if (Robot.dropCube == true) {
				addSequential(new OpenClamp());
			}
        } else {
        	addSequential(new TurnLeft(153));
			addSequential(new DriveAndMoveLift(63, Level.SCALE_LOSS));
			if (Robot.dropCube == true) {
				addSequential(new OpenClamp());
			}
        }
    }
}
