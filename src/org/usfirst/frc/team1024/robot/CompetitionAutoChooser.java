package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
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
	
	private SendableChooser<String> robotPositionChooser = new SendableChooser<String>();
	private SendableChooser<String> goal1Chooser = new SendableChooser<String>();
	private SendableChooser<String> goal2Chooser = new SendableChooser<String>();
	
	private RobotPosition robotPosition;
	private AutoObjective goal1, goal2;
	
	private CompetitionAutoChooser() {}
	
	public static CompetitionAutoChooser getInstance() {
		return instance;
	}
	
	public enum AutoObjective {
		SWITCH_MY_SIDE ("Switch (my side)"),
		SWITCH_EITHER ("Switch (either side)"),
		SCALE_MY_SIDE ("Scale (my side)"),
		SCALE_EITHER ("Scale (either side)"),
		NO_GOAL ("No Goal");
		private final String name;
		AutoObjective(String name) {
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
	}
	
	public enum RobotPosition {
		RIGHT ("Right"), 
		LEFT ("Left"), 
		MIDDLE ("Middle");
		private final String name;
		RobotPosition(String name) {
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
		
//		public static RobotPosition fromString(String position) {
//			
//		}
	}
	
	private void log(String msg) {
		System.out.println(msg);
	}
	
	// TODO call this from Robot.robotInit()
	public void initSmartDashboard() {
		// TODO should be able to do this here, that way it's all consistent and connected here
		System.out.println("initSmartDashboard");
		log("initSmartDashboard 2");
		try {
			log("robotPositionChooser is null : " + (robotPositionChooser == null ? true : false));
			robotPositionChooser.addObject(RobotPosition.RIGHT.toString(), RobotPosition.RIGHT.toString());
			robotPositionChooser.addObject(RobotPosition.LEFT.toString(), RobotPosition.LEFT.toString());
			robotPositionChooser.addObject(RobotPosition.MIDDLE.toString(), RobotPosition.MIDDLE.toString());
			log("adding robot position chooser");
			SmartDashboard.putData("Robot Position", robotPositionChooser);
			log("after adding robot position chooser");
			
			goal1Chooser = makeGoalChooser();
			SmartDashboard.putData("Goal 1", goal1Chooser);
			
			goal2Chooser = makeGoalChooser();
			SmartDashboard.putData("Goal 2", goal2Chooser);
		} catch (Exception e) {
			System.out.println("error in initSmartDashboard");
			e.printStackTrace();
		}
	}
	
	private SendableChooser<String> makeGoalChooser() {
		SendableChooser<String> goalChooser = new SendableChooser<String>();
		goalChooser.addObject(AutoObjective.SCALE_MY_SIDE.toString(), AutoObjective.SCALE_MY_SIDE.toString());
		goalChooser.addObject(AutoObjective.SCALE_EITHER.toString(), AutoObjective.SCALE_EITHER.toString());
		goalChooser.addObject(AutoObjective.SWITCH_MY_SIDE.toString(), AutoObjective.SWITCH_MY_SIDE.toString());
		goalChooser.addObject(AutoObjective.SWITCH_EITHER.toString(), AutoObjective.SWITCH_EITHER.toString());
		return goalChooser;
	}	
	
	private AutoObjective getGoalFromChooser(SendableChooser<String> goalChooser) {
		String goalSelected = goalChooser.getSelected();
		if(AutoObjective.SCALE_MY_SIDE.toString().equals(goalSelected)) {
			return AutoObjective.SCALE_MY_SIDE;
		} else if(AutoObjective.SCALE_EITHER.toString().equals(goalSelected)) {
			return AutoObjective.SCALE_EITHER;
		} else if(AutoObjective.SWITCH_MY_SIDE.toString().equals(goalSelected)) {
			return AutoObjective.SWITCH_MY_SIDE;
		} else if(AutoObjective.SWITCH_EITHER.toString().equals(goalSelected)) {
			return AutoObjective.SWITCH_EITHER;
		}
		return null;
	}
	
	private void getSmartDashboardChoices() {
		String robotPositionString = robotPositionChooser.getSelected();
//		RobotPosition robotPositionSelected = RobotPosition.;
		
		if(RobotPosition.RIGHT.toString().equals(robotPositionString)) {
			robotPosition = RobotPosition.RIGHT;
		} else if(RobotPosition.LEFT.toString().equals(robotPositionString)) {
			robotPosition = RobotPosition.LEFT;
		} else if(RobotPosition.MIDDLE.toString().equals(robotPositionString)) {
			robotPosition = RobotPosition.MIDDLE;
		}
		
		goal1 = getGoalFromChooser(goal1Chooser);
		goal2 = getGoalFromChooser(goal2Chooser);
	}
	
	public Command chooseCommand() {
		
		FieldConfig fieldConfig = Robot.fieldConfig;
		getSmartDashboardChoices();
		// TODO log the choices, robotPosition, goal1, goal2, etc.
		if(robotPosition == null || goal1 == null || goal2 == null) {
			System.out.println("Choices NOT MADE, DEFAULTING AUTO COMMAND");
//			return new DriveStraight(20);
			return new RightScaleRightScale();
		}
		System.out.println("Choices: " + robotPosition.toString() + ", " + goal1.toString() + ", " + goal2.toString());
		System.out.println("FieldConfig : " + fieldConfig);
		
		Command chosenCommand = null;
		
		switch (robotPosition) {
		case RIGHT:
			if (fieldConfig.isScaleRight()) {
				if (fieldConfig.isSwitchRight()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new RightScaleRightScale();
						} else if (AutoObjective.SWITCH_MY_SIDE.equals(goal2) || AutoObjective.SWITCH_EITHER.equals(goal2)) {
							chosenCommand = new RightScaleRightSwitch();
						} else if (AutoObjective.NO_GOAL.equals(goal2)) {
							//chosenCommand = new RightScale();
						}
					} else if (AutoObjective.SWITCH_MY_SIDE.equals(goal1) || AutoObjective.SWITCH_EITHER.equals(goal1)) {
						if(AutoObjective.SCALE_MY_SIDE.equals(goal2)|| AutoObjective.SCALE_EITHER.equals(goal2)) {
							
						} else if (AutoObjective.NO_GOAL.equals(goal2)) {
							//chosenCommand = new RightSwitch();
						}
					}
				} else if (fieldConfig.isSwitchLeft()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							//chosenCommand = new RightScaleRightScale();
						} else if (AutoObjective.SWITCH_EITHER.equals(goal2)) {
							//chosenCommand = new RightScaleLeftSwitch();
						} else if (AutoObjective.NO_GOAL.equals(goal2)) {
							//chosenCommand = new RightScale();
						}
					} else if (AutoObjective.SWITCH_EITHER.equals(goal1)) {
						if (AutoObjective.SWITCH_EITHER.equals(goal2)) {
							//chosenCommand = new LeftSwitchCrossLeftSwitch();
						} else if (AutoObjective.NO_GOAL.equals(goal2)) {
							//chosenCommand = new LeftSwitchCross();
						}
					}
				}
			} else if (fieldConfig.isScaleLeft()) {
				if (fieldConfig.isSwitchLeft()) {
					if (AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_EITHER.equals(goal2)) {
							//chosenCommand = LeftScaleCrossLeftScale();
						} else if (AutoObjective.SWITCH_EITHER.equals(goal2)) {
							//chosenCommand = LeftScaleCrossLeftSwitch();
						} else if (AutoObjective.NO_GOAL.equals(goal2)) {
							//chosenCommand = new CrossToLeftScale(); //This is one of the ones that need to be renamed to fit new conventions
						} else if (AutoObjective.SWITCH_EITHER.equals(goal1)) {
							//chosenCommand = new LeftSwitchCross();
						}
					} else if (AutoObjective.SWITCH_EITHER.equals(goal1)) {
						if (AutoObjective.SWITCH_EITHER.equals(goal2)) {
							//chosenCommand = new LeftSwitchCrossLeftSwitch();
						/**
						 * Left off here
						 */
						
						} else if (AutoObjective.SCALE_EITHER.equals(goal2)) {
							//chosenCommand = new 
						}
					}
				} else if (fieldConfig.isSwitchRight()) {
					if (AutoObjective.SCALE_EITHER.equals(goal1)) {
					}
					
				}
			}
			break;
		case LEFT:
			if (fieldConfig.isScaleLeft()) {
				if (fieldConfig.isSwitchLeft()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							//chosenCommand = new LeftScaleLeftScale();
						} else if (AutoObjective.SWITCH_MY_SIDE.equals(goal2) || AutoObjective.SWITCH_EITHER.equals(goal2)) {
							//chosenCommand = new LeftScaleLeftSwitch();
						}
					}
				} else if (fieldConfig.isSwitchRight()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							
						} else if (AutoObjective.SWITCH_EITHER.equals(goal2)) {
							//chosenCommand = new LeftScaleRightSwitch();
						}
					}
				}
			} else if (fieldConfig.isScaleRight()) {
				if (fieldConfig.isSwitchRight()) {
					
				}
			}
			break;
		case MIDDLE:
			break;
		default:
			break;
		}
		System.out.println("Running " + chosenCommand.toString() + " auto");
		return chosenCommand;
	}
}
