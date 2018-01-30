package org.usfirst.frc.team1024.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;


public class Sensors extends Subsystem {

	private static final double MILLIMETER_SCALE_FACTOR = 124;
	private static final double INCH_SCALE_FACTOR = MILLIMETER_SCALE_FACTOR * 0.3937;
	AnalogInput ultrasonic = new AnalogInput(0);
	double distanceI;

    public void initDefaultCommand() {
    }
    
    public double getDistanceInMillimeters() {
    	return getRawUltrasonic() * MILLIMETER_SCALE_FACTOR;
    }
    
    public double getDistanceInches() {
		return getRawUltrasonic() * INCH_SCALE_FACTOR;
    }
    
    public double getRawUltrasonic() {
    	return ultrasonic.getVoltage();
    }
}

