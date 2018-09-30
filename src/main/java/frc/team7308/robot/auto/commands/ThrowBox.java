package frc.team7308.robot.auto.commands;

public class ThrowBox extends Command {

    public ThrowBox() {
        requires(claw);
    }

    protected void initialize() {
        setTimeout(1);
        claw.m_clawActuator.set(DoubleSolenoid.Value.kForward);
        claw.m_boxEjector.set(DoubleSolenoid.Value.kForward);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        claw.m_clawOpen = true;
        claw.m_ejectorOut = true;
    }

    protected void interrupted() {
        claw.m_clawActuator.set(DoubleSolenoid.Value.kReverse);
        claw.m_boxEjector.set(DoubleSolenoid.Value.kReverse);
    }
}