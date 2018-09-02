package frc.team7308.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.team7308.robot.ControlLoop;
import frc.team7308.robot.DriverStation;
import frc.team7308.robot.subsystems.Subsystem;

public class Claw extends Subsystem{
    DoubleSolenoid boxEjector;
    DoubleSolenoid clawSlider;
    DoubleSolenoid clawActuator;

    private DriverStation driverStation;

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopPeriodic() {
            actuateClaw(driverStation.getOpenClaw());
            /*actuateSlider(false, false);
            actuateEjector(false);*/
        }
    };

    public Claw(){
        boxEjector = new DoubleSolenoid(0, 1);
        clawSlider = new DoubleSolenoid(2, 3);
        clawActuator = new DoubleSolenoid(4,5);

        this.driverStation = DriverStation.getInstance();

        controlLoop.start();
    }

    public void actuateClaw(boolean openClaw){
        System.out.println("hi");
        if(openClaw) {
            clawActuator.set(DoubleSolenoid.Value.kForward);
        } else{
            clawActuator.set(DoubleSolenoid.Value.kReverse);
        }
    }
    /*public void actuateSlider(boolean sliderOut, boolean sliderIn){
        if(sliderOut){
            clawSlider.set(DoubleSolenoid.Value.kForward);
        }else if(sliderIn){
            clawSlider.set(DoubleSolenoid.Value.kReverse);
        }
    }
    public void actuateEjector(boolean ejectorOut){
        if(ejectorOut){

        }
    }*/
}