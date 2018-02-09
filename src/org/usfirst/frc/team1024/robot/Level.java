package org.usfirst.frc.team1024.robot;

public enum Level {
	INTAKE (RobotMap.INTAKE_HEIGHT),
	SWITCH (RobotMap.SWITCH_HEIGHT),
	SCALE_OWNERSHIP (RobotMap.SCALE_OWNERSHIP_HEIGHT),
	SCALE_NEUTRAL (RobotMap.SCALE_NEUTRAL_HEIGHT),
	SCALE_LOSS (RobotMap.SCALE_LOSS_HEIGHT);
	private final double height;
	
	Level(double height) {
		this.height = height;
	}
	
	public double getHeight() {
		return height;
	}
}
