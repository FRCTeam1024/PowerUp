package org.usfirst.frc.team1024.robot.commands.intake;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeAcquire extends Command {
	double leftPower;
	double rightPower;
	
	public IntakeAcquire() {
    	requires(Robot.intake);
    	this.leftPower = 0.5;
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
    	Robot.intake.intakeSpeed(leftPower, rightPower);
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
