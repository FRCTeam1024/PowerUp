
package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnRelative extends Command {
	double targetAngle;
	boolean isDone;
	int onTargetCount = 0;
    public TurnRelative(double targetAngle) {
    	requires(Robot.drivetrain);
    	this.targetAngle = targetAngle;
    	isDone = false;
    }

    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    	Robot.drivetrain.turnPID.setSetpoint(targetAngle);
    	Robot.drivetrain.turnPID.enable();
    }

    protected void execute() {
    	Robot.drivetrain.pidTurn();
    	isDone = true;
    }
    
    private boolean isOnTarget() {
    	return Math.abs(Robot.drivetrain.getHeading() - targetAngle) < 1;  //if the robot is within 1 degrees of the target, stop
    	//return Robot.drivetrain.turnPID.onTarget();
    }
        
    protected boolean isFinished() {
    	if (isOnTarget()) {
    		onTargetCount++;
    	} else {
    		onTargetCount = 0;
    	}
    	
    	if(onTargetCount == 30) {
    		return isDone;
    	} else {
    		return false;
    	}
    }

    protected void end() {
    	Robot.drivetrain.stop();
    	Robot.drivetrain.turnPID.disable();
    	onTargetCount = 0;
    }

    protected void interrupted() {
    	Robot.drivetrain.stop();
    	Robot.drivetrain.turnPID.disable();
    	onTargetCount = 0;
    }
}
