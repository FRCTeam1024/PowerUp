package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeWithJoystick extends Command {
	double leftMotorOutput;
	double rightMotorOutput;
	int currentSpikeCount = 0;
	int stoppedCount = 0;
	
	public IntakeWithJoystick() {
		requires(Robot.intake);
	}

	protected void initialize() {
	}

	protected void execute() {
		double forwardSpeed = Robot.oi.logi.getRawAxis(Constants.INTAKE_STICK_AXIS_Y);
		double rotationSpeed = Robot.oi.logi.getRawAxis(Constants.INTAKE_STICK_AXIS_X);
		double maxInput = Math.copySign(Math.max(Math.abs(forwardSpeed), Math.abs(rotationSpeed)), forwardSpeed);
		
		if (forwardSpeed >= 0.0) {
			// First quadrant, else second quadrant
			if (rotationSpeed >= 0.0) {
				leftMotorOutput = maxInput;
				rightMotorOutput = forwardSpeed - rotationSpeed;
			} else {
				leftMotorOutput = forwardSpeed + rotationSpeed;
				rightMotorOutput = maxInput;
			}
		} else {
			// Third quadrant, else fourth quadrant
			if (rotationSpeed >= 0.0) {
				leftMotorOutput = forwardSpeed + rotationSpeed;
				rightMotorOutput = maxInput;
			} else {
				leftMotorOutput = maxInput;
				rightMotorOutput = forwardSpeed - rotationSpeed;
			}
		}
		
		if (Robot.intake.leftIntake.getOutputCurrent() > 10.0 || Robot.intake.rightIntake.getOutputCurrent() > 10.0) {
			currentSpikeCount++;
		} else {
			currentSpikeCount = 0;
		}
		if (currentSpikeCount > 5 && stoppedCount < 10) {
			Robot.intake.intakeSpeed(0.0, rightMotorOutput);
			stoppedCount++;
		} else {
			if (Robot.oi.logi.getRawButton(2)) {
				Robot.intake.intakeSpeed(0.0, rightMotorOutput);
			} else {
				Robot.intake.intakeSpeed(leftMotorOutput, rightMotorOutput);
			}
			stoppedCount = 0;
		}

	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {

	}
}