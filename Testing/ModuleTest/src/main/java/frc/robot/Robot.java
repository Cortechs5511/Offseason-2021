// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    XboxController controller = new XboxController(1);

    private CANSparkMax drive = createSparkMAX(10, false);
    private CANSparkMax steer = createSparkMAX(11, false);

    private DutyCycleEncoder angleEncoder = new DutyCycleEncoder(0);
    private Encoder wheelEncoder = new Encoder(1, 2);

    @Override
    public void robotInit() {
        wheelEncoder.setDistancePerPulse((4 * Math.PI) / 2048);
    }

    @Override
    public void robotPeriodic() {
        SmartDashboard.putNumber("Wheel Angle (deg)", getWheelAngle());
        SmartDashboard.putNumber("Wheel Velocity (m/s)", getWheelVelocity());
        SmartDashboard.putNumber("Wheel Distance (m)", getWheelDistance());
    }

    /**
     * Returns the direction the wheel is pointing in degrees.
     */
    private double getWheelAngle() {
        double angle = angleEncoder.get() * 360;
        return angle;
    }

    /**
     * Returns the wheel speed in meters per second.
     */
    private double getWheelVelocity() {
        double velocity = wheelEncoder.getRate();
        return velocity;
    }

    /**
     * Returns how far the wheel has traveled in meters.
     */
    private double getWheelDistance() {
        return wheelEncoder.getDistance();
    }

    @Override
    public void autonomousInit() {
    }

    /** This function is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {
    }

    /** This function is called once when teleop is enabled. */
    @Override
    public void teleopInit() {
    }

    /** This function is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {
        double driveAxis = controller.getRawAxis(2);
        double steerAxis = controller.getRawAxis(3);

        if (Math.abs(driveAxis) >= 0.25) {
            drive.set(driveAxis * 0.3);
        } else {
            drive.set(0);
        }

        if (Math.abs(steerAxis) >= 0.25) {
            steer.set(steerAxis * 0.3);
        } else {
            steer.set(0);
        }
    }

    private CANSparkMax createSparkMAX(int id, boolean invert) {
        CANSparkMax controller = new CANSparkMax(id, MotorType.kBrushless);

        controller.clearFaults();
        controller.restoreFactoryDefaults();
        controller.setIdleMode(IdleMode.kCoast);
        controller.setInverted(invert);
        controller.enableVoltageCompensation(7);
        controller.setOpenLoopRampRate(1.5);
        controller.setClosedLoopRampRate(1.5);
        controller.setSmartCurrentLimit(20, 10, 3000);

        return controller;
    }

    /** This function is called once when the robot is disabled. */
    @Override
    public void disabledInit() {
    }

    /** This function is called periodically when disabled. */
    @Override
    public void disabledPeriodic() {
    }

    /** This function is called once when test mode is enabled. */
    @Override
    public void testInit() {
    }

    /** This function is called periodically during test mode. */
    @Override
    public void testPeriodic() {
    }
}
