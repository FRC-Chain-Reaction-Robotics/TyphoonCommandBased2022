package frc.robot.commands.drive;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class TTAProfiled extends ProfiledPIDCommand
{
    Drivetrain dt;
    double angleDegrees;
    
    public TTAProfiled(double angleDegrees, Drivetrain dt)
    {
        super(new ProfiledPIDController(Constants.TTAP_KP, 0, 0, 
                new TrapezoidProfile.Constraints(Constants.TTAP_MV, Constants.TTAP_MA)),
            dt::getAngle,
            new TrapezoidProfile.State(angleDegrees, 0),
            (output, setpoint) -> dt.arcadeDrive(0, output),
            dt);

        
            this.dt = dt;
            this.angleDegrees = angleDegrees;
        
            getController().setTolerance(Constants.TTA_TOLERANCE);
            getController().enableContinuousInput(-180, 180);
    }
    
    @Override
    public void initialize()
    {
        super.initialize();
        
        var goal = new TrapezoidProfile.State(angleDegrees + dt.getAngle(), 0);
        this.m_goal = () -> goal;
    }

    @Override
    public boolean isFinished()
    {
        return getController().atGoal();
    }
}
