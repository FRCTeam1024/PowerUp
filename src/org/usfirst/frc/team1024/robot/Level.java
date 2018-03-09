package org.usfirst.frc.team1024.robot;

public enum Level {
	INTAKE (Constants.INTAKE_HEIGHT),
	SWITCH (Constants.SWITCH_HEIGHT),
	SCALE_OWNERSHIP (Constants.SCALE_OWNERSHIP_HEIGHT),
	SCALE_NEUTRAL (Constants.SCALE_NEUTRAL_HEIGHT),
	SCALE_LOSS (Constants.SCALE_LOSS_HEIGHT),
	PORTAL (Constants.PORTAL_HEIGHT);
	private final double height;
	
	Level(double height) {
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
}
