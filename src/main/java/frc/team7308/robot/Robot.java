package frc.team7308.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Compressor;

import frc.team7308.robot.subsystems.Drivetrain;
import frc.team7308.robot.subsystems.Lift;
import frc.team7308.robot.DriverStation;

public class Robot extends IterativeRobot {
    private Drivetrain drivetrain;
    private Lift lift;
    private DriverStation driverStation;
    private Compressor compressor;

    @Override
    public void robotInit() {
        this.drivetrain = new Drivetrain();
        this.driverStation = new DriverStation();
        this.lift = new Lift();
        this.compressor = new Compressor();
        compressor.start();
    }


    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        
    }


    @Override
    public void autonomousInit() {
        
    }

    @Override
    public void autonomousPeriodic() {
        
    }


    @Override
    public void teleopInit() {
        
    }

    @Override
    public void teleopPeriodic() {

    }


    @Override
    public void testInit() {
        
    }

    @Override
    public void testPeriodic() {
        
    }
}