package frc.team7308.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.cscore.UsbCamera;

import frc.team7308.robot.subsystems.Drivetrain;
import frc.team7308.robot.subsystems.Lift;
import frc.team7308.robot.subsystems.Claw;
import frc.team7308.robot.Input;
import frc.team7308.robot.LoopMaster;

import frc.team7308.robot.auto.LeftAutoLeft;
import frc.team7308.robot.auto.RightAutoRight;
import frc.team7308.robot.auto.StraightAuto;
import frc.team7308.robot.auto.StraightAutoThrow;
import frc.team7308.robot.auto.StraightAutoNoZero;

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
    private CommandGroup StraightAutoThrow;
    private CommandGroup StraightAutoNoZero;

    @Override
    public void robotInit() {
        this.driverStation = Input.getInstance();

        this.drivetrain = new Drivetrain();
        this.lift = new Lift();
        this.claw = new Claw();

        this.autoChooser = new SendableChooser();
        this.autoChooser.addDefault("Straight", "Straight");
        this.autoChooser.addObject("Left", "Left");
        this.autoChooser.addObject("Right", "Right");
        this.autoChooser.addObject("Straight Left", "StraightLeft");
        this.autoChooser.addObject("Straight Right", "StraightRight");
        this.autoChooser.addObject("Straight No Zero", "StraightNoZero");
        SmartDashboard.putData("Autonomous", this.autoChooser);

        this.LeftAutoLeft = new LeftAutoLeft();
        this.RightAutoRight = new RightAutoRight();
        this.StraightAuto = new StraightAuto();
        this.StraightAutoThrow = new StraightAutoThrow();
        this.StraightAutoNoZero = new StraightAutoNoZero();

        this.loopMaster = new LoopMaster();
        loopMaster.addLoop(drivetrain.controlLoop);
        loopMaster.addLoop(lift.controlLoop);
        loopMaster.addLoop(claw.controlLoop);
        loopMaster.start();

        this.compressor = new Compressor();
        compressor.start();

        UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
        camera.setResolution(640, 480);
        camera.setFPS(10);

        System.out.println("\n\n--- Robot started ---\n\n");
    }


    @Override
    public void disabledInit() {
        loopMaster.setGameState("Disabled");
        SmartDashboard.putString("Mode", "Disabled");
    }

    @Override
    public void autonomousInit() {
        loopMaster.setGameState("Autonomous");
        SmartDashboard.putString("Mode", "Autonomous");

        String selectedAuto = (String) autoChooser.getSelected();
        String gameData = DriverStation.getInstance().getGameSpecificMessage();

        if (selectedAuto == "Straight") {
            StraightAuto.start();
        }
        else if (selectedAuto == "Left") {
            if (gameData.length() > 0) {
                if (gameData.charAt(0) == 'L') {
                    LeftAutoLeft.start();
                } else {
                    StraightAuto.start();
                }
            }
        }
        else if (selectedAuto == "Right") {
            if (gameData.length() > 0) {
                if (gameData.charAt(0) == 'R') {
                    RightAutoRight.start();
                } else {
                    StraightAuto.start();
                }
            }
        }
        else if (selectedAuto == "StraightLeft") {
            if (gameData.length() > 0) {
                if (gameData.charAt(0) == 'L') {
                    StraightAutoThrow.start();
                } else {
                    StraightAutoNoZero.start();
                }
            }
        }
        else if (selectedAuto == "StraightRight") {
            if (gameData.length() > 0) {
                if (gameData.charAt(0) == 'R') {
                    StraightAutoThrow.start();
                } else {
                    StraightAutoNoZero.start();
                }
            }
        }
        else if (selectedAuto == "StraightNoZero") {
            StraightAutoNoZero.start();
        }
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        loopMaster.setGameState("Teleop");
        SmartDashboard.putString("Mode", "Teleop");
    }

    @Override
    public void testInit() {
         loopMaster.setGameState("Test");
         SmartDashboard.putString("Mode", "Test");
    }
}