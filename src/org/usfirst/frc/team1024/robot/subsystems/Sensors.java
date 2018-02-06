package org.usfirst.frc.team1024.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team1024.robot.Constants;
import org.usfirst.frc.team1024.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;


public class Sensors extends Subsystem {
	AnalogInput ultrasonic = new AnalogInput(RobotMap.ULTRASONIC_PORT);

    public void initDefaultCommand() {
    }
    
    public double getRawUltrasonic() {
    	return ultrasonic.getVoltage();
    }
    
    public double getDistanceInMillimeters() {
    	return getRawUltrasonic() * Constants.ULTRASONIC_MILLIMETER_SCALE_FACTOR;
    }
    
    public double getDistanceInches() {
		return getRawUltrasonic() * Constants.ULTRASONIC_INCH_SCALE_FACTOR;
    }
    
    
}

