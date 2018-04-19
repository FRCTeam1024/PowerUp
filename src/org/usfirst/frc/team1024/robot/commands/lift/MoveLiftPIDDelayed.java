package org.usfirst.frc.team1024.robot.commands.lift;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.commands.auto.right.DeliverFirstCubeScaleCurve;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveLiftPIDDelayed extends MoveLiftPID {

    public MoveLiftPIDDelayed(Level level) {
        super(level);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(DeliverFirstCubeScaleCurve.ACTIVATE_LIFT) {
    		super.execute();
    	}
    }

}
