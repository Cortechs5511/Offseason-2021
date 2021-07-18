package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.LightOn;
import frc.robot.subsystems.*;

public class ShootStandard extends SequentialCommandGroup {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    public ShootStandard(int feedThreshold, Shooter shooter, Feeder feeder, Intake intake, Limelight limelight) {
        addCommands(
                new AccelStandard(shooter, limelight),
                new WaitCommand(0.2),
                new CruiseStandard(feedThreshold, shooter, feeder, intake));
    }
}