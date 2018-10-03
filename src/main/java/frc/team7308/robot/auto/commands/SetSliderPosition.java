package frc.team7308.robot.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.team7308.robot.Robot;

// Sets the claw slider to the specified position where true = out and false = in. t = 1.0s.
public class SetSliderPosition extends Command {
    private boolean sliderOut;

    public SetSliderPosition(boolean sliderOut) {
        this.sliderOut = sliderOut;
    }

    protected void initialize() {
        setTimeout(1.0);
        if (sliderOut) {
            Robot.claw.m_sliderOut = true;
            Robot.claw.m_clawSlider.set(DoubleSolenoid.Value.kForward);
        }
        else {
            Robot.claw.m_sliderOut = false;
            Robot.claw.m_clawSlider.set(DoubleSolenoid.Value.kReverse);
        }
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