package org.usfirst.frc.team1024.robot.commands.intake;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeEject extends Command {

    public IntakeEject() {
       requires(Robot.intake);
    }
    
    protected void initialize() {
    }

    protected void execute() {
    	Robot.intake.intakeSpeed(-1.0);
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
