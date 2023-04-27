package frc.robot.autos;

import com.pathplanner.lib.auto.SwerveAutoBuilder;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.subsystems.Swerve;

public class AutoSelector {

    private SendableChooser<SequentialCommandGroup> autonomusSelector = new SendableChooser<SequentialCommandGroup>();

    public AutoSelector(Swerve s_Swerve, SwerveAutoBuilder autoBuilder) {
        autonomusSelector.setDefaultOption(
            "Do Nothing", 
            new SequentialCommandGroup()
        );

        autonomusSelector.addOption(
            "Taxi Test",
            new TaxiTest(s_Swerve, autoBuilder)
        );

        SmartDashboard.putData("Autonomus Selector", autonomusSelector);
    }

    public SequentialCommandGroup get() {
        return autonomusSelector.getSelected();
    }
}
