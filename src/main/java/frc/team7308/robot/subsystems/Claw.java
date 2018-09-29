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

    private boolean m_ejectorOut;
    private boolean m_sliderOut;
    private boolean m_clawOpenAndEject;

    private int m_totalTime1;
    private int m_totalTime2;
    private int m_totalTime3;
    private int m_encoderValue;

    private DriverStation driverStation;
    private Lift lift;

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopPeriodic() {
            actuateClaw(driverStation.getOpenClaw());
            setSliderPosition(driverStation.getClawSliderOut(), driverStation.getClawSliderIn());
            actuateEjector(driverStation.getEjectorTrigger());
            throwCube(driverStation.getThrowBox());
            if (m_ejectorOut) {
                m_totalTime1 += deltaTime;
                if(m_totalTime1==100){
                    m_ejectorOut = false;
                }
            }
            if(!m_sliderOut) {
                m_totalTime2 += deltaTime;
                if (m_totalTime2 == 500) {
                    m_sliderOut = true;
                    m_totalTime2 = 0;
                }
            }
//            if (m_clawOpenAndEject) {
//                m_totalTime3 += deltaTime;
//                System.out.println(m_totalTime3);
//                if(m_totalTime3 == 50) {
//                    m_totalTime3 = 0;
//                    System.out.println(m_totalTime3);
//                    m_boxEjector.set(DoubleSolenoid.Value.kForward);
//                }
//                // if (m_totalTime3 == 50) {
//                //     m_boxEjector.set(DoubleSolenoid.Value.kForward);
//                //     m_clawOpenAndEject = false;
//                //     m_boxEjector.set(DoubleSolenoid.Value.kReverse);
//                //     actuateClaw(false);
//                //     m_totalTime3 = 0;
//                // }
//            }
            System.out.println(m_totalTime1 + ", " + m_totalTime2 + ", " + m_totalTime3);
        }
    };

    public Claw(){
        m_boxEjector = new DoubleSolenoid(0, 1);
        m_clawSlider = new DoubleSolenoid(2, 3);
        m_clawActuator = new DoubleSolenoid(4, 5);
        m_ejectorOut = false;
        m_totalTime1 = 0;
        m_totalTime2 = 0;
        m_totalTime3 = 0;

        this.driverStation = DriverStation.getInstance();
    }

    public void actuateClaw(boolean openClaw){
        if (openClaw && m_sliderOut) {
            m_clawActuator.set(DoubleSolenoid.Value.kForward);
        } else {
            m_clawActuator.set(DoubleSolenoid.Value.kReverse);
        }
    }
    public void setSliderPosition(boolean sliderOut, boolean sliderIn){
        if (sliderOut) {
            m_clawSlider.set(DoubleSolenoid.Value.kForward);
            m_sliderOut = true;
        } else if (sliderIn && m_encoderValue>200) {
            m_clawSlider.set(DoubleSolenoid.Value.kReverse);
            m_sliderOut = false;
        }
    }
    public void actuateEjector(boolean ejectorOut) {
        if (ejectorOut && !m_ejectorOut && m_sliderOut) {
            m_boxEjector.set(DoubleSolenoid.Value.kForward);
            m_ejectorOut = true;
        } else {
            m_boxEjector.set(DoubleSolenoid.Value.kReverse);
        }
    }
    public void throwCube(boolean throwBox){
        if(throwBox && m_sliderOut){
            m_clawActuator.set(DoubleSolenoid.Value.kForward);
            m_boxEjector.set(DoubleSolenoid.Value.kForward);
        }
        if(!throwBox && m_sliderOut){
            m_clawActuator.set(DoubleSolenoid.Value.kReverse);
            m_boxEjector.set(DoubleSolenoid.Value.kReverse);
        }
    }
    public boolean getSliderOut() {
        return m_sliderOut;
    }
}