package org.usfirst.frc.team1024.robot.commands.intake;

import org.usfirst.frc.team1024.robot.commands.CloseClampOnBeamBreak;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AquireAndClamp extends CommandGroup {

    public AquireAndClamp() {
    	addParallel(new IntakeAcquire(),1);
    	addParallel(new CloseClampOnBeamBreak());
    }
}
