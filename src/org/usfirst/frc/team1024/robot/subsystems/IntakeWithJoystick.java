package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Constants;
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
    	Robot.intake.intakeSpeed(Robot.oi.logi.getRawAxis(Constants.INTAKE_STICK_AXIS));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	
    }
}