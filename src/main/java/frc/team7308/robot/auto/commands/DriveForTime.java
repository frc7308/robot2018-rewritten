package frc.team7308.robot.auto.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team7308.robot.Robot;

public class DriveForTime extends Command {
    private double time;
    private double leftSpeed;
    private double rightSpeed;

    public DriveForTime(double time, double leftSpeed, double rightSpeed) {
        this.leftSpeed = leftSpeed;
        this.rightSpeed = rightSpeed;
        this.time = time;
    }

    protected void initialize() {
        setTimeout(time);
        Robot.drivetrain.TankDrive(leftSpeed, rightSpeed);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
        Robot.drivetrain.TankDrive(0, 0);
    }

    protected void interrupted() {
        Robot.drivetrain.TankDrive(0, 0);
    }
}