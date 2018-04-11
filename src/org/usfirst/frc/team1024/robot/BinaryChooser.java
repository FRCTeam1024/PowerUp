package org.usfirst.frc.team1024.robot;

import org.usfirst.frc.team1024.robot.commands.LeftScaleScale;
import org.usfirst.frc.team1024.robot.commands.LeftScaleSwitch;
import org.usfirst.frc.team1024.robot.commands.LeftSwitchScale;
import org.usfirst.frc.team1024.robot.commands.LeftSwitchSwitch;
import org.usfirst.frc.team1024.robot.commands.RightScaleScale;
import org.usfirst.frc.team1024.robot.commands.RightScaleSwitch;
import org.usfirst.frc.team1024.robot.commands.RightSwitchSwitch;
import org.usfirst.frc.team1024.robot.commands.auto.middle.MiddleSwitchMiddleSwitch;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BinaryChooser {

	/**
	 * RobotPosition 0 = left 1 = right 2 = middle
	 * 
	 * OpponentScale 0 = no 1 = yes ReliableMiddleSwitch 0 = no 1 = yes
	 */

	public BinaryChooser() {

	}


	public int buildCode() {
		int autoCode = 0;

		if ("Right".equals(Robot.robotPosition.getSelected())) {
			autoCode += 8;
		}

		if ("Yes".equals(Robot.opponentScale.getSelected())) {
			autoCode += 4;
		}

		if (Robot.fieldConfig.isSwitchRight()) {
			autoCode += 2;
		}

		if (Robot.fieldConfig.isScaleRight()) {
			autoCode += 1;
		}
		SmartDashboard.putNumber("AutoCode", autoCode);
		return autoCode;
	}

	public Command chooseAuto() {
		Command chosenCommand = null;
		if (Robot.robotPosition.getSelected().equals("Middle")) {
			chosenCommand = new MiddleSwitchMiddleSwitch(); //ready to test
		} else {
			switch (buildCode()) {
			case 0:
			case 4:
				chosenCommand = new LeftScaleSwitch();
				// Left Zane Scale / Switch
				break;
			case 1:
				chosenCommand = new LeftSwitchSwitch();
				// Left Switch / Switch
				break;
			case 2:
			case 3:
			case 6:
			case 7:
				chosenCommand = new LeftScaleScale();
				// Left Zane Scale / Scale
				break;
			case 5:
				if (Robot.reliableMiddleSwitch.getSelected().equals("Yes")) {
					// Left Zane Scale / Switch
					chosenCommand = new LeftScaleSwitch();
				} else {
					chosenCommand = new LeftSwitchScale();
					// Left Switch / Scale
				}
				break;
			case 8: //scale left
			case 9: 
			case 12:
			case 13:
				// Right Zane Scale / Scale
				chosenCommand = new RightScaleScale(); //ready to be tested
				break;
			case 10:
				chosenCommand = new RightSwitchSwitch();
				// Right Switch / Switch
				break;
			case 11:
			case 15:
				// Right Zane Scale / Switch
				chosenCommand = new RightScaleSwitch();
				break;
			case 14:
				if (Robot.reliableMiddleSwitch.getSelected().equals("Yes")) {
					// Right Zane Scale / Switch
				} else {
					// Right Switch / Scale
				}
				break;

			}
		}
		SmartDashboard.putString("Auto: ", chosenCommand.getName());
		return chosenCommand;

	}
}
