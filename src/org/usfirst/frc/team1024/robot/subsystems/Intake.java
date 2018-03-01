package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Intake extends Subsystem {
    private TalonSRX leftIntake = new TalonSRX(RobotMap.LEFT_INTAKE_MOTOR_PORT);
    private TalonSRX rightIntake = new TalonSRX(RobotMap.RIGHT_INTAKE_MOTOR_PORT);
    
    private Solenoid leftIntakeSlide = new Solenoid(RobotMap.INTAKE_SLIDE_PORT);
    private Solenoid leftIntakePos = new Solenoid(RobotMap.INTAKE_POS_PORT);
    
    private DigitalInput leftBumpDetector = new DigitalInput(RobotMap.LEFT_BUMP_CUBE_DETECTOR_PORT);
    private DigitalInput rightBumpDetector = new DigitalInput(RobotMap.RIGHT_BUMP_CUBE_DETECTOR_PORT);
    
    private boolean intakeInState = true;
    private boolean intakeWideState = true;
    
    public Intake () {
    }
    
    public void initDefaultCommand() {
    	setDefaultCommand(new IntakeWithJoystick());
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
    	intakeInState = false;
    }
    
    public void slideIn() {
    	posIn();
    	leftIntakeSlide.set(false);
    	Robot.lift.clamp(false);
    	intakeInState = true;
    }
    
    public void posOut() {
    	if (!intakeInState) {
    		leftIntakePos.set(true);
    		intakeWideState = false;
    	}
    }
    
    public void posIn() {
    	leftIntakePos.set(false);
    	intakeWideState = true;
    }

	public boolean cubeDetecterState() {
		if (leftBumpDetector.get() != true && rightBumpDetector.get() != true) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void outputToSmartDashboard() {
		SmartDashboard.putBoolean("BreakBeam", cubeDetecterState());
	}
	
	public boolean intakeWideState() {
		return intakeWideState;
	}
	
	public boolean intakeInState() {
		return intakeInState;
	}
}