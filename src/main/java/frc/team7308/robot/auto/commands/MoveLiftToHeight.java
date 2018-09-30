package frc.team7308.robot.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team7308.robot.Robot;

public class MoveLiftToHeight extends Command {
    private int height;

    public MoveLiftToHeight(int height) {
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