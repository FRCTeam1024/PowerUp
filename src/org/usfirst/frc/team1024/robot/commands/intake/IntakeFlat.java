package org.usfirst.frc.team1024.robot.commands.intake;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeFlat extends Command {
	boolean isDone;
    public IntakeFlat() {
    	requires(Robot.intake);
    	isDone = false;
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.posOut();
    	isDone = true;
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
