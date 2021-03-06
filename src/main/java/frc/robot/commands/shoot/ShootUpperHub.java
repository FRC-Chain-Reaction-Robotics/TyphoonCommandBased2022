package frc.robot.commands.shoot;
//hehehehehe
// hahhahaha very funny zoheb :|
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.drive.*;
import frc.robot.subsystems.*;

public class ShootUpperHub extends SequentialCommandGroup { 
    
    private Shooter shooter;
    private Feeder feeder;
    private Hood hood;

    public ShootUpperHub (Shooter shooter, Hood hood, Feeder feeder) {
        
        addRequirements(shooter, hood /*feeder,*/);

        addCommands(
			// new StartShooterCommand(shooter, () -> shooter.calcRPM(shooter.calcDistanceMeters(ll.getTy())))
			// new StartShooterCommand(shooter, () -> 3825) // upper hub
			// new StartShooterCommand(shooter, () -> 3000) // far away lower hub
			// new StartShooterCommand(shooter, () -> ds1490) // close up upper hub with New Hood
			// new StartShooterCommand(shooter, () -> 2950) // NO UPPER IDLER
			// new StartShooterCommand(shooter, () -> 2000) // works with upper idler at a distance
			new StartShooterCommand(shooter, () -> 2500) // testing
				// .alongWith(new AimCommand(dt, ll::getTx)),	//	According to ReCalc, the shooter needs 1.17 sec to wind up
            // new RunComm  and(feeder::on, feeder).withTimeout(0.1),
			// new StartShooterCommand(shooter, () -> shooter.calcRPM(shooter.calcDistanceMeters(ll.getTy()))).withTimeout(2),
            // new RunCommand(feeder::on, feeder).withTimeout(0.1),
            // new InstantCommand(feeder::off, feeder)
        );

        this.shooter = shooter;
        this.feeder = feeder;
        this.hood = hood;
    }

    @Override
    public void end(boolean interrupted)
    {
        super.end(interrupted);

        shooter.stop();
        // feeder.off();
        hood.setHoodAngularPower(0);
    }
}
