package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCube extends Command {
	
	boolean isDone;
	boolean hasInitialized;
	
    public IntakeCube() {
        requires(Robot.intake);
        requires(Robot.lift);
        isDone = false;
        hasInitialized = false;
    }

    protected void initialize() {
    }

    protected void execute() {
    	if (!Robot.oi.logi.getRawButton(Constants.INTAKE_START_ACQUIRE)) {
    		this.cancel();
    	}
    	if (hasInitialized == false) {
        	Robot.intake.slideOut();
        	Timer.delay(0.5); //Needs to be trimmed down
        	Robot.lift.clamp(true);
        	Robot.intake.intakeSpeed(1.0);
        	hasInitialized = true;
    	}
    	
    	if (Robot.intake.cubeDetecterState() == true) {
    		Timer.delay(0.5);
    		Robot.lift.clamp(false);
    		Robot.intake.intakeSpeed(0.0);
    		Robot.intake.slideIn();
    		Timer.delay(0.5); //Needs to be trimmed down
    		Robot.lift.setPIDSetpoint(Level.SWITCH.getHeight());
    		isDone = true;
    	}
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
    	isDone = false;
    	hasInitialized = false;
    }

    protected void interrupted() {
    	end();
    }
}
