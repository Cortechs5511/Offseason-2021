// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drive;
import frc.robot.OI;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveDrive extends CommandBase {
	@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
	private final OI m_oi = OI.getInstance();
	private final Drive m_drive;

	public SwerveDrive(Drive drive) {
		m_drive = drive;
		addRequirements(drive);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
        double xSpeed = m_oi.getLeftX.get();
		double ySpeed = m_oi.getLeftY.get();
        double rotation = m_oi.getRightX.get();
        
        var swerveModuleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(new ChassisSpeeds(xSpeed, ySpeed, rotation));
        m_drive.setModuleStates(swerveModuleStates);
	}

	@Override
	public void end(boolean interrupted) {
        var swerveModuleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(new ChassisSpeeds(0, 0, 0));
        m_drive.setModuleStates(swerveModuleStates);
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}
