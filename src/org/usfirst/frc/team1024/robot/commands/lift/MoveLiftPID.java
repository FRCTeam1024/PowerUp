package org.usfirst.frc.team1024.robot.commands.lift;

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
    	if (Robot.lift.getLiftEncoderValue() < 25000 /*&& !Robot.oi.getOverrideButton()*/) {
    		if(Robot.lift.getCommandedOutput() > 0.0) {
        		Robot.lift.configMaxOutputs(1.0);
    		} else {
    			Robot.lift.configMaxOutputs(0.25);
    		}
    	} else if (Robot.lift.getLiftEncoderValue() > 3000 /*&& !Robot.oi.getOverrideButton()*/) {
    		if(Robot.lift.getCommandedOutput() < 0.0) {
    			Robot.lift.configMaxOutputs(1.0);
    		} else {
    			Robot.lift.configMaxOutputs(0.25);
    		}
    		
    	} else {
    		Robot.lift.configMaxOutputs(1.0);
    	}
    }

    protected boolean isFinished() {
    	if (Robot.lift.getLiftEncoderValue() < level.getHeight() - 2000 || Robot.lift.getLiftEncoderValue() > level.getHeight() + 2000) {
    		return false;
    	} else {
    		return true;
    	}
    }

    protected void end() {
    	Robot.lift.stopLift();
    }

    protected void interrupted() {
    	end();
    }
}
