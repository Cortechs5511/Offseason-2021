package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
    public CANSparkMax left0 = createSparkMAX(10, false);
    public CANSparkMax right0 = createSparkMAX(20, false);

    public DifferentialDrive drive = new DifferentialDrive(left0, right0);
    public Joystick leftStick = new Joystick(0);
    public Joystick rightStick = new Joystick(1);

    public Lighting lights = new Lighting();

    @Override
    public void robotInit() {
        drive.setDeadband(0.2);
    }

    @Override
    public void robotPeriodic() {
        SmartDashboard.putNumber("DT/Left Current", left0.getOutputCurrent());
        SmartDashboard.putNumber("DT/Right Current", right0.getOutputCurrent());
    }

    @Override
    public void autonomousInit() {
    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {
        lights.off();
    }

    @Override
    public void teleopPeriodic() {
        double power = leftStick.getY(), steer = -rightStick.getX();
        drive.arcadeDrive(power * 0.6, steer, true);
        lights.drive(power, steer);
    }

    @Override
    public void disabledInit() {
        left0.set(0);
        right0.set(0);
        lights.off();
    }

    @Override
    public void disabledPeriodic() {
    }

    private CANSparkMax createSparkMAX(int id, boolean invert) {
        CANSparkMax controller = new CANSparkMax(id, MotorType.kBrushless);

        controller.clearFaults();
        controller.restoreFactoryDefaults();
        controller.setIdleMode(IdleMode.kBrake);
        controller.setInverted(invert);
        controller.enableVoltageCompensation(7);
        controller.setOpenLoopRampRate(0.5);
        controller.setSecondaryCurrentLimit(60);
        controller.setSmartCurrentLimit(40, 20, 1000);

        return controller;
    }
}
