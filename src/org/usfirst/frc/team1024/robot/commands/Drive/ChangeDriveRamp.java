package org.usfirst.frc.team1024.robot.commands.Drive;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeDriveRamp extends Command {
	double ramp;
    public ChangeDriveRamp(double ramp) {
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
