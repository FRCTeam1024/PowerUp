package org.usfirst.frc.team1024.robot;

public class FieldConfig {
	private int switchPos = 1;
	private int scalePos = 1;
	public FieldConfig(String combinationString) {
		if(combinationString.length() > 0) { 
			if (combinationString.charAt(0) == 'L') {
				switchPos = -1;
			}
			if (combinationString.charAt(1) == 'L') {
				scalePos = -1;
			}
		}
	}
	
	public int getSwitchPosition() {
		return switchPos;
	}
	
	public int getScalePosition() {
		return scalePos;
	}
}
