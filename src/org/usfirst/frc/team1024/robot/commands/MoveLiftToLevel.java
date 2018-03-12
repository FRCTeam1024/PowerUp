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
	boolean isDone;
	public MoveLiftToLevel(Level level) {
		requires(Robot.lift);
		this.level = level;
		isDone = false;
	}

	protected void initialize() {
		Robot.lift.setPIDSetpoint(level.getHeight());
	}

	protected void execute() {
		if (Robot.oi.logi.getRawButton(Constants.REACH_PORTAL_HEIGHT)) {
			Robot.lift.setPIDSetpoint(level.getHeight());
			if (Robot.lift.getCommandedOutput() > 0.0) {
				if (Robot.lift.getLiftEncoderValue() < 25000 /* && !Robot.oi.getOverrideButton() */) {
					Robot.lift.configMaxOutputs(1.0);
					// System.out.println("1");
				} else {
					Robot.lift.configMaxOutputs(0.25);
					// System.out.println("2");

				}
			} else if (Robot.lift.getCommandedOutput() < 0.0) {
				if (Robot.lift.getLiftEncoderValue() > 3000 /* && !Robot.oi.getOverrideButton() */) {
					Robot.lift.configMaxOutputs(1.0);
					// System.out.println("3");
				} else {
					Robot.lift.configMaxOutputs(0.25);
					// System.out.println("4");
				}
			} else {
				Robot.lift.configMaxOutputs(1.0);
				// System.out.println("5");
			}
		} else {
			isDone = true;
		}
	}

	protected boolean isFinished() {
		return isDone;
	}

	protected void end() {
	}

	protected void interrupted() {
		
	}
}
