package org.usfirst.frc.team1024.robot.commands.intake;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeNarrow extends Command {
	boolean isDone;
    public IntakeNarrow() {
    	requires(Robot.intake);
    	isDone = false;
    }

    protected void initialize() {
    	System.out.println("Trying to make the Intake narrow");
    }

    protected void execute() {
    	Robot.intake.posIn();
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
