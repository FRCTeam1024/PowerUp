package org.usfirst.frc.team1024.robot.subsystems;

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
	//2 Motors, 4 Solenoids.
	//2 Solenoids per arm (to slide & manipulate).
	//1 Motor per arm (each arm having two wheels controlled by one).
    private TalonSRX leftIntake = new TalonSRX(RobotMap.LEFT_INTAKE_MOTOR_PORT);
    private Solenoid leftIntakeSlide = new Solenoid(RobotMap.INTAKE_SLIDE_PORT);
    private Solenoid leftIntakePos = new Solenoid(RobotMap.INTAKE_POS_PORT);
    
    private TalonSRX rightIntake = new TalonSRX(RobotMap.RIGHT_INTAKE_MOTOR_PORT);
    
    private DigitalOutput breakBeamEmitter = new DigitalOutput(RobotMap.INTAKE_BREAKBEAM_EMITTER_PORT);
    private DigitalInput breakBeamReciever = new DigitalInput(RobotMap.INTAKE_BREAKBEAM_RECIEVER_PORT);
    
    public Intake () {
    	breakBeamEmitter.set(true);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
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
    }
    
    public void slideIn() {
    	posIn();
    	leftIntakeSlide.set(false);
    }
    
    public void posOut() {
    	leftIntakePos.set(true);
    }
    
    public void posIn() {
    	leftIntakePos.set(false);
    }

	public boolean cubeDetecterState() {
		return breakBeamReciever.get();
	}
	
	public void outputToSmartDashboard() {
		SmartDashboard.putBoolean("BreakBeam", cubeDetecterState());
	}
}