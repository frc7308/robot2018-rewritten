package frc.team7308.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.DriverStation;

import frc.team7308.robot.subsystems.Drivetrain;
import frc.team7308.robot.subsystems.Lift;
import frc.team7308.robot.subsystems.Claw;
import frc.team7308.robot.Input;
import frc.team7308.robot.LoopMaster;

import frc.team7308.robot.auto.LeftAutoLeft;
import frc.team7308.robot.auto.RightAutoRight;
import frc.team7308.robot.auto.StraightAuto;

public class Robot extends IterativeRobot {
    public static Drivetrain drivetrain;
    public static Lift lift;
    public static Claw claw;
    private Input driverStation;
    private Compressor compressor;
    private LoopMaster loopMaster;

    private SendableChooser autoChooser;
    private CommandGroup LeftAutoLeft;
    private CommandGroup RightAutoRight;
    private CommandGroup StraightAuto;

    @Override
    public void robotInit() {
        SmartDashboard.putNumber("lol", 2);

        this.driverStation = Input.getInstance();

        this.drivetrain = new Drivetrain();
        this.lift = new Lift();
        this.claw = new Claw();

        this.autoChooser = new SendableChooser();
        this.autoChooser.addDefault("Straight", "Straight");
        this.autoChooser.addObject("Left", "Left");
        this.autoChooser.addObject("Right","Right");
        SmartDashboard.putData("Autonomous", this.autoChooser);

        this.LeftAutoLeft = new LeftAutoLeft();
        this.RightAutoRight = new RightAutoRight();
        this.StraightAuto = new StraightAuto();

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

        String selectedAuto = (String) autoChooser.getSelected();
        String gameData = DriverStation.getInstance().getGameSpecificMessage();

        if (selectedAuto == "Straight") {
            StraightAuto.start();
        }
        else if (selectedAuto == "Left") {
            if(gameData.length() > 0) {
                if(gameData.charAt(0) == 'L') {
                    LeftAutoLeft.start();
                } else {
                    StraightAuto.start();
                }
            }
        }
        else if (selectedAuto == "Right") {
            if(gameData.length() > 0) {
                if(gameData.charAt(0) == 'R') {
                    RightAutoRight.start();
                } else {
                    StraightAuto.start();
                }
            }
        }
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
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