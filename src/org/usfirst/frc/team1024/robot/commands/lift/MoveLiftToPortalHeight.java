package org.usfirst.frc.team1024.robot.commands.lift;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveLiftToPortalHeight extends CommandGroup {

    public MoveLiftToPortalHeight() {
        addSequential(new MoveLiftPID(Level.PORTAL));
    }
    
    protected void initialize() {
    	
    }
    
    protected void execute() {
    	if (!Robot.oi.logi.getRawButton(Constants.REACH_PORTAL_HEIGHT)) {
    		this.cancel();
    	}
    }
    
    protected boolean isFinished() {
		return false;
    	
    }
    
    protected void end() {
    	
    }
    
    protected void interrupted() {
    	
    }
}
