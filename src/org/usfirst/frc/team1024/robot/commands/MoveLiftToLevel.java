package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLiftToLevel extends Command {
	Level level;

	public MoveLiftToLevel(Level level) {
		requires(Robot.lift);
		this.level = level;
	}

	protected void initialize() {
		Robot.lift.setPIDSetpoint(level.getHeight());
	}

	protected void execute() {
		Robot.lift.setPIDSetpoint(level.getHeight());
		if (Robot.lift.getCommandedOutput() > 0.0) {
			if (Robot.lift.getLiftEncoderValue() < 25000 /* && !Robot.oi.getOverrideButton() */) {
				Robot.lift.configMaxOutputs(1.0);
			} else {
				Robot.lift.configMaxOutputs(0.25);

			}
		} else if (Robot.lift.getCommandedOutput() < 0.0) {
			if (Robot.lift.getLiftEncoderValue() > 3000 /* && !Robot.oi.getOverrideButton() */) {
				Robot.lift.configMaxOutputs(1.0);
			} else {
				Robot.lift.configMaxOutputs(0.25);
			}
		} else {
			Robot.lift.configMaxOutputs(1.0);
		}
	}

	protected boolean isFinished() {
		if (Robot.lift.getLiftEncoderValue() < level.getHeight() + 100 && Robot.lift.getLiftEncoderValue() > level.getHeight() - 100) {
			return true;
		} else {
			return false;
		}
	}
	
	protected void end() {
	}

	protected void interrupted() {

	}
}
