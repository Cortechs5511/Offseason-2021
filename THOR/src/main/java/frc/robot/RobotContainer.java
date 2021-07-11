package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.Constants.MechanismConstants;
import frc.robot.commands.LightToggle;
import frc.robot.commands.SetClimberPower;
import frc.robot.commands.SetFeederPower;
import frc.robot.commands.SetIntakePower;
import frc.robot.commands.SetSpeed;
import frc.robot.commands.shooter.ShootAlign;
import frc.robot.commands.shooter.StopShooter;
import frc.robot.commands.TrajectoryFollower;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
    private final Drive m_drive = new Drive();
    private final Shooter m_shooter = new Shooter();
    private final Feeder m_feeder = new Feeder();
    private final Limelight m_limelight = new Limelight();
    private final Intake m_intake = new Intake();
    private final Climber m_climber = new Climber();

    private final ShootAlign m_shootAlignFast = new ShootAlign(0.5, -1, m_drive, m_shooter, m_feeder, m_limelight,
            m_intake);
    private final ShootAlign m_shootAlignSlow = new ShootAlign(0.1, 50, m_drive, m_shooter, m_feeder, m_limelight,
            m_intake);

    private final StopShooter m_stopShooter = new StopShooter(m_shooter, m_limelight, m_feeder, m_drive);
    private final LightToggle m_lightToggle = new LightToggle(m_limelight);

    Joystick leftStick = new Joystick(0);
    Joystick rightStick = new Joystick(1);
    XboxController controller = new XboxController(2);

    SendableChooser<autonMode> m_chooser = new SendableChooser<>();

    enum autonMode {
        TowerReverse, TowerForwards, Trench, Generator;
    }

    public RobotContainer() {
        configureButtonBindings();

        SetSpeed m_setSpeed = new SetSpeed(m_drive);
        SetFeederPower m_setFeederPower = new SetFeederPower(m_feeder);
        SetIntakePower m_setIntakePower = new SetIntakePower(m_intake);
        SetClimberPower m_setClimberPower = new SetClimberPower(m_climber);

        m_drive.setDefaultCommand(m_setSpeed);
        m_feeder.setDefaultCommand(m_setFeederPower);
        m_intake.setDefaultCommand(m_setIntakePower);
        m_climber.setDefaultCommand(m_setClimberPower);

        m_chooser.addOption("Tower Forwards", autonMode.TowerForwards);
        m_chooser.addOption("Tower Reverse", autonMode.TowerReverse);
        m_chooser.addOption("Trench", autonMode.Trench);
        m_chooser.addOption("Generator", autonMode.Generator);

        Shuffleboard.getTab("Auto").add(m_chooser);

        /**
         * Speeds up the loading of the paths
         * Enable for competition
         */

        // getAutonomousCommand(autonMode.TowerReverse);
        // getAutonomousCommand(autonMode.Trench);
        // getAutonomousCommand(autonMode.Generator);
    }

    private void configureButtonBindings() {
        new JoystickButton(leftStick, 2).whenPressed(m_drive::flip);
        new JoystickButton(rightStick, 2).whenPressed(() -> m_drive.setMaxOutput(0.5))
                .whenReleased(() -> m_drive.setMaxOutput(1.0));
        new JoystickButton(rightStick, 4).whenPressed(() -> m_drive.setMaxOutput(0.25))
                .whenReleased(() -> m_drive.setMaxOutput(1.0));

        new JoystickButton(controller, 4).whenPressed(m_shootAlignSlow, true);
        new JoystickButton(controller, 2).whenPressed(m_shootAlignFast, true);
        new JoystickButton(controller, 3).whenPressed((new StopShooter(m_shooter, m_limelight, m_feeder, m_drive))
                .andThen(() -> m_shooter.setOutput(MechanismConstants.kIdlePower)));
        new JoystickButton(controller, 8).whenPressed(m_stopShooter, false);
        new JoystickButton(controller, 7).whenPressed(m_lightToggle, true);
    }

    public Command getAutonomousCommand() {
        autonMode selection = m_chooser.getSelected();
        Command pathCommand;
        
        switch (selection) {
            case TowerReverse:
                pathCommand = TrajectoryFollower.getPath("output/TowerReverse.wpilib.json", m_drive, true);
                break;
            case TowerForwards:
                pathCommand = TrajectoryFollower.getPath("output/TowerForwards.wpilib.json", m_drive, true);
                break;
            case Trench:
                pathCommand = TrajectoryFollower.getPath("output/Trench.wpilib.json", m_drive, true);
                break;
            case Generator:
                pathCommand = TrajectoryFollower.getPath("output/Generator.wpilib.json", m_drive, true);
                break;
            default:
                pathCommand = new WaitCommand(1.0);
        }
        return m_shootAlignSlow.andThen(pathCommand);
    }
}
