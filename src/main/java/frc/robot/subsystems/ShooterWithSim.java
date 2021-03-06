//heheheheheheheheheh
package frc.robot.subsystems;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.simulation.BatterySim;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.RoboRioSim;

public class ShooterWithSim extends Shooter
{
    private FlywheelSim mainFlyWheelSim = new FlywheelSim(
		DCMotor.getFalcon500(2),
		0.75,
		0.5 * Units.lbsToKilograms(0.74 * 2) * Math.pow(Units.inchesToMeters(4/2), 2)
	);

	private FlywheelSim hoodSim = new FlywheelSim(DCMotor.getNeo550(1), 1, 0.000614543272);                                                       


	@Override
	public void simulationPeriodic()
	{
		mainFlyWheelSim.setInputVoltage(masterShoot.getSimCollection().getMotorOutputLeadVoltage());
		hoodSim.setInputVoltage(upperFlywheelMotor.get());
		hoodSim.update(0.02);
        mainFlyWheelSim.update(0.02);
		masterShoot.getSimCollection().setIntegratedSensorVelocity(
			(int) CRRFalcon500.RPMtoTalonUnits(mainFlyWheelSim.getAngularVelocityRPM()));
        
		RoboRioSim.setVInVoltage(
			BatterySim.calculateDefaultBatteryLoadedVoltage(mainFlyWheelSim.getCurrentDrawAmps()));
	}
}