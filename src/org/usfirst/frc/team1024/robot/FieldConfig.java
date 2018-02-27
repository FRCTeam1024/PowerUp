package org.usfirst.frc.team1024.robot;

public class FieldConfig {
	
	public enum POSITION {
		RIGHT(-1), LEFT(1), UNKNOWN(0);
		
		int intValue;
		
		private POSITION(int intValue) {
			this.intValue = intValue;
		}
		
		public int intValue() {
			return intValue;
		}
	}
	
	private POSITION switchPos = POSITION.UNKNOWN;
	private POSITION scalePos = POSITION.UNKNOWN;
	private boolean valid = true;
	
	public FieldConfig(String combinationString) {
		if(combinationString != null && combinationString.trim().length() > 0) { 
			
			combinationString = combinationString.toUpperCase(); // just to make comparisons easier
			
			if (combinationString.charAt(0) == 'L') {
				switchPos = POSITION.LEFT;
			} else if (combinationString.charAt(0) == 'R') {
				switchPos = POSITION.RIGHT;
			} else {
				valid = false;
			}
			
			if (combinationString.charAt(1) == 'L') {
				scalePos = POSITION.LEFT;
			} else if (combinationString.charAt(1) == 'R') {
				scalePos = POSITION.RIGHT;
			} else {
				valid = false;
			}
		}
	}
	
	public boolean isValid() {
		return valid;
	}
	
	public POSITION getSwitchPosition() {
		return switchPos;
	}
	
	public POSITION getScalePosition() {
		return scalePos;
	}
	
	// convenience methods
	public boolean isSwitchLeft() {
		return POSITION.LEFT.equals(switchPos);
	}
	
	public boolean isScaleLeft() {
		return POSITION.LEFT.equals(scalePos);
	}
	
	public boolean isSwitchRight() {
		return POSITION.RIGHT.equals(switchPos);
	}
	
	public boolean isScaleRight() {
		return POSITION.RIGHT.equals(scalePos);
	}
}
