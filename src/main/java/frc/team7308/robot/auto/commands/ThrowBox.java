package frc.team7308.robot.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.team7308.robot.Robot;

public class ThrowBox extends Command {

    public ThrowBox() {
    }

    protected void initialize() {
        setTimeout(1);
        Robot.claw.m_clawActuator.set(DoubleSolenoid.Value.kForward);
        Robot.claw.m_boxEjector.set(DoubleSolenoid.Value.kForward);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}