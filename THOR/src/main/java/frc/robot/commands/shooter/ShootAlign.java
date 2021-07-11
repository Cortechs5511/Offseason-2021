package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.LightOn;
import frc.robot.subsystems.*;

public class ShootAlign extends SequentialCommandGroup {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    public ShootAlign(Double limeThreshold, int feedThreshold, Drive drive, Shooter shooter, Feeder feeder, Limelight limelight, Intake intake) {
        addCommands(
                new LightOn(limelight),
                new WaitCommand(0.5),
                new AccelAlign(limeThreshold, drive, limelight, shooter),
                new WaitCommand(0.5),
                new Cruise(feedThreshold, shooter, feeder, limelight, intake));
    }
}