package frc.team7308.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.team7308.robot.ControlLoop;
import frc.team7308.robot.DriverStation;
import frc.team7308.robot.subsystems.Subsystem;
import frc.team7308.robot.subsystems.Lift;

public class Claw extends Subsystem{
    private DoubleSolenoid m_boxEjector;
    private DoubleSolenoid m_clawSlider;
    private DoubleSolenoid m_clawActuator;

    public static boolean m_sliderOut;
    private boolean m_clawOpen;
    private boolean m_ejectorOut;

    private DriverStation driverStation;

    private boolean throwing;
    private double throwingTime;

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopInit() {
            m_sliderOut = true;
        }

        @Override
        public void loopPeriodic() {
            if (driverStation.getClawSliderOut()) {
                m_sliderOut = true;
            } else if (driverStation.getClawSliderIn() && Lift.m_encoder.get() > 200) {
                m_sliderOut = false;
            }

            if (driverStation.getOpenClaw() && m_sliderOut) {
                m_clawOpen = true;
            } else {
                m_clawOpen = false;
            }

            if (driverStation.getEjectorTrigger()) {
                m_ejectorOut = true;
            } else {
                m_ejectorOut = false;
            }

            if (driverStation.getThrowBox()) {
                throwing = true;
                throwingTime = 0;
            }
            if (throwingTime > 100) {
                throwing = false;
            } else {
                throwingTime += deltaTime;
            }
            if (throwing && m_sliderOut) {
                m_clawOpen = true;
                m_ejectorOut = true;
            }
           
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
    };

    public Claw(){
        m_boxEjector = new DoubleSolenoid(0, 1);
        m_clawSlider = new DoubleSolenoid(2, 3);
        m_clawActuator = new DoubleSolenoid(4, 5);
        m_ejectorOut = false;

        this.driverStation = DriverStation.getInstance();
    }

    public void actuateEjector(boolean ejectorOut) {
        if (ejectorOut && !m_ejectorOut && m_sliderOut) {
            m_boxEjector.set(DoubleSolenoid.Value.kForward);
            m_ejectorOut = true;
        } else {
            m_boxEjector.set(DoubleSolenoid.Value.kReverse);
        }
    }

    public boolean getSliderOut() {
        return m_sliderOut;
    }
}