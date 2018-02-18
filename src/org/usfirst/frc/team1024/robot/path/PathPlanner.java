package org.usfirst.frc.team1024.robot.path;


public class PathPlanner {

	public static void log(String msg) {
		System.out.println(msg);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double[][] scaleToSwitchCubePath = new double[][] {
			{0, 0},
			{0, 3},
			{3, 6},
			{3, 9}
    	};
		
		double totalTime = 3; //seconds
		double timeStep = 0.02; //period of control loop on Rio, seconds
		double robotTrackWidth = 2; //distance between left and right wheels, feet

		FalconPathPlanner pathPlanner = new FalconPathPlanner(scaleToSwitchCubePath);
		pathPlanner.calculate(totalTime, timeStep, robotTrackWidth);
		
		double[][] leftVelocity = pathPlanner.smoothLeftVelocity;
		double[][] rightVelocity = pathPlanner.smoothRightVelocity;
		
		int arraySize = leftVelocity.length;
		log("Num velocities : " + arraySize);
		
		for(int i = 0; i < arraySize; i++) {
			log("time : " + leftVelocity[i][0] + ", left velocity : " + leftVelocity[i][1]);
			log("            right velocity : " + rightVelocity[i][1]);
		}
	}

}
