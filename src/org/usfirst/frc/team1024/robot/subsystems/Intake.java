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
	//2 Motors, 4 Solenoids.
	//2 Solenoids per arm (to slide & manipulate).
	//1 Motor per arm (each arm having two wheels controlled by one).
    TalonSRX leftIntake = new TalonSRX(RobotMap.LEFT_INTAKE_MOTOR_PORT);
    Solenoid leftIntakeSlide = new Solenoid(RobotMap.LEFT_INTAKE_SLIDE_PORT);
    Solenoid leftIntakePos = new Solenoid(RobotMap.LEFT_INTAKE_POS_PORT);
    
    TalonSRX rightIntake = new TalonSRX(RobotMap.RIGHT_INTAKE_MOTOR_PORT);
    Solenoid rightIntakeSlide = new Solenoid(RobotMap.RIGHT_INTAKE_SLIDE_PORT);
    Solenoid rightIntakePos = new Solenoid(RobotMap.RIGHT_INTAKE_POS_PORT);
    
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
    	leftIntakeSlide.set(true);
    	rightIntakeSlide.set(true);
    }
    
    public void slideIn() {
    	leftIntakeSlide.set(false);
    	rightIntakeSlide.set(false);
    }
    
    public void posOut() {
    	leftIntakePos.set(true);
    	rightIntakePos.set(true);
    }
    
    public void posIn() {
    	leftIntakePos.set(true);
    	rightIntakePos.set(true);
    }
}