package frc.robot.commands.shooter;

import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AccelStandard extends CommandBase {
	@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
	private final Shooter m_shooter;
	private final Limelight m_limelight;

	private double input;
	private double calculatedSpeed;
	private int count = 0;

	public AccelStandard(Shooter shooter, Limelight limelight) {
		m_shooter = shooter;
		m_limelight = limelight;
		addRequirements(shooter);
		addRequirements(limelight);
	}

	@Override
	public void initialize() {
		m_shooter.setRampRate(0.75);
		calculatedSpeed = m_limelight.calculateRPM();
		m_shooter.setPIDReference(calculatedSpeed);

	}

	@Override
	public void execute() {;
		double currentSpeed = m_shooter.getSpeed.get();

		if (Math.abs(calculatedSpeed - currentSpeed) < 200) {
			count++;
		} else {
			count = 0;
		}
	}

	@Override
	public void end(boolean interrupted) {
		m_shooter.setPIDReference(calculatedSpeed);

		m_shooter.setRampRate(0.01);
		count = 0;
	}

	@Override
	public boolean isFinished() {
		return (count > 25);
	}
}
