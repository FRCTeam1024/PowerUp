package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;

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
    	if (Robot.lift.getLiftEncoderValue() < 300000 && !Robot.oi.getOverrideButton()) {
    		Robot.lift.configMaxOutputs(0.25);
    	} else {
    		Robot.lift.configMaxOutputs(1.0);
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.lift.stopLift();
    }

    protected void interrupted() {
    	end();
    }
}
