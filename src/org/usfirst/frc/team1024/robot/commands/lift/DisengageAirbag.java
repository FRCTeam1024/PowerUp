package org.usfirst.frc.team1024.robot.commands.lift;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DisengageAirbag extends Command {

    public DisengageAirbag() {
    	requires(Robot.lift);
    }

    protected void initialize() {
    	Robot.lift.disengageAirBag();
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
