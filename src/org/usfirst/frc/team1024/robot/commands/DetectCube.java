package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.ConditionalCommand;

/**
 *
 */
public class DetectCube extends ConditionalCommand {

    public DetectCube() {
    	super(new GrabCube());
    	requires(Robot.intake);
    }

    protected void initialize() {
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }

	@Override
	protected boolean condition() {
		return !Robot.intake.cubeDetecterState();
	}
}
