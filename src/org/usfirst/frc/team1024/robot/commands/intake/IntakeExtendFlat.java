package org.usfirst.frc.team1024.robot.commands.intake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeExtendFlat extends CommandGroup {

    public IntakeExtendFlat() {
    	addSequential(new IntakeExtend());
    	addSequential(new IntakeFlat());
    }
}
