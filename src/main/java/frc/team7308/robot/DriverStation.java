package frc.team7308.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.team7308.robot.ControlLoop;

public class DriverStation {
    public static double driveThrottle;
    public static double driveRotation;
    public static boolean quickTurn;

    private Joystick driveStick;
    private Joystick driveWheel;
    
    private JoystickButton quickTurnTrigger;

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopInit() {
            System.out.println("test");
        }
        @Override
        public void loopPeriodic() {
            driveThrottle = driveStick.getY();
            driveRotation = driveWheel.getY();
            quickTurn = quickTurnTrigger.get();
        }
    };

    public DriverStation() {
        this.driveStick = new Joystick(0);
        this.driveWheel = new Joystick(1);
        this.quickTurnTrigger = new JoystickButton(this.driveWheel, 1);

        this.driveWheel.setYChannel(0);

        controlLoop.start();
    }
}