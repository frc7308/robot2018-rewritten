package frc.team7308.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Compressor;

import frc.team7308.robot.subsystems.Drivetrain;
import frc.team7308.robot.subsystems.Lift;
import frc.team7308.robot.subsystems.Claw;
import frc.team7308.robot.DriverStation;
import frc.team7308.robot.LoopMaster;

public class Robot extends IterativeRobot {
    private Drivetrain drivetrain;
    private Lift lift;
    private Claw claw;
    private DriverStation driverStation;
    private Compressor compressor;
    private LoopMaster loopMaster;

    @Override
    public void robotInit() {
        this.driverStation = DriverStation.getInstance();

        this.drivetrain = new Drivetrain();
        this.lift = new Lift();
        this.claw = new Claw();

        this.loopMaster = new LoopMaster(drivetrain.controlLoop, lift.controlLoop, claw.controlLoop);
        loopMaster.start();

        this.compressor = new Compressor();
        compressor.start();
    }


    @Override
    public void disabledInit() {
        loopMaster.setGameState("Disabled");
    }

    @Override
    public void autonomousInit() {
        loopMaster.setGameState("Autonomous");
    }

    @Override
    public void teleopInit() {
         loopMaster.setGameState("Teleop");
    }

    @Override
    public void testInit() {
         loopMaster.setGameState("Test");
    }
}