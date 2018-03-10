package org.usfirst.frc.team1024.robot.commands.lift;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EngageAirbag extends Command {

    public EngageAirbag() {
    	requires(Robot.lift);
    }

    protected void initialize() {
    	Robot.lift.engageAirbag();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
