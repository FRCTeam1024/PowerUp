package org.usfirst.frc.team1024.robot.commands.auto.right;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.FieldConfig.POSITION;
import org.usfirst.frc.team1024.robot.commands.AutoDriveConstants;
import org.usfirst.frc.team1024.robot.commands.CrossTest;
import org.usfirst.frc.team1024.robot.commands.DeliverZaneSecondCubeToScale;
import org.usfirst.frc.team1024.robot.commands.DriveAndShift;
import org.usfirst.frc.team1024.robot.commands.TurnRightAndLift;
import org.usfirst.frc.team1024.robot.commands.Drive.ChangeDriveSpeed;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.Drive.TurnLeft;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverCubeToScale;
import org.usfirst.frc.team1024.robot.commands.bidirectional.DeliverSecondCubeToScale;
import org.usfirst.frc.team1024.robot.commands.bidirectional.GoGetSecondCube;
import org.usfirst.frc.team1024.robot.commands.lift.OpenClamp;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	deliver both cubes to scale on right side
 */
public class RightScaleEnd extends CommandGroup {

    public RightScaleEnd() {
    	
    		addSequential(new DeliverCubeToScale());
    		//addSequential(new GoGetSecondCube());
    		//addSequential(new DeliverSecondCubeToScale());
    	
    		
    }
}