package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeWithJoystick extends Command {

    public IntakeWithJoystick() {
    	requires(Robot.intake);
    }
    
    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.intakeSpeed(Robot.oi.logi.getRawAxis(3));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	
    }
}
