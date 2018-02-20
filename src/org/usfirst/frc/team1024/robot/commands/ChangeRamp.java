package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeRamp extends Command {
	double ramp;
    public ChangeRamp(double ramp) {
    	this.ramp = ramp;
    }

    protected void initialize() {
    	Robot.drivetrain.setRamp(ramp);
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
