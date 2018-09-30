package frc.team7308.robot.auto.commands;

public class ThrowBox extends Command {

    public ThrowBox() {
        requires(claw);
    }

    protected void initialize() {
        claw.m_clawSlider.set(DoubleSolenoid.Value.kForward);
        setTimeout(0.5);
        claw.m_clawActuator.set(DoubleSolenoid.Value.kForward);
        claw.m_boxEjector.set(DoubleSolenoid.Value.kForward);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        claw.m_sliderOut = true;
        claw.m_clawOpen = true;
        claw.m_ejectorOut = true;
    }

    protected void interrupted() {
        claw.m_clawSlider.set(DoubleSolenoid.Value.kForward);
    }
}