package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLiftWithJoysticks extends Command {

    public MoveLiftWithJoysticks() {
    	requires(Robot.lift);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.lift.moveCarriage(Robot.oi.logi.getRawAxis(Constants.LIFT_STICK_AXIS));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
