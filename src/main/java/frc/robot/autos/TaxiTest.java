package frc.robot.autos;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import frc.robot.Constants;
import frc.robot.subsystems.Swerve;


public class TaxiTest extends SequentialCommandGroup {
  public TaxiTest(Swerve s_Swerve, SwerveAutoBuilder autoBuilder) {
    // This will load the file "Example Path.path" and generate it with a max velocity of 4 m/s and a max acceleration of 3 m/s^2
    PathPlannerTrajectory examplePath = PathPlanner.loadPath("Taxi Test", new PathConstraints(4.41, 3));

    Constants.AutoConstants.autoEventMap.put("Score", new PrintCommand("Scoreeee"));
    Constants.AutoConstants.autoEventMap.put("Pickup", new PrintCommand("Pickuppp"));

    Command fullAuto = autoBuilder.fullAuto(examplePath);
    addCommands(fullAuto);
  }
}