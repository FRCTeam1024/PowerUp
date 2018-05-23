package org.usfirst.frc.team1024.robot.commands.intake;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeAcquire extends Command {
	double leftPower;
	double rightPower;
	int currentSpikeCount = 0;
	int stoppedCount = 0;
	
	public IntakeAcquire() {
    	requires(Robot.intake);
    	this.leftPower = 1.0;
    	this.rightPower = 1.0;
	}
	
    public IntakeAcquire(double leftPower, double rightPower) {
    	requires(Robot.intake);
    	this.leftPower = leftPower;
    	this.rightPower = rightPower;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (Robot.intake.leftIntake.getOutputCurrent() > 7.0 || Robot.intake.rightIntake.getOutputCurrent() > 7.0) {
			currentSpikeCount++;
		} else {
			currentSpikeCount = 0;
		}
		if (currentSpikeCount > 5 && stoppedCount < 10) {
			Robot.intake.intakeSpeed(0.0, rightPower);
			stoppedCount++;
		} else {
			Robot.intake.intakeSpeed(leftPower, rightPower);
			stoppedCount = 0;
		}
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.intake.intakeStop();
    }

    protected void interrupted() {
    	end();
    }
}
