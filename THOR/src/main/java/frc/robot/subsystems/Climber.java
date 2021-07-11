package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.OI;
import frc.robot.Constants.MechanismConstants;

public class Climber extends SubsystemBase {
    private final CANSparkMax climb0 = new CANSparkMax(60, MotorType.kBrushless);
    private final WPI_VictorSPX climb1 = new WPI_VictorSPX(61);

    private long onTime = 0;
    private OI m_oi = OI.getInstance();

    public Climber() {
        climb0.clearFaults();
        climb0.restoreFactoryDefaults();
        climb0.setIdleMode(IdleMode.kBrake);
        climb0.setInverted(false);
        climb0.disableVoltageCompensation();
        climb0.setOpenLoopRampRate(0.5);
        climb0.setClosedLoopRampRate(0.5);
        climb0.setSmartCurrentLimit(80, 80, 9000);
        climb0.setSecondaryCurrentLimit(140);
    }

    @Override
    public void periodic() {
        if (climb0.get() != 0) {
            onTime++;
        } else {
            onTime = 0;
        }

        if (onTime > MechanismConstants.kClimbTime * 50) {
            m_oi.setRumble((onTime / 20) % 2);
        } else {
            m_oi.setRumble(0);
        }
    }

    public void setClimberSpeed(double speed) {
        climb0.set(0);
        climb1.set(0);
        if (speed > 0) {
            climb0.set(speed * 0.3);
        } else {
            climb0.set(speed * 0.3);
        }
    }
}
