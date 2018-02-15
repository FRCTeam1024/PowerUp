package org.usfirst.frc.team1024.robot.commands.intake;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeAcquire extends Command {

    public IntakeAcquire() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Trying to aquire a cube");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.intakeSpeed(1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Aquiring cube");
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.intakeStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.intakeStop();
    }
}
