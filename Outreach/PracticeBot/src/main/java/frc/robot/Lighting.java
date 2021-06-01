package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpiutil.math.MathUtil;

public class Lighting {
    private AddressableLED LED = new AddressableLED(0);
    private AddressableLEDBuffer LEDBuffer = new AddressableLEDBuffer(16);

    private int length = LEDBuffer.getLength();

    public Lighting() {
        LED.setLength(length);

        LED.setData(LEDBuffer);
        LED.start();
    }

    void drive(double power, double steer) {
        double magnitude = MathUtil.clamp(((Math.abs(power) * 1.3) - 0.3) + 0.25, -1, 1);
        SmartDashboard.putNumber("Lighting/Magnitude", magnitude);

        for (int i = 0; i < length; i++)
            LEDBuffer.setRGB(i, 0, 0, 0);

        for (int i = 0; i < (length * magnitude); i++) {
            LEDBuffer.setRGB(i, 28, 70, 142);
        }

        LED.setData(LEDBuffer);
    }

    void off() {
        for (var i = 0; i < length; i++) {
            LEDBuffer.setRGB(i, 28, 70, 142);
        }
        LED.setData(LEDBuffer);
    }
}