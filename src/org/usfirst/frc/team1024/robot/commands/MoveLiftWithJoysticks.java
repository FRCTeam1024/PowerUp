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
    	if (Robot.lift.getLiftEncoderValue() < 300000 && !Robot.oi.getOverrideButton()) {
    		Robot.lift.configMaxOutputs(0.25);
    	} else {
    		Robot.lift.configMaxOutputs(1.0);
    	}
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
