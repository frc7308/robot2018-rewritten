package frc.team7308.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;

import frc.team7308.robot.subsystems.Drivetrain;
import frc.team7308.robot.subsystems.Lift;
import frc.team7308.robot.subsystems.Claw;
import frc.team7308.robot.DriverStation;
import frc.team7308.robot.LoopMaster;

import frc.team7308.robot.auto.LeftAutoLeft;

public class Robot extends IterativeRobot {
    public static Drivetrain drivetrain;
    public static Lift lift;
    public static Claw claw;
    private DriverStation driverStation;
    private Compressor compressor;
    private LoopMaster loopMaster;

    private CommandGroup LeftAutoLeft;

    @Override
    public void robotInit() {
        this.driverStation = DriverStation.getInstance();

        this.drivetrain = new Drivetrain();
        this.lift = new Lift();
        this.claw = new Claw();

        this.LeftAutoLeft = new LeftAutoLeft();

        this.loopMaster = new LoopMaster();
        loopMaster.addLoop(drivetrain.controlLoop);
        loopMaster.addLoop(lift.controlLoop);
        loopMaster.addLoop(claw.controlLoop);
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
        System.out.println("ayaya");
        LeftAutoLeft.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        loopMaster.setGameState("Teleop");
        LeftAutoLeft.cancel();
    }

    @Override
    public void testInit() {
         loopMaster.setGameState("Test");
    }
}