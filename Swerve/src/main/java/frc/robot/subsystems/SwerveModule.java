package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import frc.robot.Constants.DriveConstants;


public class SwerveModule {
    private CANSparkMax spin;
    private CANSparkMax wheel;
    private double distance;
    private DutyCycleEncoder spinEnc;
    private Encoder wheelEnc;
    private PIDController spinControl;
    private PIDController wheelControl;

	public SwerveModule(int spinPort, int wheelPort, double x, double y, int spinEncPort, int wheelEncPort) {
        spin = createSparkMAX(spinPort, false, 4);
        wheel = createSparkMAX(wheelPort, false, 12);
        distance = Math.sqrt(x*x + y*y);
        spinEnc = new DutyCycleEncoder(spinEncPort);
        wheelEnc = new Encoder(wheelEncPort, wheelEncPort + 1);
        spinControl = new PIDController(DriveConstants.kSpinP, DriveConstants.kSpinI, DriveConstants.kSpinD);
        wheelControl = new PIDController(DriveConstants.kWheelP, DriveConstants.kWheelI, DriveConstants.kWheelD);
    }
    
    private CANSparkMax createSparkMAX(int id, boolean invert, double volts) {
        CANSparkMax controller = new CANSparkMax(id, MotorType.kBrushless);

        controller.clearFaults();
        controller.restoreFactoryDefaults();
        controller.setIdleMode(IdleMode.kBrake);
        controller.setInverted(invert);
        controller.enableVoltageCompensation(volts);
        controller.setOpenLoopRampRate(0.1);
        controller.setClosedLoopRampRate(0.1);
        controller.setSmartCurrentLimit(70, 70, 9000);

        return controller;
    }

    public double returnWheelEnc() {
        return wheelEnc.getDistance();
    }

    /**
     * @return wheel speed in meters per second
     */
    public double getWheelVelocity() {
        return wheelEnc.getRate();
    }

    public Rotation2d getAngle() {
        return Rotation2d.fromDegrees((spinEnc.get() * 360) - 180);
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(getWheelVelocity(), getAngle());
    }

    public void setState(SwerveModuleState moduleState) {
        //do stuff here
        //remember to do the optimization
        var optimized = SwerveModuleState.optimize(moduleState, getAngle());
        spinControl.setSetpoint(optimized.angle.getDegrees());
        wheelControl.setSetpoint(moduleState.speedMetersPerSecond);
    }

    public void periodic() {
        double spinPower = spinControl.calculate(getAngle().getDegrees());
        double wheelPower = wheelControl.calculate(getWheelVelocity());
        wheelPower += DriveConstants.kWheelFF * wheelControl.getSetpoint();
        spin.set(spinPower);
        wheel.set(wheelPower);
    }
}
