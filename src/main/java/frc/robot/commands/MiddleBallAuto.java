package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.drive.*;
import frc.robot.commands.shoot.*;
import frc.robot.subsystems.*;

public class MiddleBallAuto extends SequentialCommandGroup
{
	public MiddleBallAuto(Shooter shooter, Hood hood, Feeder feeder, Intake intake, Drivetrain dt)
	{
		addCommands
		(
			// new DTDProfiled(1.528, dt),
			new ShootUpperHub(shooter, hood, feeder)
		//	new RunCommand(intake::on, intake).withTimeout(2),
		//	new InstantCommand(intake::off, intake),
            // new DTDProfiled(-1.528, dt),
			
		//	new RunCommand(feeder::on, feeder).withTimeout(2)
		);
	}
}
