// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Drive;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
    private final Drive m_drive = new Drive();

    Joystick leftStick = new Joystick(0);
    Joystick rightStick = new Joystick(1);
    XboxController controller = new XboxController(2);

    public RobotContainer() {
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        new JoystickButton(leftStick, 2).whenPressed(m_drive::flip);
        new JoystickButton(rightStick, 2).whenPressed(() -> m_drive.setMaxOutput(0.5)).whenReleased(() -> m_drive.setMaxOutput(1.0));
        new JoystickButton(rightStick, 4).whenPressed(() -> m_drive.setMaxOutput(0.25)).whenReleased(() -> m_drive.setMaxOutput(1.0));
    }

    public Command getAutonomousCommand() {
        return null;
    }
}
