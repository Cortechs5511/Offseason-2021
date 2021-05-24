// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

import java.util.function.Supplier;

public class Drive extends SubsystemBase {
    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
    /*
    private final CANSparkMax left0 = createSparkMAX(DriveConstants.kLeftMotor0Port, false);
    private final CANSparkMax right0 = createSparkMAX(DriveConstants.kRightMotor0Port, true);

    private final CANEncoder leftEnc = left0.getEncoder();
    private final CANEncoder rightEnc = right0.getEncoder();
    */


    //10-13 will be spin sparks
    //20-23 will be the wheel sparks
    //0s are front left, 1s are front right, 2s are back right, 3s are back left

    public Boolean invert = false;
    public double maxOutput = 1.0;
    //public PIDController anglePID = new PIDController(DriveConstants.kAngleP, DriveConstants.kAngleI, DriveConstants.kAngleD);

    /*public Supplier<Double> getLeftVelocity = leftEnc::getVelocity;
    public Supplier<Double> getRightVelocity = rightEnc::getVelocity;
    */
    private SwerveModule module0;
    private SwerveModule module1;
    private SwerveModule module2;
    private SwerveModule module3;

    public Drive() {
        double x = DriveConstants.kDTWidth / 2;
        double y = DriveConstants.kDTLength / 2;
        module0 = new SwerveModule(DriveConstants.kSpin0Port, DriveConstants.kWheel0Port, -x, y, 0, 0);
        module1 = new SwerveModule(DriveConstants.kSpin1Port, DriveConstants.kWheel1Port, x, y, 1, 2);
        module2 = new SwerveModule(DriveConstants.kSpin2Port, DriveConstants.kWheel2Port, x, -y, 2, 4);
        module3 = new SwerveModule(DriveConstants.kSpin3Port, DriveConstants.kWheel3Port, -x, -y, 3, 6);

        /*CANSparkMax left1 = createSparkMAX(DriveConstants.kLeftMotor1Port, false);
        CANSparkMax right1 = createSparkMAX(DriveConstants.kRightMotor1Port, true);*/

        /*left1.follow(left0);
        right1.follow(right0);*/

        //anglePID.disableContinuousInput();
        //anglePID.setIntegratorRange(-0.1, 0.1);
    }



    

    /*public void set(double left, double right) {
        left0.set(left);
        right0.set(right);
    }*/

    public void flip() {
        invert = !invert;
    }

    public void setMaxOutput(double maxOutput) {
        this.maxOutput = maxOutput;
    }

    @Override
    public void periodic() {
        module0.periodic();
        module1.periodic();
        module2.periodic();
        module3.periodic();

        module0.setState(new SwerveModuleState(3, Rotation2d.fromDegrees(45)));
    }

}