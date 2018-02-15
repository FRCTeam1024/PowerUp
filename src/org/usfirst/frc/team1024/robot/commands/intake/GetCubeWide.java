 package org.usfirst.frc.team1024.robot.commands.intake;

import org.usfirst.frc.team1024.robot.commands.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.CloseClampOnBeamBreak;
import org.usfirst.frc.team1024.robot.commands.Delay;
import org.usfirst.frc.team1024.robot.commands.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GetCubeWide extends CommandGroup {

    public GetCubeWide() {
    	addSequential(new IntakeExtend());
    	addSequential(new OpenClamp());
    	addSequential(new Delay(1));
    	//addSequential(new IntakeNarrow());
    	addSequential(new Delay(1));
    	//addSequential(new AquireAndClamp());
    	addSequential(new IntakeAcquire(),1);
    	addSequential(new CloseClamp());
    	addSequential(new IntakeFlat());
    	addSequential(new Delay(1));
    	addSequential(new IntakeRetract());
    	addSequential(new Delay(1));
        
    }
}
