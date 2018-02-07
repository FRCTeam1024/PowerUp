package org.usfirst.frc.team1024.robot;

public enum Level {
	INTAKE (0.0),
	SWITCH (0.0),
	SCALE_OWNERSHIP (0.0),
	SCALE_NEUTRAL (0.0),
	SCALE_LOSS (0.0);
	private final double height;
	Level(double height) {
		this.height = height;
	}
	
	private double height() {
		return height;
	}
}
