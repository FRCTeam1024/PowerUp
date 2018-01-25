package org.usfirst.frc.team1024.robot.commands;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnToAngle extends Command {
	double angle;
	boolean initialized = false;
	double rotatePower;
    public TurnToAngle(double angle) {
    	requires(Robot.drivetrain);
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetGyro();
    	//Robot.drivetrain.reset();
    	/*Robot.drivetrain.(SmartDashboard.getNumber("Turn KP", Constants.TURN_KP),
    									SmartDashboard.getNumber("Turn KI", Constants.TURN_KI),
    									SmartDashboard.getNumber("Turn KD", Constants.TURN_KD), 
    									SmartDashboard.getNumber("Turn KF", Constants.TURN_KF));
		*/
		Robot.drivetrain.setSetpoint(angle);
		Robot.drivetrain.enable();
		initialized = true;
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//rotatePower = Robot.drivetrain.turnPID.get();
    	Robot.drivetrain.drive(-Robot.drivetrain.pidGet, Robot.drivetrain.pidGet);
    	SmartDashboard.putNumber("rotatePower", rotatePower);
    	SmartDashboard.putNumber("Angle: ", Robot.drivetrain.getHeading());
    	SmartDashboard.putBoolean("isMoving", Robot.drivetrain.isMoving());
    	System.out.println("I got here");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return initialized && !Robot.drivetrain.isMoving() && Math.abs(angle - Robot.drivetrain.getHeading()) < 10; //fiddle with the 10 number
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    	initialized = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    	initialized = false;
    }
}
