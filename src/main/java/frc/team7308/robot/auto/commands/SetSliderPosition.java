package frc.team7308.robot.auto.commands;

public class ThrowBox extends Command {
    private boolean clawOut

    public ThrowBox(boolean clawOut) {
        this.clawOut = clawOut;
        requires(claw);
    }

    protected void initialize() {
        setTimeout(0.5);
        if (clawOut) {
            claw.m_clawSlider.set(DoubleSolenoid.Value.kForward);
        }
        else {
            claw.m_clawSlider.set(DoubleSolenoid.Value.kReverse);
        }
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        if(clawOut) {
            claw.m_sliderOut = true;
        }else{
            claw.m_sliderOut = false;
        }
    }

    protected void interrupted() {
        claw.m_clawSlider.set(DoubleSolenoid.Value.kForward);
    }
}