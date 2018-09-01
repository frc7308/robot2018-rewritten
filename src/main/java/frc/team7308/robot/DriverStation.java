package frc.team7308.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.team7308.robot.ControlLoop;

public class DriverStation {
    public static double driveMovement;
    public static double driveRotation;
    public static boolean releaseCube;

    private Joystick driveMovementStick;
    private Joystick driveMovementRotationStick;
    private JoystickButton releaseTrigger;

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopInit() {
            System.out.println("test");
        }
        @Override
        public void loopPeriodic() {
            driveThrottle = driveStick.getY();
            driveRotation = driveWheel.getY();
            releaseCube = releaseTrigger.get();
        }
    };

    public DriverStation() {
        this.driveMovementStick = new Joystick(1);
        this.driveMovementRotationStick = new Joystick(0);
        this.releaseTrigger = new JoystickButton(this.driveMovementStick, 1);

        controlLoop.start();
    }
}