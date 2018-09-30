package frc.team7308.robot.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team7308.robot.Robot;

public class ThrowBox extends Command {

    public DriveForTime() {

    }

    protected void initialize() {
        setTimeout(1);
        Robot.claw.m_clawActuator.set(DoubleSolenoid.Value.kForward);
        Robot.claw.m_boxEjector.set(DoubleSolenoid.Value.kForward);
        m_clawOpen = true;
        m_ejectorOut = true;
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        Robot.claw.m_clawActuator.set(DoubleSolenoid.Value.kReverse);
        Robot.claw.m_boxEjector.set(DoubleSolenoid.Value.kReverse);
        m_clawOpen = false;
        m_ejectorOut = false;
    }

    protected void interrupted() {
        Robot.claw.m_clawActuator.set(DoubleSolenoid.Value.kReverse);
        Robot.claw.m_boxEjector.set(DoubleSolenoid.Value.kReverse);
        m_clawOpen = false;
        m_ejectorOut = false;
    }
}