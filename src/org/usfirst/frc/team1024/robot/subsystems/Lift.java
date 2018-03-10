package org.usfirst.frc.team1024.robot.subsystems;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.Robot;
import org.usfirst.frc.team1024.robot.RobotMap;
import org.usfirst.frc.team1024.robot.commands.lift.MoveLiftWithJoysticks;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Lift extends Subsystem {
	
	public TalonSRX liftMotor1 = new TalonSRX(RobotMap.LIFT_MOTOR_1_PORT);
	private TalonSRX liftMotor2 = new TalonSRX(RobotMap.LIFT_MOTOR_2_PORT);
	private Solenoid clamp = new Solenoid(RobotMap.LIFT_CLAMP_PORT);
	private Solenoid airbag = new Solenoid(RobotMap.AIRBAG_PORT);
	
	public Lift () {
		liftMotor2.set(ControlMode.Follower, liftMotor1.getDeviceID());
		liftMotor1.config_kP(0, Constants.LIFT_KP, 10);
		liftMotor1.config_kI(0, Constants.LIFT_KI, 10);
		liftMotor1.config_kD(0, Constants.LIFT_KD, 10);
		
		liftMotor2.config_kP(0, Constants.LIFT_KP, 10);
		liftMotor2.config_kI(0, Constants.LIFT_KI, 10);
		liftMotor2.config_kD(0, Constants.LIFT_KD, 10);
		configMaxOutputs(1.0);
		liftMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	}
	
	public void moveCarriage(double power) {
		liftMotor1.set(ControlMode.PercentOutput, -power);
		liftMotor2.set(ControlMode.PercentOutput, -power);
	}
	
	public void setPIDSetpoint(double setpoint) {
		liftMotor1.set(ControlMode.Position, setpoint);
		liftMotor2.set(ControlMode.Position, setpoint);
	}
	
	public void clamp(boolean value) {
		clamp.set(value);
	}
	
	public void stopLift() {
		moveCarriage(0.0);
	}
	
	public void resetEncoder() {
		liftMotor1.setSelectedSensorPosition(0, 0, 10);
	}
	
	public void outputToSmartDashboard() {
		SmartDashboard.putNumber("Lift Motor Rotations", liftMotor1.getSelectedSensorPosition(0) / 4096);
		SmartDashboard.putNumber("Lift Encoder Raw", liftMotor1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("LiftMotorOutPut", liftMotor1.getMotorOutputPercent());
		
	}
	
	public double getLiftEncoderValue() {
		return liftMotor1.getSelectedSensorPosition(0);
	}
	
	public void configMaxOutputs(double maxPower) {
		liftMotor1.configPeakOutputForward(maxPower, 10);
		liftMotor1.configPeakOutputReverse(-maxPower, 10);
		liftMotor2.configPeakOutputForward(maxPower, 10); 
		liftMotor2.configPeakOutputReverse(-maxPower, 10);
	}
	
	public double getCommandedOutput() {
		return liftMotor2.getMotorOutputPercent();
	}
	
	public void engageAirbag() {
		airbag.set(true);
	}
	
	public void disengageAirBag() {
		airbag.set(false);
	}
	
	
    public void initDefaultCommand() {
    	setDefaultCommand(new MoveLiftWithJoysticks());
    }
}

