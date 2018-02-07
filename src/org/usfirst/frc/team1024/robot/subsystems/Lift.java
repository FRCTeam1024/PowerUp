package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Level;
import org.usfirst.frc.team1024.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {
	
	private TalonSRX liftMotor1 = new TalonSRX(RobotMap.LIFT_MOTOR_1_PORT);
	private TalonSRX liftMotor2 = new TalonSRX(RobotMap.LIFT_MOTOR_2_PORT);
	
	public void moveCarriage(double power) {
		liftMotor1.set(ControlMode.PercentOutput, power);
		liftMotor2.set(ControlMode.PercentOutput, power);
	}
	
	public void goToLevel(Level level) {
		if (!overrideButton) {
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    public void initDefaultCommand() {
    	
    }
}

