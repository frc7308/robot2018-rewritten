package frc.team7308.robot.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team7308.robot.Robot;

// Moves the lift to a position using normalized coordinates where 0 = the zero position
// and 1.0 is the highest possible location. Lowest position is -0.1666 repeating. t depends on goal height.
public class MoveLiftToHeight extends Command {
    private double height;

    public MoveLiftToHeight(double height) {
        this.height = height;
    }

    protected void initialize() {
        Robot.lift.setHeight(height);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return Math.abs(Robot.lift.m_encoder.get() - height) < Robot.lift.kAcceptableError;
    }

    protected void end() {
    }

    protected void interrupted() {
        Robot.lift.setHeight(Robot.lift.m_encoder.get());
    }
}