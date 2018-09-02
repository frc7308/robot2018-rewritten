package frc.team7308.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.team7308.robot.ControlLoop;

public class DriverStation {
    private static DriverStation m_instance = null;

    public static DriverStation getInstance() {
        if (m_instance == null) {
            m_instance = new DriverStation();
        }
        return m_instance;
    }

    private Joystick throttleStick;
    private Joystick rotationWheel;
    private JoystickButton quickTurnTrigger;
    private Joystick liftStick;
    private Joystick buttonBoard;
    private JoystickButton clawSliderIn;
    private JoystickButton clawSliderOut;
    private JoystickButton throwBox;
    private JoystickButton openClaw;
    private JoystickButton autoAlign;

    public DriverStation() {
        this.throttleStick = new Joystick(1);
        this.rotationWheel = new Joystick(0);
        this.quickTurnTrigger = new JoystickButton(this.rotationWheel, 1);
        this.liftStick = new Joystick(3);
        this.clawSliderIn = new JoystickButton(this.buttonBoard, 0);
        this.clawSliderIn = new JoystickButton(this.buttonBoard, 1);
        this.throwBox = new JoystickButton(this.buttonBoard, 2);
        this.openClaw = new JoystickButton(this.buttonBoard, 3);
        this.autoAlign = new JoystickButton(this.buttonBoard, 4);
    }

    public double getThrottle() {
        return throttleStick.getY();
    }

    public double getRotation() {
        return rotationWheel.getX();
    }

    public boolean getQuickTurn() {
        return quickTurnTrigger.get();
    }

    public double getLiftThrottle() {
        return liftStick.getY();
    }

    public boolean getOpenClaw() { return openClaw.get(); }
}