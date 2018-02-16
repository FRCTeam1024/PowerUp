package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShiftHigh extends Command {

    public ShiftHigh() {
    	requires(Robot.drivetrain);
    }
    
    protected void initialize() {
    	Robot.drivetrain.shiftHigh();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
