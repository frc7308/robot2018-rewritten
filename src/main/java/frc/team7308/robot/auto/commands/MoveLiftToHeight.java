package frc.team7308.robot.auto.commands;

public class MoveLiftToHeight extends Command {
    private int height;

    public DriveForTime(int height) {
        this.height = height;
        this.time = time;
    	requires(lift);
    }

    protected void initialize() {
        lift.setHeight(height);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Math.abs(lift.m_encoder.get() - height) < lift.kAcceptableError;
    }

    protected void end() {
    }

    protected void interrupted() {
        lift.setHeight(lift.m_encoder.get());
    }
}