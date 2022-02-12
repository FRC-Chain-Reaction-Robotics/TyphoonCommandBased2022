package frc.robot.commands.shoot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.Shooter;

public class SetHoodAngle extends PIDCommand // uses PID because angle is a specified position essentially
{
    Shooter shooter;
    Limelight ll;

    public SetHoodAngle(Shooter shooter, Limelight ll) {
        super(new PIDController(Constants.HOOD_KP, 0, 0),
                shooter::getHoodAngle,
                ll::getTy, 
                shooter::setHoodAngularPower,
                shooter);
        
        this.shooter = shooter;
        this.ll = ll;

        getController().setTolerance(3);
    }

    @Override
    public boolean isFinished()
    {
        return getController().atSetpoint();    //  This command will terminate once the desired angle has been reached.
    
    }
    
    @Override
    public void end(boolean interrupted) 
    {
        shooter.setHoodAngularPower(0);
    }
}
