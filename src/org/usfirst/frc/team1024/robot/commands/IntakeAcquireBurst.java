package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeAcquireBurst extends Command {
	double leftPower;
	double rightPower;
	int loop = 0;

	public IntakeAcquireBurst() {
		requires(Robot.intake);
		this.leftPower = 0.5;
		this.rightPower = 1.0;
	}

	public IntakeAcquireBurst(double leftPower, double rightPower) {
		requires(Robot.intake);
		this.leftPower = leftPower;
		this.rightPower = rightPower;
	}

	protected void initialize() {
	}

	protected void execute() {
		if (loop >= 0) {
			if (loop <= 20) {
				Robot.intake.intakeSpeed(leftPower, rightPower);
				loop++;
			} else {
				Robot.intake.intakeSpeed(0.0);
				loop = -15;
			}
		} else {
			loop++;
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.intake.intakeStop();
		loop = 0;
	}

	protected void interrupted() {
		end();
	}
}
