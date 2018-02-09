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
	
	private final double LIFT_KP = 0.0;
	private final double LIFT_KI = 0.0;
	private final double LIFT_KD = 0.0;
	
	public PIDController liftPID = new PIDController(LIFT_KP, LIFT_KI, LIFT_KD, Robot.lift.liftEncoder, output->{});
	
    public MoveLiftPID(Level level) {
    	requires(Robot.lift);
    	this.level = level;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	liftPID.setSetpoint(level.getHeight());
    	liftPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.moveCarriage(liftPID.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	liftPID.disable();
    	Robot.lift.stopLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	liftPID.disable();
    	Robot.lift.stopLift();
    }
}
