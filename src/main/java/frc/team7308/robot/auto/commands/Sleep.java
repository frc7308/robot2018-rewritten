package frc.team7308.robot.auto.commands;

import edu.wpi.first.wpilibj.command.Command;

// Sleeps the auto routine for a specified amount of time. t depends on the specified time.
public class Sleep extends Command {
    private double time;

    public Sleep(double time) {
        this.time = time;
    }

    protected void initialize() {
        setTimeout(time);
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