package org.usfirst.frc.team1024.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class CompetitionAuto {
	private static CompetitionAuto singleton = new CompetitionAuto();
	private ArrayList stuff;

	private CompetitionAuto() {
	}

	/* Static 'instance' method */
	public static CompetitionAuto getInstance() {
		return singleton;
	}

	public SendableChooser<String> populateChooser() {
		return new SendableChooser<>();
	}

	private enum RobotPosition {
		LEFT,
		RIGHT,
		CENTER;
	}
	
	private enum Objective {
		SCALE_EITHER,
		SCALE_MY,
		SWITCH_EITHER,
		SWITCH_MY;
	}

	public Command getCommand(RobotPosition robotPosition, Objective goal1, Objective goal2, Objective goal3) {
		switch (robotPosition) {
			case LEFT:
				switch(goal1) {
					case SCALE_EITHER:
						switch(goal2) {
							case SCALE_EITHER:
								switch(goal3) {
									case SCALE_EITHER:
										
										break;
									case SCALE_MY:
										break;
									case SWITCH_EITHER:
										break;
									case SWITCH_MY:
										break;
								}
								break;
							case SCALE_MY:
								break;
							case SWITCH_EITHER:
								break;
							case SWITCH_MY:
								break;
						}
						break;
					case SCALE_MY:
						break;
					case SWITCH_EITHER:
						break;
					case SWITCH_MY:
						
						break;
				}
				break;
			case RIGHT:
				break;
			case CENTER:
				break;
		}
		return null;
	}
}
