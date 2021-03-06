package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.RightScale;
import org.usfirst.frc.team1024.robot.commands.Drive.DriveStraight;
import org.usfirst.frc.team1024.robot.commands.auto.left.CrossToRightScale;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftCrossToRightScale;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftScaleEnd;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftScaleLeftScale;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftScaleLeftSwitch;
import org.usfirst.frc.team1024.robot.commands.auto.left.LeftSwitch;
import org.usfirst.frc.team1024.robot.commands.auto.middle.AutoSwitchFront;
import org.usfirst.frc.team1024.robot.commands.auto.middle.MiddleSwitchMiddleSwitch;
import org.usfirst.frc.team1024.robot.commands.auto.right.CrossToLeftScale;
import org.usfirst.frc.team1024.robot.commands.auto.right.RightScaleRightSwitch;
import org.usfirst.frc.team1024.robot.commands.auto.right.RightSwitch;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CompetitionAutoChooser {

	private static CompetitionAutoChooser instance = new CompetitionAutoChooser();
	
	private SendableChooser<RobotPosition> robotPositionChooser = new SendableChooser<RobotPosition>();
	private SendableChooser<AutoObjective> goal1Chooser = new SendableChooser<AutoObjective>();
	private SendableChooser<AutoObjective> goal2Chooser = new SendableChooser<AutoObjective>();
	private SendableChooser<String> specialConditions = new SendableChooser<String>();
	
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
		log("initSmartDashboard");
		try {
			log("robotPositionChooser is null : " + (robotPositionChooser == null ? true : false));
			robotPositionChooser.addDefault(RobotPosition.RIGHT.toString(), RobotPosition.RIGHT);
			robotPositionChooser.addObject(RobotPosition.RIGHT.toString(), RobotPosition.RIGHT);
			robotPositionChooser.addObject(RobotPosition.LEFT.toString(), RobotPosition.LEFT);
			robotPositionChooser.addObject(RobotPosition.MIDDLE.toString(), RobotPosition.MIDDLE);
			log("adding robot position chooser");
			SmartDashboard.putData("Robot Position", robotPositionChooser);
			log("after adding robot position chooser");
			
			goal1Chooser = makeGoalChooser("Goal 1");
			SmartDashboard.putData("Goal 1", goal1Chooser);
			
			goal2Chooser = makeGoalChooser("Goal 2");
			SmartDashboard.putData("Goal 2", goal2Chooser);
			
			specialConditions.addDefault("Run Normal Chooser", "Run Normal Chooser");
			specialConditions.addObject("Do Switch if on robot's side, or do either scale", "Do Switch if on robot's side, or do either scale");
		} catch (Exception e) {
			System.out.println("error in initSmartDashboard");
			e.printStackTrace();
		}
	}
	
	private SendableChooser<AutoObjective> makeGoalChooser(String goalName) {
		SendableChooser<AutoObjective> goalChooser = new SendableChooser<AutoObjective>();
		
		goalChooser.addDefault(AutoObjective.NO_GOAL.toString(), AutoObjective.NO_GOAL);
		goalChooser.addObject(goalName, AutoObjective.NO_GOAL);
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
		System.out.println(robotPositionSelected);
		
		if(RobotPosition.RIGHT.equals(robotPositionSelected)) {
			robotPosition = RobotPosition.RIGHT;
		} else if(RobotPosition.LEFT.equals(robotPositionSelected)) {
			robotPosition = RobotPosition.LEFT;
		} else if(RobotPosition.MIDDLE.equals(robotPositionSelected)) {
			robotPosition = RobotPosition.MIDDLE;
		}
		System.out.println("Confirmed Robot Position");

		goal1 = getGoalFromChooser(goal1Chooser);
		goal2 = getGoalFromChooser(goal2Chooser);
		
		System.out.println("Confirmed Goals 1 and 2");

	}
	
	public Command chooseCommand() {
		
		FieldConfig fieldConfig = Robot.fieldConfig;
		getSmartDashboardChoices();
		// TODO log the choices, robotPosition, goal1, goal2, etc.
		if(robotPosition != null && goal1 != null && goal2 != null) {
			if(robotPosition != null) {
				log("Choices: robotPosition : " + robotPosition.toString());
			}
			if(goal1 != null ) {
				log("goal1 = " + goal1.toString());
			}
			if(goal2 != null) {
				log("goal2 = " + goal2.toString());
			}
		} else {
			log("One or more of the input choosers was null");
		}
		System.out.println("FieldConfig : " + fieldConfig);
		Command chosenCommand = null;
		
		switch (robotPosition) {
		case RIGHT:
			System.out.println("Right Case Ran");
			if (fieldConfig.isScaleRight()) {
				// give it a default just in case
				chosenCommand = new RightScale();
				if (fieldConfig.isSwitchRight()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							//because the robot is on the right, the scale is on the right, and the switch does not matter,
							//we want want the robot to do two cubes in the scale
							chosenCommand = new RightScale();
						} else if (AutoObjective.SWITCH_MY_SIDE.equals(goal2) || AutoObjective.SWITCH_EITHER.equals(goal2)) {
							//because the robot is on the right, the scale is on the right, and the switch is on the right,
							//we want the robot to do the right scale, then do the right switch
							chosenCommand = new RightScaleRightSwitch();
						} else if (AutoObjective.NO_GOAL.equals(goal2)) {
							//because the robot is on the right, the scale is on the right, and the switch doesn't matter,
							//we want the robot to just do one cube in the scale
							chosenCommand = new RightScale();
						}
					} else if (AutoObjective.SWITCH_MY_SIDE.equals(goal1) || AutoObjective.SWITCH_EITHER.equals(goal1)) {
						// default
						chosenCommand = new RightSwitch();
						if(AutoObjective.SCALE_MY_SIDE.equals(goal2)|| AutoObjective.SCALE_EITHER.equals(goal2)) {
							//because the robot is on the right, the scale is on the right, and the switch in on the right,
							//we want the robot to do the right scale, then do the right switch
							chosenCommand = new RightScaleRightSwitch();
						} else if (AutoObjective.NO_GOAL.equals(goal2)) {
							//because the robot is on the right, the scale is on the right, and the switch is on the right.
							//we want the robot to do two cubes in scale
							chosenCommand = new RightSwitch();
						}
					}
				} else if (fieldConfig.isSwitchLeft()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new RightScale();
						} else if (AutoObjective.SWITCH_EITHER.equals(goal2)) {
							//chosenCommand = new RightScaleLeftSwitch();
						} else if (AutoObjective.NO_GOAL.equals(goal2)) {
							chosenCommand = new RightScale();
						}
					} else if (AutoObjective.SCALE_EITHER.equals(goal2) || AutoObjective.SCALE_MY_SIDE.equals(goal2)) {
						//because the robot is on the right, the scale is on the right, and the switch is on the left
						//we want the robot do the right scale
						chosenCommand = new RightScale();
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
					//default
					chosenCommand = new CrossToLeftScale();
					if (AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new CrossToLeftScale();
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
						chosenCommand = new CrossToLeftScale();
					}
					
				}
			}
			break;
		case LEFT:
			System.out.println("Left Case Ran");

			if (fieldConfig.isScaleLeft()) {
				if (fieldConfig.isSwitchLeft()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new LeftScaleLeftScale();
						} else if (AutoObjective.SWITCH_MY_SIDE.equals(goal2) || AutoObjective.SWITCH_EITHER.equals(goal2)) {
							chosenCommand = new LeftScaleLeftSwitch();
						} else if (AutoObjective.NO_GOAL.equals(goal2)) {
							chosenCommand = new LeftScaleEnd();
						}
					} else if (AutoObjective.SWITCH_MY_SIDE.equals(goal1) || AutoObjective.SWITCH_EITHER.equals(goal1)) {
						//Add more options here later
						chosenCommand = new LeftSwitch();
					}
				} else if (fieldConfig.isSwitchRight()) {
					if (AutoObjective.SCALE_MY_SIDE.equals(goal1) || AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_MY_SIDE.equals(goal2) || AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new LeftScaleLeftScale();
						} else if (AutoObjective.SWITCH_EITHER.equals(goal2)) {
							//chosenCommand = new LeftScaleRightSwitch();
						} else if (AutoObjective.NO_GOAL.equals(goal2)) {
							chosenCommand = new LeftScaleEnd();
						}
					}
				}
			} else if (fieldConfig.isScaleRight()) {
				if (fieldConfig.isSwitchRight()) {
					chosenCommand = new CrossToRightScale();
					if (AutoObjective.SCALE_EITHER.equals(goal1)) {
						if (AutoObjective.SCALE_EITHER.equals(goal2)) {
							chosenCommand = new CrossToRightScale();
						}
					}
				}
				
			}
			break;
		case MIDDLE:
			System.out.println("Middle Case Ran");
			if (AutoObjective.SWITCH_EITHER.equals(goal1)) {
				if (AutoObjective.SWITCH_EITHER.equals(goal2)) {
					chosenCommand = new MiddleSwitchMiddleSwitch();
				} else if (AutoObjective.NO_GOAL.equals(goal2)) {
					chosenCommand = new AutoSwitchFront();
				}
			}
			break;
		default:
			break;
		}
		SmartDashboard.putString("Runnig Auto CommandGroup", chosenCommand.toString());
		System.out.println("Running "+ chosenCommand.toString() + " auto");
		return chosenCommand;
	}
}
