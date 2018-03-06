package org.usfirst.frc.team1024.robot.commands.Drive;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveUntilPID extends Command {
	double targetDistance;
	double initialSpeed;
	int onTargetCount = 0;

	public DriveUntilPID(double targetDistance, double initialSpeed) {
	    	requires(Robot.drivetrain);
	    	this.targetDistance = targetDistance;
	    	this.initialSpeed = initialSpeed;
	    }

	protected void initialize() {
		Robot.drivetrain.resetOpticalEncoder();
		Robot.drivetrain.resetMagneticEncoder();
		double currentAngle = Robot.drivetrain.getHeading();
		Robot.drivetrain.posPID.setSetpoint(targetDistance);
		Robot.drivetrain.trimPID.setSetpoint(currentAngle);
		Robot.drivetrain.posPID.enable();
		Robot.drivetrain.trimPID.enable();
	}

	protected void execute() {
		SmartDashboard.putNumber("targetDistance", targetDistance);
		// Robot.drivetrain.pidDriveForwardStraight();
		if (targetDistance < 0) {
			Robot.drivetrain.pidDriveBackwardStraight();
		} else {
			Robot.drivetrain.pidDriveForwardStraight();
			Robot.drivetrain.pidDriveUntil(1.0);
			Robot.drivetrain.drive(-initialSpeed - Robot.drivetrain.trimPID.get(), -initialSpeed + Robot.drivetrain.trimPID.get());
		}
	}

	protected boolean isFinished() {
		if (Math.abs(Robot.drivetrain.getOpticalDistanceInches() - targetDistance) < 1.0) {
			return true;
		} else {
			return false;
		}
	}

	protected void end() {
		Robot.drivetrain.resetOpticalEncoder();
		Robot.drivetrain.posPID.disable();
		Robot.drivetrain.trimPID.disable();
	}

	protected void interrupted() {
		Robot.drivetrain.resetOpticalEncoder();
		Robot.drivetrain.posPID.disable();
		Robot.drivetrain.trimPID.disable();
	}
}
