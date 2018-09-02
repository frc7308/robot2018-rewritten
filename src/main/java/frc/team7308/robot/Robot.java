package frc.team7308.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Compressor;

import frc.team7308.robot.subsystems.Drivetrain;
import frc.team7308.robot.subsystems.Lift;
import frc.team7308.robot.subsystems.Claw;
import frc.team7308.robot.DriverStation;

public class Robot extends IterativeRobot {
    private Drivetrain drivetrain;
    private Lift lift;
    private Claw claw;
    private DriverStation driverStation;
    private Compressor compressor;

    @Override
    public void robotInit() {
        this.driverStation = DriverStation.getInstance();
        this.drivetrain = new Drivetrain();
        this.lift = new Lift();
        this.claw = new Claw();
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