package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLiftPID extends Command {
	Level level;
	
	
	
	
    public MoveLiftPID(Level level) {
    	requires(Robot.lift);
    	this.level = level;
    }

    protected void initialize() {
    	Robot.lift.setPIDSetpoint(level.getHeight());
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.lift.stopLift();
    }

    protected void interrupted() {
    	Robot.lift.stopLift();
    }
}
