package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLiftPID extends Command {
	Level level;
	int count = 0;
	boolean isDone = false;
	
	
	
    public MoveLiftPID(Level level) {
    	requires(Robot.lift);
    	this.level = level;
    }

    protected void initialize() {
    	count = 0;
    	System.out.println("Trying to move the lift to " + level.getHeight());
    	Robot.lift.setPIDSetpoint(level.getHeight());
    }

    protected void execute() {
    	count++;
    	Robot.lift.moveCarriage(0.5);
    	System.out.print("I have lifted " + count + " times!");
    	if(count > 100) {
    		isDone = true;
    	}
    }

    protected boolean isFinished() {
        return isDone;
    }

    protected void end() {
    	Robot.lift.stopLift();
    }

    protected void interrupted() {
    	Robot.lift.stopLift();
    }
}
