package frc.team7308.robot.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team7308.robot.Robot;

public class SetSliderPosition extends Command {
    private boolean sliderOut;

    public DriveForTime(boolean sliderOut) {
        this.sliderOut = sliderOut;
    }

    protected void initialize() {
        setTimeout(0.5);
        if (sliderOut) {
            Robot.claw.m_clawSlider.set(DoubleSolenoid.Value.kForward);
        }
        else {
            Robot.claw.m_clawSlider.set(DoubleSolenoid.Value.kReverse);
        }
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        m_sliderOut = true;
        Robot.claw.m_clawSlider.set(DoubleSolenoid.Value.kForward);
    }

    protected void interrupted() {
        Robot.claw.m_clawSlider.set(DoubleSolenoid.Value.kForward);
    }
}