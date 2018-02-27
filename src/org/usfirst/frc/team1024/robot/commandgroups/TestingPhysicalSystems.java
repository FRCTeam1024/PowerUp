package org.usfirst.frc.team1024.robot.commandgroups;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.commands.Delay;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.ShiftHigh;
import org.usfirst.frc.team1024.robot.commands.Drive.ShiftLow;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeExtend;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeFlat;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeNarrow;
import org.usfirst.frc.team1024.robot.commands.intake.IntakeRetract;
import org.usfirst.frc.team1024.robot.commands.lift.CloseClamp;
import org.usfirst.frc.team1024.robot.commands.lift.MoveLiftPID;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestingPhysicalSystems extends CommandGroup {

    public TestingPhysicalSystems() {
    	
    	//Testing Intake solenoids
    	System.out.println("I'm getting called to extend!");
    	addSequential(new IntakeExtend());
    	addSequential(new Delay(10));	
    	/*addSequential(new IntakeFlat());
    	addSequential(new Delay(10));
    	addSequential(new IntakeNarrow());
    	addSequential(new Delay(10)); */
    	System.out.println("I'm getting called to retract!");
    	addSequential(new IntakeRetract());
    	addSequential(new Delay(10));
    	//Testing Lift Movement to the Switch and the 3 Scale Heights and the Clamp Movement
    	/*addSequential(new MoveLiftPID(Level.SWITCH));
    	addSequential(new Delay(10));
    	addSequential(new MoveLiftPID(Level.SCALE_OWNERSHIP));
    	addSequential(new Delay(10));
    	addSequential(new MoveLiftPID(Level.SCALE_NEUTRAL));
    	addSequential(new Delay(10));
    	addSequential(new MoveLiftPID(Level.SCALE_LOSS));
    	addSequential(new Delay(10));
    	addSequential(new OpenClamp());
    	addSequential(new Delay(10));
    	addSequential(new CloseClamp());
    	addSequential(new Delay(10));
    	//Testing Driving Shift
    	addSequential(new ShiftHigh());
    	addSequential(new Delay(10));
    	addSequential(new ShiftLow());
    	addSequential(new Delay(10));
    	//Testing Driving Motors
    	addSequential(new DriveStraight(10));
    	addSequential(new Delay(10));
    	    	*/
    }
}
