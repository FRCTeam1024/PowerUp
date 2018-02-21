package org.usfirst.frc.team1024.robot.commands.Drive;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ChangePosPID extends Command {
	double posP;
	double posI;
	double posD;
    public ChangePosPID(double posP, double posI, double posD) {
    	this.posP = posP;
    	this.posI = posI;
    	this.posD = posD;
    }

    protected void initialize() {
    	Robot.drivetrain.changePosPID(posP, posI, posD);
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
