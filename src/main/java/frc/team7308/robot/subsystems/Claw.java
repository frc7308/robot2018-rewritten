package frc.team7308.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.team7308.robot.ControlLoop;
import frc.team7308.robot.Input;
import frc.team7308.robot.subsystems.Subsystem;
import frc.team7308.robot.subsystems.Lift;

public class Claw extends Subsystem{
    public DoubleSolenoid m_boxEjector;
    public DoubleSolenoid m_clawSlider;
    public DoubleSolenoid m_clawActuator;

    public static boolean m_sliderOut;
    private boolean m_clawOpen;
    private boolean m_ejectorOut;

    private Input driverStation;

    private boolean throwing;
    private double throwingTime;

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopInit() {
            m_sliderOut = true;
        }

        @Override
        public void loopPeriodic() {
            if (gameState.equals("Teleop")) {
                // Set the open slider flag if they are pressing the out or in buttons.
                if (driverStation.getClawSliderOut()) {
                    m_sliderOut = true;
                // Prevents the claw from sliding in if encoder value is less than 200 to prevent it intersecting with the lift support beams.
                } else if (driverStation.getClawSliderIn() && Lift.m_encoder.get() > 200) {
                    m_sliderOut = false;
                }

                // Set the open claw flag to true if the manipulator is pressing their open claw button or if the driver presses
                // their open claw button and the lift height is below 0, otherwise throw instead.
                // This allows the driver to control the claw without worrying about opening and throwing, using
                // only one button to open the claw and throw based on the height of the lift.
                if ((driverStation.getOpenClaw() || (driverStation.getOpenClawDriver() && Lift.m_encoder.get() <= 0)) && m_sliderOut) {
                    m_clawOpen = true;
                } else {
                    m_clawOpen = false;
                }

                // Set ejector flag to true if the manipulator is pressing their stick's trigger.
                if (driverStation.getEjectorTrigger()) {
                    m_ejectorOut = true;
                } else {
                    m_ejectorOut = false;
                }

                // Timer that keeps the throw action going for 100 ms at a minimum.
                // The timer is constantly reset while the button is being held down,
                // allowing for longer throws.
                if (throwingTime > 100) {
                    throwing = false;
                } else {
                    throwingTime += deltaTime;
                }
                if (driverStation.getThrowBox() || (driverStation.getOpenClawDriver() && Lift.m_encoder.get() > 0)) {
                    throwing = true;
                    throwingTime = 0;
                }
                if (throwing && m_sliderOut) {
                    m_clawOpen = true;
                    m_ejectorOut = true;
                }
            
                // Set the pneumatic solenoid states based on the state flags.
                if (m_sliderOut) {
                    m_clawSlider.set(DoubleSolenoid.Value.kForward);
                } else {
                    m_clawSlider.set(DoubleSolenoid.Value.kReverse);
                }
                if (m_clawOpen) {
                    m_clawActuator.set(DoubleSolenoid.Value.kForward);
                } else {
                    m_clawActuator.set(DoubleSolenoid.Value.kReverse);
                }
                if (m_ejectorOut) {
                    m_boxEjector.set(DoubleSolenoid.Value.kForward);
                } else {
                    m_boxEjector.set(DoubleSolenoid.Value.kReverse);
                }
            }
        }
    };

    public Claw() {
        // Initialize the piston solenoids.
        m_boxEjector = new DoubleSolenoid(0, 1);
        m_clawSlider = new DoubleSolenoid(2, 3);
        m_clawActuator = new DoubleSolenoid(4, 5);
        m_ejectorOut = false;

        this.driverStation = Input.getInstance();
    }

    // Used for superstructure collision detection in the Lift class
    public boolean getSliderOut() {
        return m_sliderOut;
    }
}