package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.auto.left.LeftScaleLeftScale;
import org.usfirst.frc.team1024.robot.commands.auto.right.CrossToLeftScale;
import org.usfirst.frc.team1024.robot.commands.auto.right.RightScaleRightScale;
import org.usfirst.frc.team1024.robot.commands.auto.right.RightScaleRightSwitch;
import org.usfirst.frc.team1024.robot.commands.auto.right.RightSwitch;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CompetitionAutoChooser {

	private static CompetitionAutoChooser instance = new CompetitionAutoChooser();
	
	SendableChooser<String> robotPositionChooser = new SendableChooser<String>();
	SendableChooser<String> goal1Chooser = new SendableChooser<String>();
	SendableChooser<String> goal2Chooser = new SendableChooser<String>();
	
	RobotPosition robotPosition;
	AutoObjective goal1, goal2, goal3;
	
	private CompetitionAutoChooser() {}
	
	public static CompetitionAutoChooser getInstance() {
		return instance;
	}
	
	public enum AutoObjective {
		SWITCH_MY_SIDE, SWITCH_EITHER, SCALE_MY_SIDE, SCALE_EITHER;
	}
	
	public enum RobotPosition {
		RIGHT, LEFT, MIDDLE;
	}
	
	// TODO call this from Robot.robotInit()
	public void initSmartDashboard() {
		// TODO should be able to do this here, that way it's all consistent and connected here
		robotPositionChooser.addObject("Right", "Right");
		robotPositionChooser.addObject("Left", "Left");
		robotPositionChooser.addObject("Middle", "Middle");
		SmartDashboard.putData("Robot Position", robotPositionChooser);
		
		goal1Chooser = makeGoalChooser();
		SmartDashboard.putData("Goal 1", goal1Chooser);
		
		goal2Chooser = makeGoalChooser();
		SmartDashboard.putData("Goal 2", goal2Chooser);
	}
	
	private SendableChooser<String> makeGoalChooser() {
		SendableChooser<String> goalChooser = new SendableChooser<String>();
		goalChooser.addObject("Scale (my side)", "Scale (my side)");
		goalChooser.addObject("Scale (either side)", "Scale (either side)");
		goalChooser.addObject("Switch (my side)", "Switch (my side)");
		goalChooser.addObject("Switch (either side)", "Switch (either side)");
		return goalChooser;
	}
	
	private void getSmartDashboardChoices() {
		// ugh this is so ugly and janky but I'm tired
		String robotPositionStr = robotPositionChooser.getSelected();
		// these strings should be constants, not 'magic' strings that you hope are equal
		if("Right".equals(robotPositionStr)) {
			robotPosition = RobotPosition.RIGHT;
		} else if("Left".equals(robotPositionStr)) {
			robotPosition = RobotPosition.LEFT;
		} else if("Middle".equals(robotPositionStr)) {
			robotPosition = RobotPosition.MIDDLE;
		}
		
		goal1 = getGoalFromChooser(goal1Chooser);
		goal2 = getGoalFromChooser(goal2Chooser);
	}
	
	private AutoObjective getGoalFromChooser(SendableChooser<String> goalChooser) {
		String chosenGoalStr = goalChooser.getSelected();
		if("Scale (my side)".equals(chosenGoalStr)) {
			return AutoObjective.SCALE_MY_SIDE;
		} else if("Scale (either side)".equals(chosenGoalStr)) {
			return AutoObjective.SCALE_EITHER;
		} else if("Switch (my side".equals(chosenGoalStr)) {
			return AutoObjective.SWITCH_MY_SIDE;
		} else if("Switch (either side)".equals(chosenGoalStr)) {
			return AutoObjective.SWITCH_EITHER;
		}
		return null;
	}
	
	public Command chooseCommand( ) {
		
		FieldConfig fieldConfig = Robot.fieldConfig;
		getSmartDashboardChoices();
		// TODO log the choices, robotPosition, goal1, goal2, etc.
		
		Command chosenCommand = null;
		switch (robotPosition) {
		case RIGHT:
			if(fieldConfig.isScaleRight()) {
				if(fieldConfig.isSwitchRight()) {
					if(AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if(AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new RightScaleRightScale();
						} else if(AutoObjective.SWITCH_MY_SIDE.equals(goal2)) {
							chosenCommand = new RightScaleRightSwitch();
						}
					} else if(AutoObjective.SWITCH_MY_SIDE.equals(goal1)) {
						// just another way to do it, return it from here
						return new RightSwitch();
					}
				} else if(fieldConfig.isSwitchLeft()) {
					if(AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if(AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new RightScaleRightScale();
						}
					}
				}
			} else if(Robot.fieldConfig.isScaleLeft()) {
				if(AutoObjective.SCALE_EITHER.equals(goal1)) {
					// not sure which was the latest, CrossToLeftScale or FastCrossToScale
					// but FastCrossToScale doesn't look right
					chosenCommand = new CrossToLeftScale();
				}
			}
			break;
		case LEFT:
			if (fieldConfig.isScaleLeft()) {
				if(fieldConfig.isSwitchLeft()) {
					if(AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if(AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new LeftScaleLeftScale();
						}
					}
				}
			}
			break;
		}
		return chosenCommand;
	}
}
