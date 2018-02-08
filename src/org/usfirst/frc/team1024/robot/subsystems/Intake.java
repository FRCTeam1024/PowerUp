package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    TalonSRX leftIntake = new TalonSRX(RobotMap.LEFT_INTAKE_MOTOR_PORT);
    TalonSRX rightIntake = new TalonSRX(RobotMap.RIGHT_INTAKE_MOTOR_PORT);
    Solenoid intakeSlide = new Solenoid(RobotMap.INTAKE_SLIDE_PORT);
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    
    }
    
    public void intakeSpeed(double Power) {
		leftIntake.set(ControlMode.PercentOutput, -Power);
		rightIntake.set(ControlMode.PercentOutput, Power);
	}
	
    public void intakeStop() {
    	leftIntake.set(ControlMode.PercentOutput, 0.0);
		rightIntake.set(ControlMode.PercentOutput, 0.0);
    }
    
    public void slideOut() {
    	intakeSlide.set(true);
    }
    
    public void slideIn() {
    	intakeSlide.set(false);
    }
}