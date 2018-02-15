package org.usfirst.frc.team1024.robot.commands.test;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.commands.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.GrabCube;
import org.usfirst.frc.team1024.robot.commands.MoveLiftPID;
import org.usfirst.frc.team1024.robot.commands.OpenClamp;
import org.usfirst.frc.team1024.robot.commands.intake.GetCubeWide;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FrontSwitchSimple extends CommandGroup {

    public FrontSwitchSimple() {
    		addSequential(new GetCubeWide());
    		addSequential(new MoveLiftPID(Level.SWITCH));
    		addSequential(new DriveStraight(106.0));
    		addSequential(new OpenClamp());
    }
}
