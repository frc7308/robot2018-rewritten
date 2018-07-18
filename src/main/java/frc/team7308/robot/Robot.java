package frc.team7308.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Compressor;

import frc.team7308.robot.Drivetrain;
import frc.team7308.robot.DriverStation;

public class Robot extends IterativeRobot {
    private Drivetrain drivetrain;
    private DriverStation driverStation;
    private Compressor compressor;

    @Override
    public void robotInit() {
        this.drivetrain = new Drivetrain(0, 1, 2, 3);
        this.driverStation = new DriverStation(0, 1);
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