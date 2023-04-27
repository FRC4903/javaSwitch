package frc.robot.autos;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.auto.PIDConstants;
import com.pathplanner.lib.auto.SwerveAutoBuilder;

import frc.robot.Constants;
import frc.robot.subsystems.Swerve;

import java.util.HashMap;

public class exampleAuto extends SequentialCommandGroup {
  public exampleAuto(Swerve s_Swerve) {
    // This will load the file "Example Path.path" and generate it with a max velocity of 4 m/s and a max acceleration of 3 m/s^2
    PathPlannerTrajectory examplePath = PathPlanner.loadPath("Taxi Test", new PathConstraints(4, 3));

    Constants.AutoConstants.autoEventMap.put("Score", new PrintCommand("Scoreeee"));
    Constants.AutoConstants.autoEventMap.put("Pickup", new PrintCommand("Pickuppp"));

    // Create the AutoBuilder. This only needs to be created once when robot code starts, not every time you want to create an auto command. A good place to put this is in RobotContainer along with your subsystems.
    SwerveAutoBuilder autoBuilder = new SwerveAutoBuilder(
        s_Swerve::getPose, // Pose2d supplier
        s_Swerve::resetOdometry, // Pose2d consumer, used to reset odometry at the beginning of auto
        Constants.Swerve.swerveKinematics, // SwerveDriveKinematics
        new PIDConstants(Constants.AutoConstants.kPXController, 0, 0), // PID constants to correct for translation error (used to create the X and Y PID controllers)
        new PIDConstants(Constants.AutoConstants.kPThetaController, 0, 0), // PID constants to correct for rotation error (used to create the rotation controller)
        s_Swerve::setModuleStates, // Module states consumer used to output to the drive subsystem
        Constants.AutoConstants.autoEventMap,
        true, // Should the path be automatically mirrored depending on alliance color. Optional, defaults to true
        s_Swerve // The drive subsystem. Used to properly set the requirements of path following commands
    );

    Command fullAuto = autoBuilder.fullAuto(examplePath);
    addCommands(fullAuto);
  }
}