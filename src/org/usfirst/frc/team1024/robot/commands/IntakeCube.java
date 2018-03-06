package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeCube extends Command {
	
	boolean isDone;
	
    public IntakeCube() {
        requires(Robot.intake);
        requires(Robot.lift);
        isDone = false;
    }

    protected void initialize() {
    	Robot.intake.slideOut();
    	Timer.delay(0.5); //Needs to be trimmed down
    	Robot.lift.clamp(true);
    	Robot.intake.intakeSpeed(1.0);
    }

    protected void execute() {
    	if (!Robot.intake.cubeDetecterState() == true) {
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
    	
    }

    protected void interrupted() {
    	isDone = false;
    }
}
