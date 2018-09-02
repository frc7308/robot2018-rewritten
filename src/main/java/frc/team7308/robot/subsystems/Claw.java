package frc.team7308.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.team7308.robot.ControlLoop;
import frc.team7308.robot.DriverStation;
import frc.team7308.robot.subsystems.Subsystem;

public class Claw extends Subsystem{
    private DoubleSolenoid m_boxEjector;
    private DoubleSolenoid m_clawSlider;
    private DoubleSolenoid m_clawActuator;

    private boolean m_ejectorOut;

    private int m_totalTime;

    private DriverStation driverStation;

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopPeriodic() {
            actuateClaw(driverStation.getOpenClaw());
            setSliderPosition(driverStation.getClawSliderOut(), driveStation.getClawSliderIn());
            actuateEjector(driverStation.getEjectorTrigger());
            if(m_ejectorOut){
                m_totalTime += deltaTime;
                System.out.println(m_totalTime);
            }
        }
    };

    public Claw(){
        m_boxEjector = new DoubleSolenoid(0, 1);
        m_clawSlider = new DoubleSolenoid(2, 3);
        m_clawActuator = new DoubleSolenoid(4, 5);
        m_ejectorOut = false;
        m_totalTime = 0;

        this.driverStation = DriverStation.getInstance();

        controlLoop.start();
    }

    public void actuateClaw(boolean openClaw){
        if(openClaw) {
            clawActuator.set(DoubleSolenoid.Value.kForward);
        } else{
            clawActuator.set(DoubleSolenoid.Value.kReverse);
        }
    }
    public void setSliderPosition(boolean sliderOut, boolean sliderIn){
        if(sliderOut){
            clawSlider.set(DoubleSolenoid.Value.kForward);
        }else if(sliderIn){
            clawSlider.set(DoubleSolenoid.Value.kReverse);
        }
    }
    public void actuateEjector(boolean ejectorOut){
        if(ejectorOut&&!ejectorOut){
            boxEjector.set(DoubleSolenoid.Value.kForward);
            ejectorOut = true;


        }
    }
}