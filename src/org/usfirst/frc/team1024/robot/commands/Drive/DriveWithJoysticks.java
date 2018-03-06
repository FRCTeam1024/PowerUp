package org.usfirst.frc.team1024.robot.commands.Drive;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoysticks extends Command {

    public DriveWithJoysticks() {
    	requires(Robot.drivetrain);
    }
    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrain.drive(Robot.oi.lJoy.getY(), Robot.oi.rJoy.getY());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
