package frc.robot.commands;

import frc.robot.subsystems.Feeder;
import frc.robot.OI;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class SetFeederPower extends CommandBase {
	@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
	private final Feeder m_feeder;
	private final OI m_oi = OI.getInstance();

	public SetFeederPower(Feeder feeder) {
		m_feeder = feeder;
		addRequirements(feeder);
	}

	@Override
	public void initialize() {
	}

	@Override
	public void execute() {
		boolean bottom = !m_feeder.getBottomSensor.get();
		boolean top = !m_feeder.getTopSensor.get();
		boolean green = !m_feeder.getGreenSensor.get();
		boolean black = !m_feeder.getBlackSensor.get();

		boolean intaking = m_oi.getIntake.get();

		double blackSpeed;
		double greenSpeed;
		if (intaking) {
			blackSpeed = 0.6;
			greenSpeed = 0.6;
		} else {
			blackSpeed = 0;
			greenSpeed = 0;
		}

		double polySpeed;
		if (top && bottom && black && green) { 
			polySpeed = 0; 
			blackSpeed = 0; 
			greenSpeed = 0; 
		} else if (top && bottom && black) { 
			polySpeed = 0; 
			blackSpeed = 0; 
		} else if (top && bottom && green) { 
			polySpeed = 0; 
		} else if (top && bottom) { 
			polySpeed = 0; 
		} else if (top && black && green) { // 1011
			polySpeed = -0.4; 
			blackSpeed = 0; 
			greenSpeed = 0; 
		} else if (top && !black && green) { 
			polySpeed = -0.4; 
		} else if (top && black) { 
			polySpeed = -0.4; 
			blackSpeed = 0; 
		} else if (top) { 
			polySpeed = -0.4; 
		} else if (bottom) { 
			polySpeed = 0.4; 
		} else { 
			polySpeed = 0;
		}

		if (m_oi.getIntakeBackFeed.get()) {
			blackSpeed = -0.9;
			greenSpeed = -0.6;
		}
		if (m_oi.getBeltBackFeed.get()) {
			polySpeed = -0.35;
		}
        
		m_feeder.setFeederSpeed(polySpeed); 
		m_feeder.setFeeder2Speed(blackSpeed); 
		m_feeder.setFeeder3Speed(greenSpeed); 
	}

	@Override
	public void end(boolean interrupted) {
		m_feeder.setFeederSpeed(0);
		m_feeder.setFeeder2Speed(0);
		m_feeder.setFeeder3Speed(0);
	}

	@Override
	public boolean isFinished() {
		return false;
	}
}