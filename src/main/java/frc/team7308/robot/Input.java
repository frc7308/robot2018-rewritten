package frc.team7308.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.team7308.robot.ControlLoop;

// Maps driver station inputs to functions to increase readability and consistency in code.
public class Input {
    private static Input m_instance = null;

    public static Input getInstance() {
        if (m_instance == null) {
            m_instance = new Input();
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

    public Input() {
        this.throttleStick = new Joystick(1);
        this.rotationWheel = new Joystick(0);
        this.quickTurnTrigger = new JoystickButton(this.rotationWheel, 1);
        this.liftStick = new Joystick(3);
        this.buttonBoard = new Joystick(2);
        this.clawSliderIn = new JoystickButton(this.buttonBoard, 1);
        this.clawSliderOut = new JoystickButton(this.buttonBoard, 2);
        this.throwBox = new JoystickButton(this.buttonBoard, 3);
        this.openClaw = new JoystickButton(this.buttonBoard, 4);
        this.autoAlign = new JoystickButton(this.buttonBoard, 5);
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

    public boolean getOpenClaw() {
        return openClaw.get();
    }

    public boolean getClawSliderIn() {
        return clawSliderIn.get();
    }

    public boolean getClawSliderOut() {
        return clawSliderOut.get();
    }

    public boolean getThrowBox() {
        return throwBox.get();
    }

    public boolean getOpenClawDriver() {
        return throttleStick.getTrigger();
    }

    // Not used due to Jetson power issues.
    public boolean autoAlign() {
        return autoAlign.get();
    }

    public boolean getEjectorTrigger() {
        return liftStick.getTrigger();
    }
}