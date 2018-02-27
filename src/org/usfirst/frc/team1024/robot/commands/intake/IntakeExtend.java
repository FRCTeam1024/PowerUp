package org.usfirst.frc.team1024.robot.commands.intake;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeExtend extends Command {
	
    public IntakeExtend() {
    	requires(Robot.intake);
    }

    protected void initialize() {
    	System.out.println("before Slide out");
    	Robot.intake.slideOut();
    	System.out.println("after Slide out");
    }
    
    protected void execute() {
    	System.out.println("Execute Extend");
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
