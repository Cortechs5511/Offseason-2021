package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
    private final CANSparkMax climb0 = new CANSparkMax(60, MotorType.kBrushless);
    private final WPI_VictorSPX climb1 = new WPI_VictorSPX(61);

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
    }

    public void setClimberSpeed(double speed) {
        climb0.set(0);
        climb1.set(0);
        if (speed > 0) {
            climb0.set(speed * 0.3);
        } else {
            climb1.set(speed * 0.3);
        }
    }
}
