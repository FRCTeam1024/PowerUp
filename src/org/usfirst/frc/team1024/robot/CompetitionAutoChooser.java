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
	
	SendableChooser<RobotPosition> robotPositionChooser = new SendableChooser<RobotPosition>();
	SendableChooser<AutoObjective> goal1Chooser = new SendableChooser<AutoObjective>();
	SendableChooser<AutoObjective> goal2Chooser = new SendableChooser<AutoObjective>();
	
	RobotPosition robotPosition;
	AutoObjective goal1, goal2;
	
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
	}
	
	// TODO call this from Robot.robotInit()
	public void initSmartDashboard() {
		// TODO should be able to do this here, that way it's all consistent and connected here
		robotPositionChooser.addObject(RobotPosition.RIGHT.toString(), RobotPosition.RIGHT);
		robotPositionChooser.addObject(RobotPosition.LEFT.toString(), RobotPosition.LEFT);
		robotPositionChooser.addObject(RobotPosition.MIDDLE.toString(), RobotPosition.MIDDLE);
		SmartDashboard.putData("Robot Position", robotPositionChooser);
		
		goal1Chooser = makeGoalChooser();
		SmartDashboard.putData("Goal 1", goal1Chooser);
		
		goal2Chooser = makeGoalChooser();
		SmartDashboard.putData("Goal 2", goal2Chooser);
	}
	
	private SendableChooser<AutoObjective> makeGoalChooser() {
		SendableChooser<AutoObjective> goalChooser = new SendableChooser<AutoObjective>();
		goalChooser.addObject(AutoObjective.SCALE_MY_SIDE.toString(), AutoObjective.SCALE_MY_SIDE);
		goalChooser.addObject(AutoObjective.SCALE_EITHER.toString(), AutoObjective.SCALE_EITHER);
		goalChooser.addObject(AutoObjective.SWITCH_MY_SIDE.toString(), AutoObjective.SWITCH_MY_SIDE);
		goalChooser.addObject(AutoObjective.SWITCH_EITHER.toString(), AutoObjective.SWITCH_EITHER);
		return goalChooser;
	}	
	
	private AutoObjective getGoalFromChooser(SendableChooser<AutoObjective> goalChooser) {
		AutoObjective goalSelected = goalChooser.getSelected();
		if(AutoObjective.SCALE_MY_SIDE.equals(goalSelected)) {
			return AutoObjective.SCALE_MY_SIDE;
		} else if(AutoObjective.SCALE_EITHER.equals(goalSelected)) {
			return AutoObjective.SCALE_EITHER;
		} else if(AutoObjective.SWITCH_MY_SIDE.equals(goalSelected)) {
			return AutoObjective.SWITCH_MY_SIDE;
		} else if(AutoObjective.SWITCH_EITHER.equals(goalSelected)) {
			return AutoObjective.SWITCH_EITHER;
		}
		return null;
	}
	
	private void getSmartDashboardChoices() {
		RobotPosition robotPositionSelected = robotPositionChooser.getSelected();
		
		if(RobotPosition.RIGHT.equals(robotPositionSelected)) {
			robotPosition = RobotPosition.RIGHT;
		} else if(RobotPosition.LEFT.equals(robotPositionSelected)) {
			robotPosition = RobotPosition.LEFT;
		} else if(RobotPosition.MIDDLE.equals(robotPositionSelected)) {
			robotPosition = RobotPosition.MIDDLE;
		}
		
		goal1 = getGoalFromChooser(goal1Chooser);
		goal2 = getGoalFromChooser(goal2Chooser);
	}
	
	public Command chooseCommand() {
		
		FieldConfig fieldConfig = Robot.fieldConfig;
		getSmartDashboardChoices();
		// TODO log the choices, robotPosition, goal1, goal2, etc.
		System.out.println("Choices: " + robotPosition.toString() + ", " + goal1.toString() + ", " + goal2.toString());
		
		
		Command chosenCommand = null;
		if (robotPosition.equals(RobotPosition.LEFT)) {
			if (fieldConfig.isScaleLeft()) {
				if (fieldConfig.isSwitchLeft()) {
					switch(goal1) {
						case SCALE_EITHER:
						case SCALE_MY_SIDE:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						case SWITCH_EITHER:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						case SWITCH_MY_SIDE:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						case NO_GOAL:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						default:
							break;
					}
				} else if (fieldConfig.isSwitchRight()) {
					
				}
			} else if (fieldConfig.isScaleRight()) {
				if (fieldConfig.isSwitchLeft()) {
					
				} else if (fieldConfig.isSwitchLeft()) {
					
				}
			}
		} else if (robotPosition.equals(RobotPosition.RIGHT)) {
			if (fieldConfig.isScaleLeft()) {
				if (fieldConfig.isSwitchLeft()) {
					switch(goal1) {
						case SCALE_EITHER:
						case SCALE_MY_SIDE:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						case SWITCH_EITHER:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						case SWITCH_MY_SIDE:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						case NO_GOAL:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						default:
							break;
					}
				} else if (fieldConfig.isSwitchRight()) {
					
				}
			} else if (fieldConfig.isScaleRight()) {
				if (fieldConfig.isSwitchLeft()) {
					
				} else if (fieldConfig.isSwitchLeft()) {
					
				}
			}
		} else if (robotPosition.equals(RobotPosition.MIDDLE)) {
			if (fieldConfig.isScaleLeft()) {
				if (fieldConfig.isSwitchLeft()) {
					switch(goal1) {
						case SCALE_EITHER:
						case SCALE_MY_SIDE:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						case SWITCH_EITHER:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						case SWITCH_MY_SIDE:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						case NO_GOAL:
							switch(goal2) {
								case SCALE_EITHER:
									break;
								case SCALE_MY_SIDE:
									break;
								case SWITCH_EITHER:
									break;
								case SWITCH_MY_SIDE:
									break;
								case NO_GOAL:
									break;
								default:
									break;
							}
							break;
						default:
							break;
					}
				} else if (fieldConfig.isSwitchRight()) {
					
				}
			} else if (fieldConfig.isScaleRight()) {
				if (fieldConfig.isSwitchLeft()) {
					
				} else if (fieldConfig.isSwitchLeft()) {
					
				}
			}
		}
		
		
		
		
		
		
		
		
		
		switch (robotPosition) {
		case RIGHT:
			if (fieldConfig.isScaleRight()) {
				if (fieldConfig.isSwitchRight()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new RightScaleRightScale();
						} else if (AutoObjective.SWITCH_MY_SIDE.equals(goal2)) {
							chosenCommand = new RightScaleRightSwitch();
						}
					} else if (AutoObjective.SWITCH_MY_SIDE.equals(goal1)) {
						
						return new RightSwitch();
					}
				} else if (fieldConfig.isSwitchLeft()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new RightScaleRightScale();
						} else if (AutoObjective.SWITCH_EITHER.equals(goal2)) {
							chosenCommand = new RightScaleLeftSwitch();
						}
					} else if (AutoObjective.SWITCH_EITHER.equals(goal1)) {
						
					}
				}
			} else if (fieldConfig.isScaleLeft()) {
				if (AutoObjective.SCALE_EITHER.equals(goal1)) {
					// not sure which was the latest, CrossToLeftScale or FastCrossToScale
					// but FastCrossToScale doesn't look right
					chosenCommand = new CrossToLeftScale();
				}
			}
			break;
		case LEFT:
			if (fieldConfig.isScaleLeft()) {
				if (fieldConfig.isSwitchLeft()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new LeftScaleLeftScale();
						} else if (AutoObjective.SWITCH_MY_SIDE.equals(goal2) || AutoObjective.SWITCH_EITHER.equals(goal2)) {
							chosenCommand = new LeftScaleLeftSwitch();
						}
					}
				} else if (fieldConfig.isSwitchRight()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							
						} else if (AutoObjective.SWITCH_EITHER.equals(goal2)) {
							chosenCommand = new LeftScaleRightSwitch();
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
		System.out.println("Running " + chosenCommand.toString() + "auto");
		return chosenCommand;
	}
}
