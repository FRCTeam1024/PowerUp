package org.usfirst.frc.team1024.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;



/**
 *
 */
public class Sensors extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	AnalogInput ultrasonic = new AnalogInput(0);
	double distance;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
    public double getDistanceInMillimeters() {
    	double volts = ultrasonic.getVoltage();
    	distance = volts/4096;
    	return distance;
    }
    
    public double getDistanceInches() {
    	
    }
    
    private double getRawUltrasonic() {
    	
    }
}

