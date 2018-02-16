package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.RobotMap;
import org.usfirst.frc.team1024.robot.commands.MoveLiftWithJoysticks;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lift extends Subsystem {
	
	private TalonSRX liftMotor1 = new TalonSRX(RobotMap.LIFT_MOTOR_1_PORT);
	private TalonSRX liftMotor2 = new TalonSRX(RobotMap.LIFT_MOTOR_2_PORT);
	private Solenoid clamp = new Solenoid(RobotMap.LIFT_CLAMP_PORT);
	
	public Lift () {
		liftMotor2.set(ControlMode.Follower, liftMotor1.getDeviceID());
		liftMotor1.config_kP(0, Constants.LIFT_KP, 10);
		liftMotor1.config_kI(0, Constants.LIFT_KI, 10);
		liftMotor1.config_kD(0, Constants.LIFT_KD, 10);
		liftMotor1.configPeakOutputForward(1.0, 10);
		liftMotor1.configPeakOutputReverse(-1.0, 10);
		liftMotor2.configPeakOutputForward(1.0, 10); //can maybe remove?
		liftMotor2.configPeakOutputReverse(-1.0, 10);
		liftMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
	}
	
	public void moveCarriage(double power) {
		liftMotor1.set(ControlMode.PercentOutput, -power);
		liftMotor2.set(ControlMode.PercentOutput, -power);
	}
	
	public void setPIDSetpoint(double setpoint) {
		liftMotor1.set(ControlMode.Position, setpoint);
	}
	
	public void clamp(boolean value) {
		clamp.set(value);
	}
	
	public void stopLift() {
		moveCarriage(0.0);
	}
	
	public void resetEncoder() {
		liftMotor1.getSelectedSensorPosition(0);
	}
	
	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("Lift Encoder Raw", liftMotor1.getSelectedSensorPosition(0));
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new MoveLiftWithJoysticks());
    }
}

