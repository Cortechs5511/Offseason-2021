package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.OI;
import frc.robot.subsystems.Climber;

public class SetClimberPower extends CommandBase {
    private final Climber m_climber;
    private final OI m_oi = OI.getInstance();

    public SetClimberPower(Climber climber) {
        m_climber = climber;
        addRequirements(climber);
    }

    @Override
    public void initialize() {
        m_climber.setClimberSpeed(0);
    }

    @Override
    public void execute() {
        m_climber.setClimberSpeed(m_oi.getClimber());
    }

    @Override
    public void end(boolean interrupted) {
        m_climber.setClimberSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
