package org.usfirst.frc.team1024.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TrimPID extends PIDSubsystem {
    // Initialize your subsystem here
	AHRS navx = new AHRS(Port.kMXP);
	double output = 0;

    public TrimPID() {
    	super("trimPID", 10.0, 0.0, 0.0);
    	getPIDController().setAbsoluteTolerance(0.1);
    	//PIDController pid = getPIDController();
    	getPIDController().setInputRange(-180, 180);
    	getPIDController().setContinuous();
    	getPIDController().setOutputRange(-0.3, 0.3);
    	getPIDController().enable();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return navx.getAngle();
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	this.output = output;
    	SmartDashboard.putNumber("trimPID output", output);
    }
    public double getOutput() {
    	return output;
    }
}
