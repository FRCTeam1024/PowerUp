package org.usfirst.frc.team1024.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrintToConsole extends Command {
	String print;
    public PrintToConsole(String print) {
    	this.print = print;
    }

    protected void initialize() {
    	System.out.println(print);
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
