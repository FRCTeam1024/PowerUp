package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeNarrow;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeExtendNarrow extends CommandGroup {

    public IntakeExtendNarrow() {
    	addSequential(new IntakeExtend());
    	addSequential(new IntakeNarrow());
    }
}
