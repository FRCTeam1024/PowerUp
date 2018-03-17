package org.usfirst.frc.team1024.robot.commands.Drive;

import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangeTrimPID extends Command {
	double trimP;
	double trimI;
	double trimD;
	double maxOutput;
    public ChangeTrimPID(double trimP, double trimI, double trimD, double maxOutput) {
    	this.trimP = trimP;
    	this.trimI = trimI;
    	this.trimD = trimD;
    	this.maxOutput = maxOutput;
    }

    protected void initialize() {
    	Robot.drivetrain.trimPID.setPID(trimP, trimI, trimD);
    	Robot.drivetrain.trimPID.setOutputRange(-maxOutput, maxOutput);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
