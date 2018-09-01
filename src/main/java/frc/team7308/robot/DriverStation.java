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

    public DriverStation() {
        this.throttleStick = new Joystick(1);
        this.rotationWheel = new Joystick(0);
        this.quickTurnTrigger = new JoystickButton(this.throttleStick, 1);
    }

    public double getThrottle() {
        return throttleStick.getY();
    }

    public double getRotation() {
        return rotationWheel.getY();
    }

    public boolean getQuickTurn() {
        return quickTurnTrigger.get();
    }
}