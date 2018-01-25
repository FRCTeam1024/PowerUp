package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.DriveDistance;
import org.usfirst.frc.team1024.robot.commands.ResetEncoder;
import org.usfirst.frc.team1024.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmarterDashboard {
	public void smartDash() {
		SmartDashboard.putNumber("Encoder Value: ", Robot.drivetrain.frontLeft.getSensorCollection().getQuadraturePosition());
		SmartDashboard.putNumber("Raw Encoder", Robot.drivetrain.getRawEncoder());
		SmartDashboard.putNumber("Encoder Distance (In.)", Robot.drivetrain.getDistanceInches());
		SmartDashboard.putNumber("Raw Encoder Quad", Robot.drivetrain.getRawQuad());
		SmartDashboard.putData("Reset Encoder", new ResetEncoder());
		SmartDashboard.putBoolean("isMoving", Robot.drivetrain.isMoving());
		
		SmartDashboard.putData("Move", new DriveDistance(SmartDashboard.getNumber("SET DISTANCE", 0)));
	}
}
