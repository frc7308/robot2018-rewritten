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

    public DriverStation(int driveStickPort, int driveWheelPort) {
        this.driveStick = new Joystick(driveStickPort);
        this.driveWheel = new Joystick(driveWheelPort);
        this.quickTurnTrigger = new JoystickButton(this.driveWheel, 1);

        this.driveWheel.setYChannel(0);

        controlLoop.start();
    }
}