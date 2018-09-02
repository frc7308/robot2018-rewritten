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
    private Joystick liftThrottle;

    public DriverStation() {
        this.throttleStick = new Joystick(1);
        this.rotationWheel = new Joystick(0);
        this.quickTurnTrigger = new JoystickButton(this.rotationWheel, 1);

        this.liftThrottle = new Joystick(3);
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
}