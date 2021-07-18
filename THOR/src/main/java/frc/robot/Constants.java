package frc.robot;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

public final class Constants {
    public static final class DriveConstants {
        public static final int kLeftMotor0Port = 10;
        public static final int kLeftMotor1Port = 11;
        public static final int kRightMotor0Port = 20;
        public static final int kRightMotor1Port = 21;

        public static final double kEncoderCPR = 42.0;
        public static final double kGearing = 11.127;
        public static final double kWheelDiameterMeters = 0.1524;
        public static final double kEncoderDistancePerPulse = 1 / (11.127 / 0.4788);
        public static final double kEncoderPulsesPerMeter = 1/ kEncoderDistancePerPulse;

        public static final double kTrackwidthMeters = 0.676;
        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
                kTrackwidthMeters);

        public static final double ksVolts = 0.524; 
        public static final double kvVoltSecondsPerMeter = 2.8; 
        public static final double kaVoltSecondsSquaredPerMeter = 0.0462;

        public static final double kPDriveVel = 0.208;

        public static final double kLeftP = 0.0294; 
        public static final double kLeftI = 0;
        public static final double kLeftD = 14.6;
        public static final double kLeftFF = 0;

        public static final double kRightP = 0.0292; 
        public static final double kRightI = 0;
        public static final double kRightD = 14.5;
        public static final double kRightFF = 0;

        public static final double kAngleP = 0.023;
        public static final double kAngleI = 0.037;
        public static final double kAngleD = 0.00115;
    }

    public static final class MechanismConstants {
        public static final int kWristPort = 30;
        public static final int kIntakePort = 31;

        public static final int kFeeder0Port = 40;
        public static final int kFeeder1Port = 41;
        public static final int kFeeder2Port = 42;
        public static final int kFeeder3Port = 43;

        public static final int kTopSensorPort = 0;
        public static final int kBottomSensorPort = 1;
        public static final int kGreenSensorPort = 2;
        public static final int kBlackSensorPort = 3;

        public static final int kShoot0Port = 50;
        public static final int kShoot1Port = 51;

        public static final double kShootP = 0.00025;
        public static final double kShootI = 0;
        public static final double kShootD = 0;
        public static final double kShootFF = 0.000199;
        public static final double kIdlePower = 0.25;

        //public static final int kClimb0Port = 60;
        //public static final int kClimb1Port = 61;

        public static final double kClimbTime = 20.0;
    }

    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = 2;
        public static final double kMaxAccelerationMetersPerSecondSquared = 2;

        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = 0.7;
    }
}
