package org.usfirst.frc.team1024.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class CompetitionAuto {
	   private static CompetitionAuto singleton = new CompetitionAuto( );
	   private ArrayList stuff;

	   private CompetitionAuto() { }

	   /* Static 'instance' method */
	   public static CompetitionAuto getInstance( ) {
	      return singleton;
	   }
	   
	   public SendableChooser<String> populateChooser() {
		   return new SendableChooser<>();
	   }
}
