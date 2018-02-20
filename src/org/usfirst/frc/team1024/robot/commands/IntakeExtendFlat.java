package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeFlat;

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
